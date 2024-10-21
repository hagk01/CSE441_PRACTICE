package com.example.ex_22;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends Activity {
    Button btnparse;
    ListView lv1;
    ArrayAdapter<String> myadapter;
    ArrayList<String> mylist;
    String URL = "https://api.androidhive.info/pizza/?format=xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btnparse);
        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv1.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadExampleTask task = new LoadExampleTask();
                task.execute();
            }
        });
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            ArrayList<String> list = new ArrayList<>();
            try {
                XMLParser parser = new XMLParser();
                String xml = parser.getXmlFromUrl(URL);
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser xpp = fc.newPullParser();
                xpp.setInput(new StringReader(xml));

                int eventType = xpp.getEventType();
                String nodeName;
                String datashow = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            nodeName = xpp.getName();
                            if (nodeName.equals("id")) {
                                datashow = xpp.nextText() + " - ";
                            } else if (nodeName.equals("name")) {
                                datashow += xpp.nextText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = xpp.getName();
                            if (nodeName.equals("item")) {
                                list.add(datashow);
                            }
                            break;
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            myadapter.clear();
            myadapter.addAll(result);
        }
    }
}
