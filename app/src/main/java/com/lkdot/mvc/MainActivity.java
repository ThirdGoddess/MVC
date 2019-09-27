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
