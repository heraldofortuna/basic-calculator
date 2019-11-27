package app.ejemplo.ingenioteca.calculadorabasica;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    public final int duracion = 3000;
    private MediaPlayer mp_intro; //Declaramos objeto de archivo de música.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },duracion);
    }

    @Override
    protected void onResume() {
        super.onResume(); //Iniciamos la reproducción de la música:
        mp_intro = MediaPlayer.create(SplashScreen.this, R.raw.efecto_intro);
        mp_intro.start();
    }
    @Override
    protected void onPause() {
        super.onPause(); //Si está sonando la música, la detenemos:
        if(mp_intro != null){
            mp_intro.stop();
            mp_intro.release();
            mp_intro = null;
        }
    }
}

