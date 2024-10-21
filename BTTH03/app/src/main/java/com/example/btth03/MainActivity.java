package com.example.btth03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.btth03.adapter.StudentAdapter;
import com.example.btth03.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList;
    private Button btnAddStudent;  // Nút thêm sinh viên

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         btnAddStudent = findViewById(R.id.btnAddStudent);

         studentList = new ArrayList<>();
        studentList.add(new Student("B27DCCN100", "Mai", "Thị", "Hoa", "Nữ", "10/10/2009", "hoamai@gmail.com", "Nghệ An", "CNTT", 3.25));

         studentAdapter = new StudentAdapter(studentList, student -> {
             Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("student", student); // Truyền dữ liệu qua Intent
            startActivity(intent);
        });

        recyclerView.setAdapter(studentAdapter);

         btnAddStudent.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddStudentActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Student newStudent = (Student) data.getSerializableExtra("newStudent");
            studentList.add(newStudent);  // Thêm sinh viên vào danh sách
            studentAdapter.notifyDataSetChanged();  // Cập nhật lại RecyclerView
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);  // Liên kết menu với Toolbar
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                 return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                 studentAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
