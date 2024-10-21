package com.example.studen_view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth;
    private DatabaseReference studentRef;
    private StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        // Kiểm tra xem người dùng đã đăng nhập chưa
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Nếu chưa đăng nhập, chuyển đến màn hình đăng nhập
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
            return; // Kết thúc phương thức nếu chưa đăng nhập
        }

        // Khởi tạo RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo tham chiếu đến "sinhvien" trong Firebase Realtime Database
        studentRef = FirebaseDatabase.getInstance().getReference().child("sinhvien");

        // Cấu hình FirebaseRecyclerOptions
        FirebaseRecyclerOptions<Student> options = new FirebaseRecyclerOptions.Builder<Student>()
                .setQuery(studentRef, Student.class)
                .build();

        // Thiết lập Adapter
        adapter = new StudentAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening(); // Bắt đầu lắng nghe sự thay đổi từ Firebase
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening(); // Ngừng lắng nghe sự thay đổi
    }
}
