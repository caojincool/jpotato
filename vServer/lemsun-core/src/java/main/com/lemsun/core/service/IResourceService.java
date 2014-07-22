package com.lemsun.core.service;

import com.lemsun.core.*;
import com.lemsun.core.PreviewImageSize;
import com.lemsun.core.util.Pid;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

import java.io.InputStream;
import java.util.List;

/**
 * 基本的组件管理服务接口
 * User: Xudong
 * Date: 12-10-25
 * Time: 下午4:03
 */
public interface IResourceService {


    /**
     * 获取组件的预览图片在数据库中的文件名称
     * @param pid
     * @param size
     * @return
     */
    String getPreviewImageName(String pid, PreviewImageSize size);

    /**
     * 返回保存在DB数据库中的文件完整的名称
     * @param pid 组件ID
     * @param filename 文件名
     * @param filetype 文件类型
     * @return 数据库的文件完整名称
     */
    String getDbFileName(String pid, String filename, String filetype);

    /**
     * 获取组件在数据库中的文件名
     * @param pid
     * @return
     */
    String getDbContentName(String pid);

    /**
     * 获取组件的描述信息, 在数据库的文件名
     * @param pid
     * @return
     */
    String getDbResourceDescriptionName(String pid);


    /**
     * 使用业务编码查找组件对象.
     *
     * 一般情况过程,
     * 先在当前账套下进行查找, 如果有重复的, 将优先获取最近一次修改的时间.
     * 如果没有发现将对全局进行查找.
     *
     * 编码中的 "/" 或者 "\" 有特殊的意义.
     * 账套将按照树状进行组织. 末级为组件编码. "/" 或者 "\" 之前的都是账套编码.
     * 如果编码中带有 "/" 或者 "\" 就是按照账套路径进行查找
     *
     * @param code 业务编码
     * @return 基本组件对象信息
     */
    LemsunResource getByBusinessCode(String code);


    /**
     * 创建组件
     * @param resource
     */
    void create(IResource resource);


    /**
     * 查询获取分页的组件模型
     */
    <T extends IResource> Page<T> getPageing(AbstractPageRequest query,Class<T> clazz);


	/**
	 * 在树状的组件结构中依次查找父节点的类 一旦发现类 就返回组件对
	 *
	 * @param resource 资源对象
	 * @param cate 查询类型
	 * @return 资源对象
	 */
	IResource findParentResource(IResource resource, String cate);


	/**
	 * 获取组件的基本信息并返回基本对像
	 * @param pid 主键
	 * @return 返回对象
	 */
	LemsunResource getBaseResource(String pid);

	/**
	 * 获取一个组件的内容信息
	 * @param pid 主键
	 * @return 如果没有就返回空
	 */
	String getContent(String pid);


    /**
     * 获取组件的设置信息
     * @param pid
     * @return
     */
    String getSetting(String pid);


    /**
     * 获取组件下的全部附件集合
     * @param pid
     * @return
     */
    List<ResourceAttach> getAttachDetails(String pid);

    /**
     * 获取组件的附件对象
     * @param pid
     * @param filename
     * @param filetype
     * @return
     */
    ResourceAttach getAttachDetail(String pid, String filename, String filetype);


    /**
     * 通过附件的完整名称获取附件信息
     * @param fullname
     * @return
     */
    ResourceAttach getAttachDetail(String fullname);

    /**
     * 获取组件的预览图片文件
     * @param pid
     * @param size
     * @return
     */
    GridFSDBFile getPreviewImage(String pid, PreviewImageSize size);

    /**
     * 获取一个组件的内容文件
     * @param pid
     * @return
     */
    GridFSDBFile getContentFile(String pid);

    /**
     * 获取组件的附件
     * @param pid
     * @param filename
     * @param fileType
     * @return
     */
    GridFSDBFile getAttachFile(String pid, String filename, String fileType);

