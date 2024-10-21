package com.example.btth03;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.btth03.model.Student;

public class DetailActivity extends AppCompatActivity {
    private TextView tvStudentDetail;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvStudentDetail = findViewById(R.id.tvStudentDetail);

         Student student = (Student) getIntent().getSerializableExtra("student");

         if (student != null) {
            tvStudentDetail.setText("ID: " + student.getId() + "\n"
                    + "Họ và Tên: " + student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName() + "\n"
                    + "Giới tính: " + student.getGender() + "\n"
                    + "Ngày sinh: " + student.getBirthDate() + "\n"
                    + "Email: " + student.getEmail() + "\n"
                    + "Địa chỉ: " + student.getAddress() + "\n"
                    + "Ngành: " + student.getMajor() + "\n"
                    + "GPA: " + student.getGpa());
        }
    }
}
