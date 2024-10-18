package com.example.btth4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class AddEditStudentActivity extends AppCompatActivity {
    private EditText editTextName, editTextMSSV, editTextLop, editTextDiem;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_student);

        editTextName = findViewById(R.id.edit_text_name);
        editTextMSSV = findViewById(R.id.edit_text_mssv);
        editTextLop = findViewById(R.id.edit_text_lop);
        editTextDiem = findViewById(R.id.edit_text_diem);
        databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");

        // Kiểm tra Intent để xác định xem đây có phải là sửa sinh viên hay không
        Intent intent = getIntent();
        if (intent.hasExtra("mssv")) {
            editTextMSSV.setText(intent.getStringExtra("mssv"));
            editTextName.setText(intent.getStringExtra("hoten"));
            editTextLop.setText(intent.getStringExtra("lop"));
            editTextDiem.setText(String.valueOf(intent.getDoubleExtra("diem", 0.0)));
        }

        findViewById(R.id.button_save).setOnClickListener(v -> saveStudent());
    }

    private void saveStudent() {
        String hoten = editTextName.getText().toString().trim();
        String mssv = editTextMSSV.getText().toString().trim();
        String lop = editTextLop.getText().toString().trim();
        String diemStr = editTextDiem.getText().toString().trim();

        if (!hoten.isEmpty() && !mssv.isEmpty() && !lop.isEmpty() && !diemStr.isEmpty()) {
            try {
                double diem = Double.parseDouble(diemStr);

                // Kiểm tra MSSV có tồn tại chưa
                databaseReference.child(mssv).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists() && !mssv.equals(intent.getStringExtra("mssv"))) {
                            Toast.makeText(AddEditStudentActivity.this, "MSSV đã tồn tại!", Toast.LENGTH_SHORT).show();
                        } else {
                            Student student = new Student(hoten, mssv, lop, diem);
                            databaseReference.child(mssv).setValue(student);
                            Toast.makeText(AddEditStudentActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(AddEditStudentActivity.this, "Lỗi truy cập cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Điểm trung bình không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}
