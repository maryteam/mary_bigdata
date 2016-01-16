package com.shellming.modules;

import com.google.gson.Gson;

/**
 * Created by gssflyaway on 16/1/16.
 */
public class EnterpriseRank {
    private String cName;
    private Integer num;
    private Integer rank;

    public EnterpriseRank(String cName, Integer num, Integer rank){
        this.cName = cName;
        this.num = num;
        this.rank = rank;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String toString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
