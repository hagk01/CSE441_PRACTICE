package com.example.ex_20;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
    Activity contextCha;

    public MyAsyncTask(Activity ctx) {
        contextCha = ctx;
    }

    @Override
    protected void onPreExecute() {
        // Hàm này được gọi trước khi thực hiện tác vụ chính
        Toast.makeText(contextCha, "onPreExecute!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        // Xử lý chính trong nền
        for (int i = 0; i <= 100; i++) {
            SystemClock.sleep(100);  // Ngủ 100ms giữa mỗi bước
            publishProgress(i);  // Cập nhật tiến trình
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // Hàm này được gọi khi publishProgress được gọi
        ProgressBar paCha = contextCha.findViewById(R.id.progressBar1);
        TextView txtmsg = contextCha.findViewById(R.id.textView1);

        paCha.setProgress(values[0]);
        txtmsg.setText(values[0] + "%");
    }

    @Override
    protected void onPostExecute(Void result) {
        // Hàm này được gọi sau khi tác vụ hoàn thành
        Toast.makeText(contextCha, "Update xong rồi đó!", Toast.LENGTH_LONG).show();
    }
}
