package com.lemsun.task;

/**
 * User: 刘晓宝
 * Date: 14-3-18
 * Time: 下午2:03
 */
public class CronTime {
    /**
     * 0代表一般人员选择输入
     * 1 代表高级人员输入
     */
    private int group;
    private String remark;
    /**
     * 年 月 日
     */
    private String date;
    /**
     * 时间
     */
    private String time;
    /**
     * 执行规律
     */
    private int performRegular;
    /**
     * 按天还是按周执行 1 代表天 2代表周
     */
    private int dayAndWeeks;
    /**
     * 几分钟执行一次
     */
    private int walk;
    /**
     * 那几个星期执行
     */
    private int[] week;
    /**
     * 每月那几天执行
     */
    private String day;
    /**
     * 那几月执行
     */
    private String[] month;
    /**
     * 月里边那几个星期执行
     */
    private String[] zr;
    /**
     * 星期几执行
     */
    private String[] xq;
    /**
     * 产生时间表达式
     */
    private String cronExpression;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPerformRegular() {
        return performRegular;
    }

    public void setPerformRegular(int performRegular) {
        this.performRegular = performRegular;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public int getDayAndWeeks() {
        return dayAndWeeks;
    }

    public void setDayAndWeeks(int dayAndWeeks) {
        this.dayAndWeeks = dayAndWeeks;
    }

    public int getWalk() {
        return walk;
    }

    public void setWalk(int walk) {
        this.walk = walk;
    }

    public int[] getWeek() {
        return week;
    }

    public void setWeek(int[] week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String[] getMonth() {
        return month;
    }

    public void setMonth(String[] month) {
        this.month = month;
    }

    public String[] getZr() {
        return zr;
    }

    public void setZr(String[] zr) {
        this.zr = zr;
    }

    public String[] getXq() {
        return xq;
    }

    public void setXq(String[] xq) {
        this.xq = xq;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
