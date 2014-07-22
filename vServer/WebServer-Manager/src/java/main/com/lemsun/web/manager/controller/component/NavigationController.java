package com.lemsun.web.manager.controller.component;

import com.lemsun.core.*;
import com.lemsun.core.member.NavigationReturnData;
import com.lemsun.core.service.INavigationService;
import com.lemsun.core.service.IResourceService;
import com.lemsun.web.manager.model.component.CusNavigationComponentModel;
import com.lemsun.web.manager.model.component.NavigationTreeStore;
import com.lemsun.web.manager.model.component.ResourceSelectQuery;
import com.lemsun.web.model.ResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航管理
 * User: Lucklim
 * Date: 12-11-19
 * Time: 下午12:46
 */
@Controller
@RequestMapping("component/navigation")
public class NavigationController {

	@Autowired
	IResourceService resourceService;

    @Autowired
    private INavigationService navigationService;

    @RequestMapping(value = {"","/","view"})
    public String view()
    {
        return "admin/component/navigation";
    }

	/**
	 * 获取导航树形集合
	 * @return 导航树形集合
	 */
	@ResponseBody
	@RequestMapping("list/get")
	public List<NavigationTreeStore> getData()
	{
		Navigation navigation= navigationService.getRoot();

		return (new NavigationTreeStore()).convertNavigation(navigation,true);
	}

	/**
	 * 获取某导航下的所有挂载组件集合
	 * @param query 分页条件
	 * @return 此导航下的所有挂载组件集合
	 */
	@ResponseBody
	@RequestMapping("/list/getncdata")
	public ResponseEntity<ArrayList<CusNavigationComponentModel>> getNavigationComponentData(CusNavigationComponentModel query)
	{
		ResponseEntity<ArrayList<CusNavigationComponentModel>> responseEntity=new ResponseEntity<>();

		if(StringUtils.isEmpty(query.getParentid())||query.getParentid()==null){
			query.setParentid(navigationService.getRootByParent().getId().toString());
		}

		Page<NavigationComponent> result = navigationService.getNComponentPagging(query);

		ArrayList<CusNavigationComponentModel> list = new ArrayList<>();

		for (NavigationComponent aList : result) {
			CusNavigationComponentModel cu = new CusNavigationComponentModel();
			cu.setId(aList.getId().toString());
			cu.setName(aList.getName());
			cu.setCategory(aList.getCategory());
			cu.setUpdateTime(aList.getUpdateTime());
			cu.setCreateUser(aList.getCreateUser());
			cu.setResourcePid(aList.getResourcePid());
			cu.setParentid(aList.getParent().getId().toString());

			list.add(cu);
		}
		//setSuccess设置为false时，不会刷新数据
		responseEntity.setSuccess(true);
		responseEntity.setEntity(list);
		responseEntity.setTotalCount(result.getTotalElements());
		return responseEntity;
	}

	/**
	 * 删除导航挂载组件信息
	 * @param query 要删除的导航挂载组件objid
	 * @return 删除后的导航挂载组件集合
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("list/delete")
	public ResponseEntity<ArrayList<CusNavigationComponentModel>> deleteNavigationComponentData(CusNavigationComponentModel query) throws Exception {
		navigationService.removeNavigationComponentById(query.getId());

		return getNavigationComponentData(query);
	}

	/**
	 * 添加导航
	 * @param filename 导航名称
	 * @param fileremark 导航备注
	 * @param parentpid 导航父objid
	 * @return 导航集合
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "addwj", method = RequestMethod.POST)
	public ResponseEntity addWJ(String filename,String fileremark,String parentpid) throws Exception {
		ResponseEntity responseEntity=new ResponseEntity();
		Navigation navigation = parentpid.trim().equals("") ? navigationService.getRootByParent() : navigationService.getNavigationByPid(parentpid);

		Navigation newNav=new Navigation(filename);
		//navigation 为 null 时，表示添加的是根节点
		newNav.setParent(navigation);
		newNav.setName(filename);
		newNav.setRemark(fileremark);
		newNav.setCreateUser("system");
		navigationService.create(newNav);

		responseEntity.setSuccess(true);
		return responseEntity;
	}

	/**
	 * 挂载组件
	 * @param navpid 组件pid
	 * @param navname 组件名称
	 * @param navremark 备注信息
	 * @param parentpid 导航objid
	 * @return 此导航的挂载组件集合
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "addzj", method = RequestMethod.POST)
	public ResponseEntity addNavigation(String navpid,String navname,String navremark,String parentpid) throws Exception {
		ResponseEntity responseEntity=new ResponseEntity();
		Navigation navigation = parentpid.trim().equals("") ? navigationService.getRootByParent() : navigationService.getNavigationByPid(parentpid);

		//表示添加的是组件
		Navigation nvi = parentpid.trim().equals("") ? navigationService.getRootByParent() : navigationService.getNavigationByPid(parentpid);
		if(nvi!=null)
		{
			NavigationComponent nv=new NavigationComponent();
			nv.setResourcePid(navpid);
			nv.setName(navname);
			nv.setRemark(navremark);
			nv.setCreateUser("system");

			navigationService.addComponent(nvi,nv);

			responseEntity.setSuccess(true);
			return responseEntity;
		}
		responseEntity.setMessage("请先创建导航根节点！");
		responseEntity.setSuccess(false);
		return responseEntity;
	}

	/**
	 * 删除导航信息
	 * @param pid 导航pid
	 */
    @ResponseBody
    @RequestMapping(value = "delete")
    public void deleteNavigation(String pid)
    {
        navigationService.deleteNavigation(pid);
    }
}
