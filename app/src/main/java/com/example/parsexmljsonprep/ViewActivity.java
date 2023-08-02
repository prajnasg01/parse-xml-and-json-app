package com.example.parsexmljsonprep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ViewActivity extends AppCompatActivity
{
    TextView xd, jd;
    int mode = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        xd = (TextView) findViewById(R.id.txd);
        jd = (TextView) findViewById(R.id.tjd);

        mode = getIntent().getIntExtra("mode", 0);
        if (mode == 1) {
            parseJson();
        }
        if (mode == 2) {
            parseXml();
        }
    }
    private void parseJson()
    {
        try {
            InputStream ins = getAssets().open("input.json");
            byte[] data = new byte[ins.available()];
            ins.read(data);

            String readdata = new String(data);
            JSONObject jo = new JSONObject(readdata);
            JSONObject jo1 = jo.getJSONObject("employee");
            jd.setText("City Name:" + jo1.getString("city_name") + "\n");
            jd.append("Latitude:" + jo1.getString("Latitude") + "\n");
            jd.append("Longitude" + jo1.getString("Longitude") + "\n");
            jd.append("Temperature:" + jo1.getInt("Temperature") + "\n");
            jd.append("Humidity" + jo1.getString("Humidity") + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String parseXml()
    {
        try
        {
            InputStream is = getAssets().open("input.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document d = db.parse(is);

            Element e = d.getDocumentElement();
            e.normalize();

            NodeList nl = d.getElementsByTagName("employee");

            for (int i = 0; i < nl.getLength(); i++) {
                Node n = nl.item(i);
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e2 = (Element) n;
                    xd.setText("City Name:" + getValue("city_name", e2) + "\n");
                    xd.append("Latitude : " + getValue("Latitude", e2) + "\n");
                    xd.append("Longitude : " + getValue("Longitude", e2) + "\n");
                    xd.append("Temperature : " + getValue("Temperature", e2) + "\n");
                    xd.append("Humidity : " + getValue("Humidity", e2) + "\n");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private String getValue(String tag, Element e)
    {
        NodeList nl = e.getElementsByTagName(tag).item(0).getChildNodes();
        Node n=nl.item(0);
        return n.getNodeValue();
    }
}