    /**
     * 获取组件的附件
     * @param pid
     * @param filename 附件名称, 如果为空. 那么获取组件的内容信息
     * @return
     */
    GridFSDBFile getAttachFile(String pid, String filename);


    /**
     * 使用文件的 ID 获取附件
     * @param id
     * @return
     */
    GridFSDBFile getAttachFile(ObjectId id);


    /**
     * 使用完整的名称获取附件文件
     * @param fullname
     * @return
     */
    GridFSDBFile getAttachFile(String fullname);


    /**
     * 将输入的字符作为皮肤信息保存到配置信息中
     * @param pid 组件PId
     * @param stream 皮肤字符流
     */
    void saveResourceFace(String pid, InputStream stream);


    /**
     * 根据pid查询并返回实体类
     */
    <T> T get(String pid, Class<T> type);

    /**
     * 获取一个类型化的对象模�
     */
    @Pid
    IResource get(String pid);

    /**
     * 使用类型获取全部组件
     * @param type
     * @param <T>
     * @return
     */
    <T extends IResource> List<T> getAll(Class<T> type);

    /**
     * 根据类型返回组件
     * @param category
     * @return
     */
    List<LemsunResource> getByCategory(String category);

    /**
     * 通过一个组件模型。 返回已这个组件的所关联的全部组件列表。 树形结构
     * @param pid
     * @return
     */
    LemsunResource loadTreeResourceByPid(String pid);

    /**
     * 获取子组件
     * @param pid
     * @return
     */
    List<LemsunResource> getChildResourceByParentId(String pid);


    /**
     * 保存一个组件的权限信息
     * @param pid 组件pid
     * @param rp 组件的权限集合
     */
    void updatePermissions(String pid,List<ResourcePermission> rp) throws Exception;

    /**
     * 获取一个组件的权限列表
     * @param pid 组件编码
     * @return 权限列表
     * 如果组件不存在返回null
     */
    List<ResourcePermission> listPermissions(String pid);

    /**
     * 通过通过组件类型查询它下分类函数
     * @param category
     * @return
     */
    public List<String> distinctScriptTypebyCategory(String category);

    /**
     * 通过函数类型和组件类别查询组件
     * @param category
     * @return
     */
    public List<LemsunResource> queryByCategoryAndScriptType(String category,String scriptType);


    /**
     * 更新一个组件
     * @param resource
     */
    void update(IResource resource);


    /**
     * 更新或者保存组件的附件
     * @param pid 组件PID
     * @param stream 内容流
     * @param fileName 文件名称
     * @param fileType 文件类型, 可以为空
     */
    void uploadAttachFile(String pid, InputStream stream, String fileName, String fileType);

    /**
     * 更新或者保存组件的附件
     * @param pid 组件PID
     * @param stream 内容流
     * @param fileName 文件名称
     */
    void uploadAttachFile(String pid, InputStream stream, String fileName);


    /**
     * 更新一个组件的内容
     * @param pid
     * @param content
     */
    void updateContent(String pid, String content);

    /**
     * 更新一个组件的内容
     * @param pid
     * @param stream
     */
    void updateContent(String pid, InputStream stream);

    /**
     * 更新一个组件的内容, 并将内容命名为给定的温度计
     * @param pid
     * @param stream
     * @param filename
     */
    void updateContent(String pid, InputStream stream, String filename);

    /**
     * 更新组件的预览图片
     * @param pid
     * @param size
     * @param stream
     */
    void updatePreviewImage(String pid, PreviewImageSize size, InputStream stream);

    /**
     * 移除一个不针对类型的组件. 特别注意: 如果不调用具体的类型组件而将组件移除, 有可能会不能进行比较干净的清楚
     * @param pid
     */
    void remove(String pid);


    /**
     * 移除组件的附件
     * @param pid
     * @param filename
     * @param filetype
     */
    void removeAttach(String pid, String filename, String filetype);

}
