package com.example.btth03;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.btth03.model.Student;

public class AddStudentActivity extends AppCompatActivity {
    private EditText etStudentId, etFirstName, etMiddleName, etLastName, etEmail, etMajor;
    private Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

         etStudentId = findViewById(R.id.etStudentId);
        etFirstName = findViewById(R.id.etFirstName);
        etMiddleName = findViewById(R.id.etMiddleName);
        etLastName = findViewById(R.id.etLastName);
        etEmail = findViewById(R.id.etEmail);
        etMajor = findViewById(R.id.etMajor);
        btnAddStudent = findViewById(R.id.btnAddStudent);

         btnAddStudent.setOnClickListener(v -> {
            String id = etStudentId.getText().toString();
            String firstName = etFirstName.getText().toString();
            String middleName = etMiddleName.getText().toString();
            String lastName = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String major = etMajor.getText().toString();

             Student newStudent = new Student(id, firstName, middleName, lastName, "Nữ", "01/01/2000", email, "Hà Nội", major, 4.0);

             Intent intent = new Intent();
            intent.putExtra("newStudent", newStudent);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}
