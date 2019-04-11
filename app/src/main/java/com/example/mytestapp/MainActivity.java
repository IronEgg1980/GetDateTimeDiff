package com.example.mytestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private DatePicker datePicker1;
    private DatePicker datePicker2;
    Button button;
    private Calendar calendar1,calendar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textview);
        datePicker2 = findViewById(R.id.datePicker2);
        datePicker1 = findViewById(R.id.datePicker1);
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
        calendar1 = new GregorianCalendar();
        calendar2 = new GregorianCalendar();
        int y = calendar1.get(Calendar.YEAR);
        int m = calendar1.get(Calendar.MONTH);
        int d = calendar1.get(Calendar.DAY_OF_MONTH);
        datePicker1.init(y,m,d, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = new GregorianCalendar();
                calendar1.set(year,monthOfYear,dayOfMonth,
                        calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
            }
        });
        datePicker2.init(y, m, d, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = new GregorianCalendar();
                calendar2.set(year,monthOfYear,dayOfMonth,
                        calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
            }
        });
    }
    private void click(){
        int[] values = GetDateTimeDiff.getValues(calendar1,calendar2);
        if(values == null){
            textView.setText("Error");
            return;
        }
        textView.setText(values[0]+"年"+values[1]+"个月"+values[2]+"天"+values[3]+"小时"+values[4]+"分钟");
    }


}
