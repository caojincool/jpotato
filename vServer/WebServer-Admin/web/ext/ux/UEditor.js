/**
 * Created with UEditor的扩展
 * User: Administrator
 * Date: 13-3-20
 * Time: 下午4:58
 */
Ext.form.UEditor = function (config) {
    Ext.form.UEditor.superclass.constructor.call(this, config);
    this.UEditor = 0;
    this.MyisLoaded = false;
    this.MyValue = '';
};

Ext.define(Ext.form.UEditor, Ext.form.field.TextAreaView, {
    onRender: function (ct, position) {
        if (!this.el) {
            this.defaultAutoCreate = {
                tag: "textarea",
                style: "width:100px;height:60px;",
                autocomplete: "off"
            };
        }
    }
});