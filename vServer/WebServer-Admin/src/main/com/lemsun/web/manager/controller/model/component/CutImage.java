package com.lemsun.web.manager.controller.model.component;

/**
 * 接受前台传送切割图片相关信息
 * User: 刘晓宝
 * Date: 13-10-15
 * Time: 下午3:36
 */
public class CutImage {
    private int x;//切割起始x坐标
    private int y;//切割起始y坐标
    private int width;
    private int height;
    private String srcPath;
    private String descPath;
    public CutImage(){

    }
    public CutImage( int width, int height,String descPath) {

        this.width = width;
        this.height = height;
        this.descPath=descPath;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getHeight() {
        return height;
    }



    public void setHeight(int height) {
        this.height = height;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getDescPath() {
        return descPath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setDescPath(String descPath) {
        this.descPath = descPath;
    }
}
