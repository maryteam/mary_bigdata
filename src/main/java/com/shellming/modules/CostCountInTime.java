package com.shellming.modules;

import com.google.gson.Gson;

/**
 * Created by ruluo1992 on 1/11/2016.
 */
public class CostCountInTime {
    private String cName;
    private Integer count;      // 支付次数
    private Integer hour;       // 发生支付的时间，24小时制
    private Long time;

    public CostCountInTime(String cName, Integer count, Integer hour, Long time) {
        this.cName = cName;
        this.count = count;
        this.hour = hour;
        this.time = time;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
