package com.lemsun.form;

import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.repository.ResourceRepository;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * 单据资源
 * User: Xudong
 * Date: 12-10-17
 * Time: 下午2:59
 */
@Document(collection = ResourceRepository.ResourceCollectionName)
public abstract class FormResource extends AbstractLemsunResource {

	@PersistenceConstructor
	public FormResource(String name, String category) {
		super(name, category);
	}

    @Transient
    private String content;

	private Map<String, String> params = new HashMap<>();

    /**
     * 设置组件的附加信息
     * @param name 附加信息名称
     * @param value 附加信息的值
     */
	public void setAttribute(String name, String value)
	{
		if(params.containsKey(name)) params.remove(name);
		params.put(name, value);
	}

    /**
     * 根据组件附加信息的名字获取值
     * @param name 附加信息的名称
     * @return 附加信息值
     */
	public String getAttribute(String name)
	{
		return params.containsKey(name)?params.get(name):null;
	}

    /**
     * 移除组件的附加信息
     * @param name 附加信息名字
     */
	public void removeAttribute(String name)
	{
		if(params.containsKey(name)) params.remove(name);
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
