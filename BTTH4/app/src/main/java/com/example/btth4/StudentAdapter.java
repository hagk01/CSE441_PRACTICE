package com.example.btth4;

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
import com.google.firebase.database.FirebaseDatabase;


public class StudentAdapter extends FirebaseRecyclerAdapter<Student, StudentAdapter.StudentViewHolder> {

    public StudentAdapter(@NonNull FirebaseRecyclerOptions<Student> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StudentViewHolder holder, int position, @NonNull Student model) {
        holder.textStudentName.setText(model.getHoten());
        holder.textStudentMSSV.setText(model.getMssv());
        holder.textStudentLop.setText(model.getLop());
        holder.textStudentDiem.setText(String.valueOf(model.getDiem()));

        holder.buttonEditStudent.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), AddEditStudentActivity.class);
            intent.putExtra("mssv", model.getMssv());
            intent.putExtra("hoten", model.getHoten());
            intent.putExtra("lop", model.getLop());
            intent.putExtra("diem", model.getDiem());
            holder.itemView.getContext().startActivity(intent);
        });

        holder.buttonDeleteStudent.setOnClickListener(v -> {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("sinhvien");
            databaseReference.child(model.getMssv()).removeValue();
        });
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(view);
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView textStudentName, textStudentMSSV, textStudentLop, textStudentDiem;
        Button buttonEditStudent, buttonDeleteStudent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            textStudentName = itemView.findViewById(R.id.text_student_name);
            textStudentMSSV = itemView.findViewById(R.id.text_student_mssv);
            textStudentLop = itemView.findViewById(R.id.text_student_lop);
            textStudentDiem = itemView.findViewById(R.id.text_student_diem);
            buttonEditStudent = itemView.findViewById(R.id.button_edit_student);
            buttonDeleteStudent = itemView.findViewById(R.id.button_delete_student);
        }
    }
}

