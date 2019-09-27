@[toc](MVC文章目录)

## 引入
**CSDN文章地址**    [https://github.com/NorthernBrain/MVC](https://blog.csdn.net/qq_40881680/article/details/101571000)

MVC全名是Model View Controller，是模型(model)－视图(view)－控制器(controller)的缩写，一种软件设计典范，用一种业务逻辑、数据、界面显示分离的方法组织代码，将业务逻辑聚集到一个部件里面，在改进和个性化定制界面及用户交互的同时，不需要重新编写业务逻辑。MVC被独特的发展起来用于映射传统的输入、处理和输出功能在一个逻辑的图形化用户界面的结构中。

----
## 原理
控制器（Controller）- 负责转发请求，对请求进行处理。
视图（View） - 界面设计人员进行图形界面设计。
模型（Model） - 程序员编写程序应有的功能（实现算法等等）、数据库专家进行数据管理和数据库设计(可以实现具体的功能)。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190927235548329.png)
## 我的理解
在安卓中，MVC架构模式，当用户点击屏幕有数据交互的时候（比如说，请求网络、解析操作等），此时，首先会调用控制器（Controller）中的方法，让它负责转发到模型（Model），让模型中的功能或者是算法执行，之后模型（Model）执行完毕，此时控制器（Controller）就要调用实现的接口，把数据返回给视图（View）。看看我写的demo例子吧！

## 我的Demo
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190928000436771.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQwODgxNjgw,size_12,color_FFFFFF,t_10)
我首先写的是一个Bean类，也就是模拟的数据，之后就要实现可以更新数据的这一层（模型），所以呢，在这一层写入了增加和删除，以及查找方法。之后就是控制器，控制器的职责上述也说了，负责转发请求，对用户的请求进行处理，里面写入了调用模型（Model）的方法，并且实现了回调接口（方便View更新数据），如果模型层，执行完毕之后，紧接着就是控制器调用接口通知给用户，然后View完成更新操作。

----
### 第一步Bean
````java
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

````


----
### 第二步Modle
````java
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
````
----
### 第三步Controller
````java
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

````
----
### 第四步完成View
````java
package com.lkdot.mvc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lkdot.mvc.controller.PeopleControler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private Button add;
    private Button reduce;

    private PeopleControler peopleControler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        peopleControler = new PeopleControler();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        text.setText("null");
        add = (Button) findViewById(R.id.add);
        reduce = (Button) findViewById(R.id.reduce);

        add.setOnClickListener(this);
        reduce.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add:
                peopleControler.addNumber(new PeopleControler.addListener(){
                    @Override
                    public void addOK(String str) {
                        text.setText(str);
                    }
                });
                break;
            case R.id.reduce:
                peopleControler.reduceNumber(new PeopleControler.reduceListener() {
                    @Override
                    public void reduceOK(String str) {
                        text.setText(str);
                    }
                });
                break;
        }
    }
}

````
**CSDN文章地址**    [https://github.com/NorthernBrain/MVC](https://blog.csdn.net/qq_40881680/article/details/101571000)
