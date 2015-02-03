package com.dp.baobao.model;



import java.util.List;
import java.util.Map;

/**
 * Created by dpyang on 2014/11/22.
 */
public class EasyUINode {
    private String id;
    private String text;
    private String state;
    private String iconCls;
    private boolean checked;
    private Map<String,String> attributes;
    private List<EasyUINode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<EasyUINode> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUINode> children) {
        this.children = children;
    }
/*
    public static EasyUINode loadDate(Tree tree){
        if (tree==null) return null;

        EasyUINode easyUINode=new EasyUINode();
        easyUINode.setId(tree.getId());
        easyUINode.setText(tree.getName());
        List<Tree> child=tree.getChildren();

        if(child!=null){
            List<EasyUINode> nodes=new ArrayList<EasyUINode>();
            for(Tree t:child){
                nodes.add(loadDate(t));
            }
            easyUINode.setChildren(nodes);
        }

        return easyUINode;
    }
*/

}
