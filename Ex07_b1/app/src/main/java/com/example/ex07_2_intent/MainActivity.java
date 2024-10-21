package com.example.ex07_2_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb;
    Button btnkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btnkq = findViewById(R.id.btnkq);

        // Xử lý sự kiện khi nhấn nút btnkq
        btnkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra xem các trường EditText có trống không
                if (edta.getText().toString().isEmpty() || edtb.getText().toString().isEmpty()) {
                    edta.setError("Vui lòng nhập giá trị cho a");
                    edtb.setError("Vui lòng nhập giá trị cho b");
                    return;
                }

                try {
                    // Chuyển đổi giá trị từ EditText sang số nguyên
                    int a = Integer.parseInt(edta.getText().toString());
                    int b = Integer.parseInt(edtb.getText().toString());

                    // Tạo Intent và Bundle để truyền dữ liệu
                    Intent myintent = new Intent(MainActivity.this, activity_ketqua.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putInt("soa", a);
                    bundle1.putInt("sob", b);
                    myintent.putExtra("mybackage", bundle1);

                    // Chuyển sang activity_ketqua
                    startActivity(myintent);
                } catch (NumberFormatException e) {
                    edta.setError("Giá trị không hợp lệ");
                    edtb.setError("Giá trị không hợp lệ");
                }
            }
        });
    }
}
