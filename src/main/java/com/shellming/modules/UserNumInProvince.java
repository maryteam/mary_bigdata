package com.shellming.modules;

/**
 * Created by ruluo1992 on 1/11/2016.
 */
public class UserNumInProvince {
    private String pName;  // 省市名
    private String cName;   // 公司名
    private Integer num;    // 用户数目
    private Long time;

    public UserNumInProvince(String pName, String cName, Integer num, Long time) {
        this.pName = pName;
        this.cName = cName;
        this.num = num;
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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
