package com.example.dang_ky_thong_tin_ca_nhan;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtten, edtcmnd, edtbosung;
    CheckBox chkdocbao, chkcode, chkdocsach;
    Button btnsend;
    RadioGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view từ layout
        edtten = findViewById(R.id.edtten);
        edtcmnd = findViewById(R.id.edtcmnd);
        edtbosung = findViewById(R.id.edtbosung);
        chkdocbao = findViewById(R.id.chkdocbao);
        chkcode = findViewById(R.id.chkcode);
        chkdocsach = findViewById(R.id.chkdocsach);
        btnsend = findViewById(R.id.btnsend);
        group = findViewById(R.id.group);

        // Xử lý sự kiện khi nhấn nút "Gửi"
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doShowInformation();
            }
        });
    }

    public void doShowInformation() {
        // Kiểm tra tên hợp lệ
        String ten = edtten.getText().toString().trim();
        if (ten.length() < 3) {
            edtten.requestFocus();
            edtten.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra CMND hợp lệ
        String cmnd = edtcmnd.getText().toString().trim();
        if (cmnd.length() != 9) {
            edtcmnd.requestFocus();
            edtcmnd.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }

        // Kiểm tra bằng cấp
        String bang = "";
        int id = group.getCheckedRadioButtonId();
        if (id == -1) {
            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad = findViewById(id);
        bang = rad.getText().toString();

        // Kiểm tra sở thích
        String sothich = "";
        if (chkdocbao.isChecked()) sothich += chkdocbao.getText() + "\n";
        if (chkdocsach.isChecked()) sothich += chkdocsach.getText() + "\n";
        if (chkcode.isChecked()) sothich += chkcode.getText() + "\n";

        String bosung = edtbosung.getText().toString();

        // Tạo nội dung thông báo
        String msg = "Tên: " + ten + "\n";
        msg += "CMND: " + cmnd + "\n";
        msg += "Bằng cấp: " + bang + "\n";
        msg += "Sở thích:\n" + sothich;
        msg += "—————————–\n";
        msg += "Thông tin bổ sung:\n" + bosung + "\n";
        msg += "—————————–";

        // Tạo và hiển thị AlertDialog
        AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
        mydialog.setTitle("THÔNG TIN CÁ NHÂN");
        mydialog.setMessage(msg);
        mydialog.setPositiveButton("ĐÓNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        mydialog.create().show();
    }
}
