package com.example.parsexmljsonprep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button px,pj;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        px=(Button)findViewById(R.id.bpx);
        px.setOnClickListener(this);

        pj=(Button)findViewById(R.id.bpj);
        pj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v.equals(pj))
        {
            Intent it=new Intent(this,ViewActivity.class);
            it.putExtra("mode",1);
            startActivity(it);
        }
        if(v.equals(px))
        {
            Intent it=new Intent(this,ViewActivity.class);
            it.putExtra("mode",2);
            startActivity(it);
        }
    }
}