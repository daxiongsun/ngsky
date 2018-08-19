package com.ngsky.im.bean;

/**
 * @Description task entity
 * @Author daxiong
 * @Date 8/9/2018 12:17 AM
 **/

public class Task {

    private String name;          // task name
    private String className;     // which classname run task
    private int threadNum;        // thread numbers
    private int createTime;       // create time

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getThreadNum() {
        return threadNum;
    }

    public void setThreadNum(int threadNum) {
        this.threadNum = threadNum;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }
}
