package com.example.ph20617_mob201_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ph20617_mob201_assignment.Adapter.Adapter_news;
import com.example.ph20617_mob201_assignment.DTO.News;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class News1 extends AppCompatActivity {
    List<News> list;
    RecyclerView rcy;
    Adapter_news adapter;
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news1);

        edt = findViewById(R.id.edt_link);
        rcy = findViewById(R.id.rcy);
        adapter = new Adapter_news();
        list = new ArrayList<>();
        adapter.setData(list);
        rcy.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rcy.setAdapter(adapter);
        edt.setText("http://rss.weather.com.au/nsw/sydney");
        findViewById(R.id.btnLoad).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StaticFieldLeak")
            @Override
            public void onClick(View v) {
                list.clear();
                new a().execute(edt.getText().toString().trim());
            }
        });
    }
    public class a extends AsyncTask<String, Void, List<News>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @SuppressLint("StaticFieldLeak")
        @Override
        protected List<News> doInBackground(String... strings) {
            try {
                return getData(strings[0]);
            } catch (IOException | XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(List<News> baos) {
            super.onPostExecute(baos);
            adapter.setData(baos);
        }
    }

    public List<News> parseXMLAndStoreIt(XmlPullParser xpp) throws XmlPullParserException {
        int event;
        try {
            event = xpp.getEventType();
            String text = null;
            String name0 = null;
            String name1 = null;
            String name2 = null;
            String name3 = null;
            String name4 = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                String name = xpp.getName();
                Log.d("abcd", "parseXMLAndStoreIt: " + name);
                switch (event) {
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (name.equals("title")) {
                            name0 = text;
                        } else if (name.equals("description")) {
                            Document doc = Jsoup.parse(text);
                            doc.getElementsByTag("a").remove();
                            doc.getElementsByTag("img").remove();
                            name1 = doc.getAllElements().text();
                        } else if (name.equals("pubDate")) {
                            name2 = text;
                        } else if (name.equals("link")) {
                            name3 = text;

                        } else if (name.equals("image")) {
                            name4 = text;
                        } else {
                        }
                        if (name0 != null && name1 != null && name2 != null && name3 != null) {
                            News bao = new News(name0, name4, name1, name2, name3);
                            ;
                            list.add(bao);
                            text = null;
                            name0 = null;
                            name1 = null;
                            name2 = null;
                            name3 = null;
                            name4 = null;
                        }
                        break;
                }


                event = xpp.next();
            }


        } catch (Exception e) {
            Log.d("fsssssss", "parseXMLAndStoreIt: " + e.getMessage());
        } finally {

        }
        Log.d("âddas", "parseXMLAndStoreIt: " + list.size());
        return list;
    }
    private List<News> getData(String URL) throws IOException, XmlPullParserException {

        java.net.URL url = new URL(URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(inputStream, "utf-8");
        parseXMLAndStoreIt(xpp);
        inputStream.close();
        return list;
    }
}