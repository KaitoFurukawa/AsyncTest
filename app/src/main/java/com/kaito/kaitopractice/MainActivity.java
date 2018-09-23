package com.kaito.kaitopractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Date;
import android.widget.Button;
import java.text.*;
import android.widget.TextView;
import java.text.DateFormat;

public class MainActivity extends AppCompatActivity implements ResultListener {

    private TestAsync testTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 現在日時の取得
//        Date now = new Date(System.currentTimeMillis());
//
//        // 日時のフォーマットオブジェクト作成
//        DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");

        // フォーマット
//        String nowText = formatter.format(now);

        Button button1 = (Button) findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                testTask = new TestAsync(MainActivity.this);
                testTask.execute("");
            }

        });

        // 表示
//        TextView textview = (TextView)findViewById(R.id.textView);
//        textView.setText(nowText);
    }

    @Override
    public void onResult(String result) {
        TextView text = (TextView) findViewById(R.id.textView);
        text.setText(result);
    }
}
