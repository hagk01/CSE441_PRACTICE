package com.example.studen_view;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

public class StudentAdapter extends FirebaseRecyclerAdapter<Student, StudentAdapter.StudentViewHolder> {

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull Student model) {
        // Hiển thị thông tin sinh viên
        holder.name.setText(model.getHoten());
        holder.mssv.setText(model.getMssv());
        holder.classRoom.setText(model.getLop());
        holder.grade.setText(String.valueOf(model.getDiem()));

        // Nút Sửa - Khi bấm vào sẽ chuyển dữ liệu sang AddStudentActivity để sửa
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), AddStudentActivity.class);
            intent.putExtra("student", (CharSequence) model); // Truyền đối tượng sinh viên cần sửa
            v.getContext().startActivity(intent);
        });

        // Nút Xóa - Xóa sinh viên khỏi Firebase
        holder.btnDelete.setOnClickListener(v -> {
            // Lấy tham chiếu đến sinh viên tại vị trí hiện tại và xóa
            DatabaseReference ref = getRef(position);
            ref.removeValue();
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView name, mssv, classRoom, grade;
        Button btnEdit, btnDelete;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            mssv = itemView.findViewById(R.id.tvMSSV);
            classRoom = itemView.findViewById(R.id.tvClass);
            grade = itemView.findViewById(R.id.tvGrade);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
