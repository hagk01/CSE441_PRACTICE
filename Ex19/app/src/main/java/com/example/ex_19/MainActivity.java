package com.example.ex_19;

import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ex_19.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnparse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btnparse);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsexml();
            }
        });
    }

    private void parsexml() {
        try {
            InputStream myinput = getAssets().open("employee.xml");
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(myinput, null);

            int eventType = parser.getEventType();
            String nodeName;
            String datashow = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            datashow = parser.getAttributeValue(null, "id") + "-" + parser.getAttributeValue(null, "title") + "-";
                        } else if (nodeName.equals("name")) {
                            datashow += parser.nextText() + "-";
                        } else if (nodeName.equals("phone")) {
                            datashow += parser.nextText();
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        nodeName = parser.getName();
                        if (nodeName.equals("employee")) {
                            mylist.add(datashow);
                            datashow = "";
                        }
                        break;
                }
                eventType = parser.next();
            }
            myadapter.notifyDataSetChanged();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }
}
