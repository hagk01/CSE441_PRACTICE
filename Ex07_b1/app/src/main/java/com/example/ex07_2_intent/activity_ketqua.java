package com.example.ex07_2_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class activity_ketqua extends AppCompatActivity {
    EditText edtkq;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);

        // Ánh xạ các thành phần giao diện
        edtkq = findViewById(R.id.edtkq);
        btnback = findViewById(R.id.btnback);

        // Nhận Intent và Bundle từ activity trước đó
        Intent yourintent = getIntent();
        Bundle yourbundle = yourintent.getBundleExtra("mybackage");

        if (yourbundle != null) {
            // Lấy giá trị từ bundle
            int a = yourbundle.getInt("soa");
            int b = yourbundle.getInt("sob");

            String kq = "";
            if (a == 0 && b == 0) {
                kq = "Vô số nghiệm";
            } else if (a == 0 && b != 0) {
                kq = "Vô nghiệm";
            } else {
                // Định dạng kết quả với hai chữ số thập phân
                DecimalFormat dcf = new DecimalFormat("0.##");
                kq = dcf.format(-b * 1.0 / a);
            }

            // Hiển thị kết quả lên EditText
            edtkq.setText(kq);
        } else {
            // Xử lý khi không nhận được dữ liệu
            edtkq.setText("Không nhận được dữ liệu");
        }

        // Xử lý sự kiện khi nhấn nút quay lại
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kết thúc activity hiện tại để quay lại activity trước đó
                finish();
            }
        });
    }
}
