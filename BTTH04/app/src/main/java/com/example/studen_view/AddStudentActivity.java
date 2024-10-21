package com.example.studen_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etName, etMSSV, etClass, etGrade;
    private Button btnSave, btnDelete;
    private DatabaseReference database;
    private String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_activity);

        etName = findViewById(R.id.etName);
        etMSSV = findViewById(R.id.etMSSV);
        etClass = findViewById(R.id.etClass);
        etGrade = findViewById(R.id.etGrade);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        database = FirebaseDatabase.getInstance().getReference("sinhvien");

        Intent intent = getIntent();
        if (intent.hasExtra("student")) {
            Student student = (Student) intent.getSerializableExtra("student");
            studentId = student.getMssv();

            // Hiển thị thông tin sinh viên để sửa
            etName.setText(student.getHoten());
            etMSSV.setText(student.getMssv());
            etClass.setText(student.getLop());
            etGrade.setText(String.valueOf(student.getDiem()));

            // Ẩn trường MSSV vì MSSV không được sửa
            etMSSV.setEnabled(false);

            // Hiển thị nút Xóa
            btnDelete.setVisibility(View.VISIBLE);
        }

        // Lưu lại thông tin sau khi sửa
        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String mssv = etMSSV.getText().toString();
            String lop = etClass.getText().toString();
            double diem = Double.parseDouble(etGrade.getText().toString());

            if (name.isEmpty() || mssv.isEmpty() || lop.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            Student student = new Student(name, mssv, lop, diem);
            if (studentId != null) {
                database.child(studentId).setValue(student).addOnSuccessListener(aVoid ->
                        Toast.makeText(AddStudentActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                );
            }
        });

        // Xóa sinh viên
        btnDelete.setOnClickListener(v -> {
            if (studentId != null) {
                database.child(studentId).removeValue().addOnSuccessListener(aVoid ->
                        Toast.makeText(AddStudentActivity.this, "Xóa thành công!", Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

}
