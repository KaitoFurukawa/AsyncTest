package com.kaito.kaitopractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Date;
import java.text.*;
import android.widget.TextView;
import java.text.DateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 現在日時の取得
        Date now = new Date(System.currentTimeMillis());

        // 日時のフォーマットオブジェクト作成
        DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH時mm分");

        // フォーマット
        String nowText = formatter.format(now);

        // 表示
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(nowText);
    }

}
