package com.lemsun.form;

/**
 * 基本的显示组件对象
 */
public class BaseFormResource extends FormResource {

    public BaseFormResource()
    {
        super(null, null);
    }

    public BaseFormResource(String name, String category) {
        super(name, category);
    }
}
