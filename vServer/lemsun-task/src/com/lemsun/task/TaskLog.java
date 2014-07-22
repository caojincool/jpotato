package com.lemsun.task;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lemsun.core.jackson.ObjectIdDeserializer;
import com.lemsun.core.jackson.ObjectIdSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

/**
 * User: 刘晓宝
 * Date: 4/9/14
 * Time: 8:42 AM
 */
public class TaskLog {

    public static final int SUCESS=1;

    public static final int ERROR=2;

    public static final int TIMEOUT=4;

    public static final int RUNING=8;
    @Id
    private ObjectId _id;
    @Indexed
    private String lid;

    private String pid;

    private String taskName;

    private Date startTime;

    private Date endTime;

    private long runTime;

    private int state;

    private String remark;

    public String getPid() {
        return pid;
    }

    public long getRunTime() {
        return runTime;
    }

    public void setRunTime(long runTime) {
        this.runTime = runTime;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * 获取数据库的组件编码
     *
     * @return
     */
    @JsonSerialize(using = ObjectIdSerializer.class)
    public ObjectId getId() {
        return _id;
    }

    @JsonDeserialize(using = ObjectIdDeserializer.class)
    protected void setId(ObjectId id) {
        _id = id;
    }

    /**
     * 任务编码
     * @return
     */
    public String getLid() {
        return lid;
    }

    /**
     * 任务编码
     * @param lid
     */
    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getTaskName() {
        return taskName;
    }

    /**
     * 任务名
     * @param taskName
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 任务启动时间
     * @return
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 任务开始时间
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 任务结束时间
     * @return
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 任务结束时间
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 日志状态
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * 日志状态
     * @param state
     */
    public void setState(int state) {
        this.state = state;
    }
    /**
     * 获取日志执行结果信息
     *
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置日志执行结果信息
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 通过任务设置初始化日志对象
     * @param resource 计划任务
     */
    public void setProps(TaskResource resource){
        this.setStartTime(new Date());
        this.setPid(resource.getPid());
        this.setTaskName(resource.getName());
        this.setState(TaskLog.RUNING);
    }
}
