package com.example.ex07_3_intent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class activity_sub extends AppCompatActivity {
 EditText edtaa, edtbb;
 Button btnsendtong, btnsendhieu;
 Intent myintent;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_sub);
         edtaa = findViewById(R.id.edtaa);
         edtbb = findViewById(R.id.edtbb);
       btnsendtong = findViewById(R.id.btnsendtong);
       btnsendhieu = findViewById(R.id.btnsendhieu);
       myintent = getIntent();
       int a = myintent.getIntExtra("soa",0);
       int b = myintent.getIntExtra("sob",0);
       edtaa.setText(a+"");
       edtbb.setText(b+"");
       btnsendtong.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int sum = a + b;
                myintent.putExtra("kq",sum);
                setResult(33,myintent);
                finish();
           }
       });
       btnsendhieu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int sub = a - b;
               myintent.putExtra("kq",sub);
               setResult(34, myintent);
               finish();
           }
       });
    }
}




