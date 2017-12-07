package com.jhorje18.freebitcoincaptcha;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Variables
    WebView vistaWeb;
    TextView txtTiempo;
    Button btnIniciar, btnWeb, btnCancelar;
    ProgressBar barraProgreso;

    private CountDownTimer countDownTimer;
    int tiempo = 60;
    int actual = 0;

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
        barraProgreso = (ProgressBar) findViewById(R.id.progresoTiempo);

        btnCancelar.setEnabled(false);
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
            case R.id.btnWeb:
                recargar(v);
                break;
        }
    }

    private void recargar(View v) {
        vistaWeb.reload();
        Snackbar.make(v,"Recargando Web",Snackbar.LENGTH_LONG).show();
    }

    //Bara progreso
    private void progresoBarra(int restante){
        int estado = (restante * 100) / tiempo;
        actual = restante;

        barraProgreso.setProgress(100 - estado);
        Log.d("#TIME" , "Tiempo establecido a " + estado);
    }

    //Iniciar cuenta
    private void iniciar(){
        txtTiempo.setText("00:00");

        countDownTimer = new CountDownTimer(tiempo * 1000, 1000) {
            @Override
            public void onTick(long l) {
                int segundos = (int) (l / 1000) % 60;
                int minutos = (int) ((l / (1000*60)) % 60);
                String pantalla = "";
                String segundo, minuto = null;

                //Fromato segundos
                //Formato minutos
                if (minutos < 10){
                    pantalla += "0" + minutos;
                } else {
                    pantalla += minutos;
                }
                pantalla += ":";
                if (segundos < 10){
                    pantalla += "0" + segundos;
                } else {
                    pantalla += segundos;
                }

                txtTiempo.setText(pantalla);
                int milisegundos = (int) l;

                progresoBarra(segundos);
            }

            @Override
            public void onFinish() {
                txtTiempo.setText("Fin!");
            }
        };

        btnCancelar.setEnabled(true);
        btnIniciar.setEnabled(false);
        countDownTimer.start();
    }

    //Detener cuenta
    private void cancelar() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
            txtTiempo.setText("00:00");
            barraProgreso.setProgress(0);

            btnCancelar.setEnabled(false);
            btnIniciar.setEnabled(true);
        }
    }
}
