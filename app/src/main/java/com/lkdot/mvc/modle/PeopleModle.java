package com.lkdot.mvc.modle;

import com.lkdot.mvc.bean.PeopleBean;

public class PeopleModle {

    private static PeopleBean peopleBean = new PeopleBean();

    static {
        peopleBean.setName("NorthernBrain");
        peopleBean.setNumber(1);
    }

    /**
     * 添加数量
     */
    public void addNumber() {
        peopleBean.setNumber(peopleBean.getNumber() + 1);
    }

    /**
     * 减少数量
     */
    public void reduceNumber() {
        peopleBean.setNumber(peopleBean.getNumber() - 1);
    }

    public String selectData(){
        return peopleBean.toString();
    }
}
