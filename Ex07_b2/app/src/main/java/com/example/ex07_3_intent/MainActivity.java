package com.example.ex07_3_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
 EditText edta, edtb, edtkq;
 Button btnrequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         edta = findViewById(R.id.edta);
         edtb = findViewById(R.id.edtb);
         edtkq = findViewById(R.id.edtkq);
         btnrequest = findViewById(R.id.btnrequest);
         btnrequest.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent myintent = new
                         Intent(MainActivity.this, activity_sub.class);
//Lấy dữ liệu a, b
                 int a =
                         Integer.parseInt(edta.getText().toString());
                 int b =
                         Integer.parseInt(edtb.getText().toString());
// Đẩy dữ liệu vào Intent
                 myintent.putExtra("soa",a);
                 myintent.putExtra("sob",b);

                         startActivityForResult(myintent,99);
             }
         });
             }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,
                resultCode, data);
        if (requestCode == 99 && resultCode == 33)
        {
            assert data != null;
            int sum = data.getIntExtra("kq",0);
            edtkq.setText("Tổng 2 số là: "+sum);
        }
        if (requestCode == 99 && resultCode == 34)
        {
            assert data != null;
            int sub = data.getIntExtra("kq",0);
            edtkq.setText("Hiệu 2 số là: "+sub);
        }
    }
    }



