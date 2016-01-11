package com.shellming.modules;

/**
 * Created by ruluo1992 on 1/11/2016.
 */
public class CostCountInTime {
    private String cName;
    private String pName;
    private Integer hour;       // 发生支付的时间，24小时制
    private Long time;

    public CostCountInTime(String cName, String pName, Integer hour, Long time) {
        this.cName = cName;
        this.pName = pName;
        this.hour = hour;
        this.time = time;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
