package com.jhorje18.freebitcoincaptcha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    //Variables
    WebView vistaWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conectar con vista
        vistaWeb = (WebView) findViewById(R.id.WebVista);

        vistaWeb.loadUrl("https://freebitco.in/?op=home#");
    }
}
