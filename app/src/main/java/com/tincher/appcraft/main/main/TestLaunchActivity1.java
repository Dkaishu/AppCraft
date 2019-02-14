package com.tincher.appcraft.main.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tincher.appcraft.R;

/**
 * Created by dks on 2017/11/21.
 */

public class TestLaunchActivity1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        TextView textView = findViewById(R.id.tv_example);
        textView.setText("single task 1");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(TestLaunchActivity1.this,TestLaunchActivity2.class));
                startActivity(new Intent(TestLaunchActivity1.this, ModifyIdentifyTest.class));
            }
        });

    }

}
