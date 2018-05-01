package com.app.tzahi.lototzahi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView num1;
    TextView num2;
    TextView num3;
    TextView num4;
    TextView num5;
    TextView num6;
    TextView numStrong;

    Button btnRun;



    final Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (TextView)findViewById(R.id.txtNum1);
        num2 = (TextView)findViewById(R.id.txtNum2);
        num3 = (TextView)findViewById(R.id.txtNum3);
        num4 = (TextView)findViewById(R.id.txtNum4);
        num5 = (TextView)findViewById(R.id.txtNum5);
        num6 = (TextView)findViewById(R.id.txtNum6);
        numStrong = (TextView)findViewById(R.id.txtNumStrong);
        btnRun = (Button)findViewById(R.id.btnRun);

        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Random r = new Random();
                num1.setText(Integer.toString(r.nextInt(37)+1));
                num2.setText(Integer.toString(r.nextInt(37)+1));
                num3.setText(Integer.toString(r.nextInt(37)+1));
                num4.setText(Integer.toString(r.nextInt(37)+1));
                num5.setText(Integer.toString(r.nextInt(37)+1));
                num6.setText(Integer.toString(r.nextInt(37)+1));
                numStrong.setText(Integer.toString(r.nextInt(8)+1));

            }
        });

    }
}
