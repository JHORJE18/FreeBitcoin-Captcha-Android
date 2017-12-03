package com.jhorje18.freebitcoincaptcha;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Variables
    WebView vistaWeb;
    TextView txtTiempo;
    Button btnIniciar, btnWeb, btnCancelar;
    private CountDownTimer countDownTimer;
    int tiempo = 3600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Conectar con vista
        vistaWeb = (WebView) findViewById(R.id.WebVista);
        txtTiempo = (TextView) findViewById(R.id.txtTiempo);
        btnIniciar = (Button) findViewById(R.id.btnIniciar);
        btnWeb = (Button) findViewById(R.id.btnWeb);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);

        vistaWeb.loadUrl("https://freebitco.in/?op=home#");
    }

    //Eventos botones
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnIniciar:
                iniciar();
                break;
            case R.id.btnCancelar:
                cancelar();
                break;
        }
    }

    //Bara progreso

    //Iniciar cuenta
    private void iniciar(){
        txtTiempo.setText("00:00");

        countDownTimer = new CountDownTimer(tiempo * 1000, 1000) {
            @Override
            public void onTick(long l) {
                int segundos = (int) (l / 1000) % 60;
                int minutos = (int) ((l / (1000*60)) % 60);
                txtTiempo.setText("" + minutos + ":" + segundos);
            }

            @Override
            public void onFinish() {
                txtTiempo.setText("Finalizado!");
            }
        };

        countDownTimer.start();
    }

    //Detener cuenta
    private void cancelar() {
        if (countDownTimer != null){
            countDownTimer.cancel();
            countDownTimer = null;
            txtTiempo.setText("00:00");
        }
    }
}
