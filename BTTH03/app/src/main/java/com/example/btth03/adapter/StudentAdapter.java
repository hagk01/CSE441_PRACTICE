package com.example.btth03.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Filter;
import android.widget.Filterable;
import com.example.btth03.R;
import com.example.btth03.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> implements Filterable {
    private List<Student> studentList;
    private List<Student> studentListFull;  // Danh sách đầy đủ để thực hiện lọc
    private OnItemClickListener listener;

    public StudentAdapter(List<Student> studentList, OnItemClickListener listener) {
        this.studentList = studentList;
        this.studentListFull = new ArrayList<>(studentList);  // Sao lưu danh sách đầy đủ
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvFullName.setText(student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
        holder.tvGPA.setText("GPA: " + student.getGpa());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(student));
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // Implement giao diện Filterable
    @Override
    public Filter getFilter() {
        return studentFilter;
    }

    private Filter studentFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Student> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(studentListFull);  // Nếu không có từ khóa, hiển thị danh sách đầy đủ
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Student student : studentListFull) {
                    // Lọc theo tên hoặc mã sinh viên
                    if (student.getFirstName().toLowerCase().contains(filterPattern)
                            || student.getLastName().toLowerCase().contains(filterPattern)
                            || student.getId().toLowerCase().contains(filterPattern)) {
                        filteredList.add(student);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            studentList.clear();
            studentList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        private TextView tvFullName, tvGPA;
        private ImageView imgStudent;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFullName = itemView.findViewById(R.id.tvFullName);
            tvGPA = itemView.findViewById(R.id.tvGPA);
            imgStudent = itemView.findViewById(R.id.imgStudent);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Student student);
    }
}
