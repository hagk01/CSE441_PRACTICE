package com.example.btth4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {

    private EditText editTextName, editTextMSSV, editTextClass, editTextScore;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_student);

        // Khởi tạo các thành phần giao diện
        editTextName = findViewById(R.id.edit_text_name);
        editTextMSSV = findViewById(R.id.edit_text_mssv);
        editTextClass = findViewById(R.id.edit_text_class);
        editTextScore = findViewById(R.id.edit_text_score);
        btnSave = findViewById(R.id.btn_save);

        // Xử lý sự kiện nhấn nút "Lưu"
        btnSave.setOnClickListener(v -> addStudent());
    }

    private void addStudent() {
        // Lấy thông tin từ các EditText
        String name = editTextName.getText().toString().trim();
        String mssv = editTextMSSV.getText().toString().trim();
        String className = editTextClass.getText().toString().trim();
        String scoreStr = editTextScore.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (name.isEmpty() || mssv.isEmpty() || className.isEmpty() || scoreStr.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chuyển đổi điểm từ String sang double
        double score = Double.parseDouble(scoreStr);

        // Lưu thông tin sinh viên vào Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien").child(mssv);
        Student student = new Student(name, mssv, className, score);
        databaseReference.setValue(student)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thành công!", Toast.LENGTH_SHORT).show();
                        finish(); // Quay lại MainActivity
                    } else {
                        Toast.makeText(AddStudentActivity.this, "Thêm sinh viên thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

