package com.shellming.modules;

/**
 * Created by ruluo1992 on 1/11/2016.
 */
public class UserCostInProvince {
    private String pName;  // 省市名
    private String cName;   // 公司名
    private Double cost;    // 用户数目
    private Long time;

    public UserCostInProvince(String pName, String cName, Double cost, Long time) {
        this.pName = pName;
        this.cName = cName;
        this.cost = cost;
        this.time = time;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
