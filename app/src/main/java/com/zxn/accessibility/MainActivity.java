package com.zxn.accessibility;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView tvOpen;
    protected EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_open) {
            PlugAccessibilityService.actionAccessibilitySettings(this);
        }
    }

    private void initView() {
        tvOpen = (TextView) findViewById(R.id.tv_open);
        tvOpen.setOnClickListener(MainActivity.this);
        etInput = (EditText) findViewById(R.id.et_input);
    }
}
