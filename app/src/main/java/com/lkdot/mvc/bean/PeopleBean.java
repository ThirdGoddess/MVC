package com.lkdot.mvc.bean;

/**
 * Bean类
 */
public class PeopleBean {
    private String name;
    private int number;

    public PeopleBean() {
    }

    public PeopleBean(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PeopleBean{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
