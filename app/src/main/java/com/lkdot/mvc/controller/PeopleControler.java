package com.lkdot.mvc.controller;

import com.lkdot.mvc.modle.PeopleModle;

public class PeopleControler {
    private PeopleModle peopleModle;

    public PeopleControler() {
        peopleModle = new PeopleModle();
    }

    /**
     * 增加数量
     */
    public void addNumber(addListener addListener) {
        peopleModle.addNumber();
        addListener.addOK(peopleModle.selectData());
    }


    /**
     * 减少数量
     */
    public void reduceNumber(reduceListener reduceListener) {
        peopleModle.reduceNumber();
        reduceListener.reduceOK(peopleModle.selectData());
    }

    public interface addListener {
        void addOK(String str);
    }

    public interface reduceListener {
        void reduceOK(String str);
    }

}
