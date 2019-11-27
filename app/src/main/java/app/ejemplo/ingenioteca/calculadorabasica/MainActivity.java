package app.ejemplo.ingenioteca.calculadorabasica;

import android.media.AudioManager;
import android.media.SoundPool;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

//Basado en un código encontrado en : https://www.youtube.com/watch?v=R7Q8tp3ut5Y

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Variables para efectos de sonido:
    SoundPool sp_1, sp_2, sp_del, sp_ac, sp_igual, sp_error;
    int efecto_1, efecto_2, efecto_del, efecto_ac, efecto_igual, efecto_error;
    private AdView ad_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Método para inicializar el aviso publicitario:
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        //Método para crear el aviso publicitario:
        ad_banner = findViewById(R.id.ad_banner);
        AdRequest adRequest = new AdRequest.Builder().build();
        ad_banner.loadAd(adRequest);

        //Creamos y asignamos las variables:
        Button b_cero = (Button)findViewById(R.id.b_cero);
        b_cero.setOnClickListener(this); //Cuando el usuario da click.
        Button b_uno = (Button)findViewById(R.id.b_uno);
        b_uno.setOnClickListener(this); //Cuando el usuario da click.
        Button b_dos = (Button)findViewById(R.id.b_dos);
        b_dos.setOnClickListener(this); //Cuando el usuario da click.
        Button b_tres = (Button)findViewById(R.id.b_tres);
        b_tres.setOnClickListener(this); //Cuando el usuario da click.
        Button b_cuatro = (Button)findViewById(R.id.b_cuatro);
        b_cuatro.setOnClickListener(this); //Cuando el usuario da click.
        Button b_cinco = (Button)findViewById(R.id.b_cinco);
        b_cinco.setOnClickListener(this); //Cuando el usuario da click.
        Button b_seis = (Button)findViewById(R.id.b_seis);
        b_seis.setOnClickListener(this); //Cuando el usuario da click.
        Button b_siete = (Button)findViewById(R.id.b_siete);
        b_siete.setOnClickListener(this); //Cuando el usuario da click.
        Button b_ocho = (Button)findViewById(R.id.b_ocho);
        b_ocho.setOnClickListener(this); //Cuando el usuario da click.
        Button b_nueve = (Button)findViewById(R.id.b_nueve);
        b_nueve.setOnClickListener(this); //Cuando el usuario da click.
        Button b_doblecero = (Button)findViewById(R.id.b_doblecero);
        b_doblecero.setOnClickListener(this); //Cuando el usuario da click.
        Button b_punto = (Button)findViewById(R.id.b_punto);
        b_punto.setOnClickListener(this); //Cuando el usuario da click.
        Button b_suma = (Button)findViewById(R.id.b_suma);
        b_suma.setOnClickListener(this); //Cuando el usuario da click.
        Button b_resta = (Button)findViewById(R.id.b_resta);
        b_resta.setOnClickListener(this); //Cuando el usuario da click.
        Button b_multiplicacion = (Button)findViewById(R.id.b_multiplicacion);
        b_multiplicacion.setOnClickListener(this); //Cuando el usuario da click.
        Button b_division = (Button)findViewById(R.id.b_division);
        b_division.setOnClickListener(this); //Cuando el usuario da click.
        Button b_delete = (Button)findViewById(R.id.b_delete);
        b_delete.setOnClickListener(this); //Cuando el usuario da click.
        Button b_ac = (Button)findViewById(R.id.b_ac);
        b_ac.setOnClickListener(this); //Cuando el usuario da click.
        Button b_igual = (Button)findViewById(R.id.b_igual);
        b_igual.setOnClickListener(this); //Cuando el usuario da click.

        //Asignaciones y creaciones para efectos de sonido:
        sp_1 = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        efecto_1 = sp_1.load(this,R.raw.efecto_1,1);
        sp_2 = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        efecto_2 = sp_2.load(this,R.raw.efecto_2,1);
        sp_del = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        efecto_del = sp_del.load(this,R.raw.efecto_del,1);
        sp_ac = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        efecto_ac = sp_ac.load(this,R.raw.efecto_ac,1);
        sp_igual = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        efecto_igual = sp_igual.load(this,R.raw.efecto_igual,1);
        sp_error = new SoundPool(1,AudioManager.STREAM_MUSIC,1);
        efecto_error = sp_error.load(this,R.raw.efecto_error,1);

    }

    //Inicializamos los botones en falso:
    boolean suma = false;
    boolean resta = false;
    boolean multiplicacion = false;
    boolean division = false;
    boolean igual = false;
    boolean decimal = false;
    boolean error = false;

    Double aux;
    Double aux1;
    Double acumulador = 0.0;
    Double resultado;

    @Override
    public void onClick(View v) {
        TextView pantalla_1 = (TextView)findViewById(R.id.tv_respuesta_1);
        TextView pantalla_2 = (TextView)findViewById(R.id.tv_respuesta_2);

        int seleccion = v.getId(); //Se guardara el Id del boton que se haya presionado.
        String a = pantalla_2.getText().toString(); //Se guardará los números que el usuario vaya digitando en pantalla.

        //Implementamos un Try-catch para validar un error (para que no se cierre):
        try{
            switch (seleccion){
                case R.id.b_cero:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("0");
                    }
                    else{
                        pantalla_2.setText(a+"0");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_uno:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("1");
                    }
                    else{
                        pantalla_2.setText(a+"1");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_dos:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("2");
                    }
                    else{
                        pantalla_2.setText(a+"2");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_tres:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("3");
                    }
                    else{
                        pantalla_2.setText(a+"3");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_cuatro:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("4");
                    }
                    else{
                        pantalla_2.setText(a+"4");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_cinco:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("5");
                    }
                    else{
                        pantalla_2.setText(a+"5");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_seis:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("6");
                    }
                    else{
                        pantalla_2.setText(a+"6");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_siete:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("7");
                    }
                    else{
                        pantalla_2.setText(a+"7");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_ocho:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("8");
                    }
                    else{
                        pantalla_2.setText(a+"8");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_nueve:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("9");
                    }
                    else{
                        pantalla_2.setText(a+"9");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_doblecero:
                    if(igual == true || error == true){ //Esto es para que luego de usar el botón de igual, al dar click a cada númrero se reinicie la operación.
                        pantalla_1.setText("");
                        pantalla_2.setText("00");
                    }
                    else{
                        pantalla_2.setText(a+"00");
                    }
                    igual = false;
                    sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    break;
                case R.id.b_punto:
                    if(decimal == false && igual == false){
                        pantalla_2.setText(a+".");
                        sp_1.play(efecto_1,1,1,1,0,1); //Submétodo para reproducir el efecto.
                        decimal = true;
                    } else{
                        return;
                    }
                    break;
                case R.id.b_suma:
                    sp_2.play(efecto_2,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    suma = true;
                    igual = false;
                    if(resta == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador - aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " + ");
                        resta = false;
                    }
                    else if(multiplicacion == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador * aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " + ");
                        multiplicacion = false;
                    }
                    else if(division == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador / aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " + ");
                        division = false;
                    }
                    else if(a == ""){
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " + ");
                    }
                    else{
                        aux = Double.parseDouble(a);
                        acumulador = acumulador + aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " + ");
                    }
                    break;
                case R.id.b_resta:
                    sp_2.play(efecto_2,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    resta = true;
                    igual = false;
                    if(suma == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador + aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " - ");
                        suma = false;
                    }
                    else if(multiplicacion == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador * aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " - ");
                        multiplicacion = false;
                    }
                    else if(division == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador / aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " - ");
                        division = false;
                    }
                    else if(a == ""){
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " - ");
                    }
                    else{
                        aux = Double.parseDouble(a);
                        if(acumulador == 0.0){
                            acumulador = aux;
                        } else{
                            acumulador = acumulador - aux;
                        }
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " - ");
                    }
                    break;
                case R.id.b_multiplicacion:
                    sp_2.play(efecto_2,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    multiplicacion = true;
                    igual = false;
                    if(suma == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador * aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " x ");
                        suma = false;
                    }
                    else if(resta == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador * aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " x ");
                        resta = false;
                    }
                    else if(division == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador * aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " x ");
                        division = false;
                    }
                    else if(a == ""){
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " x ");
                    }
                    else{
                        aux = Double.parseDouble(a);
                        if(acumulador == 0.0){
                            acumulador = aux;
                        } else{
                            acumulador = acumulador * aux;
                        }
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " x ");
                    }
                    break;
                case R.id.b_division:
                    sp_2.play(efecto_2,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    division = true;
                    igual = false;
                    if(suma == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador + aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " / ");
                        suma = false;
                    }
                    else if(resta == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador - aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " / ");
                        resta = false;
                    }
                    else if(multiplicacion == true){
                        aux = Double.parseDouble(a);
                        acumulador = acumulador * aux;
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " / ");
                        multiplicacion = false;
                    }
                    else if(a == ""){
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " / ");
                    }
                    else{
                        aux = Double.parseDouble(a);
                        if(acumulador == 0.0){
                            acumulador = aux;
                        } else{
                            acumulador = acumulador / aux;
                        }
                        pantalla_2.setText("");
                        pantalla_1.setText(acumulador + " / ");
                    }
                    break;
                case R.id.b_igual:
                    igual = true;
                    aux1 = Double.parseDouble(a);
                    if(suma){
                        sp_igual.play(efecto_igual,1,1,1,0,1); //Submétodo para reproducir el efecto.
                        resultado = acumulador + aux1;
                        pantalla_2.setText(""+resultado);
                        pantalla_1.setText(acumulador + " + "+aux1);
                        suma = false;
                        acumulador = 0.0;
                    }
                    else if(resta){
                        sp_igual.play(efecto_igual,1,1,1,0,1); //Submétodo para reproducir el efecto.
                        resultado = acumulador - aux1;
                        pantalla_2.setText(""+resultado);
                        pantalla_1.setText(acumulador + " - "+aux1);
                        resta = false;
                        acumulador = 0.0;
                    }
                    else if(multiplicacion){
                        sp_igual.play(efecto_igual,1,1,1,0,1); //Submétodo para reproducir el efecto.
                        resultado = acumulador * aux1;
                        if(resultado == -0.0){ //Caso especial cuando se multiplica por 0. Esto evita el error de que salga "-0.0".
                            resultado = 0.0;
                            pantalla_2.setText(""+resultado);
                            pantalla_1.setText(acumulador + " x "+aux1);
                        }
                        else{
                            pantalla_2.setText(""+resultado);
                            pantalla_1.setText(acumulador + " x "+aux1);
                        }
                        multiplicacion = false;
                        acumulador = 0.0;
                    }
                    else if(division){
                        if(aux1 != 0.0){
                            sp_igual.play(efecto_igual,1,1,1,0,1); //Submétodo para reproducir el efecto.
                            resultado = acumulador / aux1;
                            pantalla_2.setText(""+resultado);
                            pantalla_1.setText(acumulador + " / "+aux1);
                            division = false;
                            acumulador = 0.0;
                        } else{
                            sp_error.play(efecto_error,1,1,1,0,1); //Submétodo para reproducir el efecto.
                            pantalla_2.setText("");
                            pantalla_1.setText("No se puede dividir entre 0");
                            return;
                        }
                    }
                    decimal = false;
                    break;
                case R.id.b_delete:
                    if(igual == false){ //Solo se activa para numeros que ingresamos, mas no para resultados que se obtengan.
                        sp_del.play(efecto_del,1,1,1,0,1);//Submétodo para reproducir el efecto.
                        if(!pantalla_2.getText().toString().isEmpty()){
                            String valor = pantalla_2.getText().toString();
                            if(valor.length() >= 0){
                                valor = valor.substring(0,valor.length()-1);
                                pantalla_2.setText(valor);
                            }
                        }
                    }
                    else{
                        sp_error.play(efecto_error,1,1,1,0,1); //Submétodo para reproducir el efecto.
                        return;
                    }
                    break;
                case R.id.b_ac:
                    sp_ac.play(efecto_ac,1,1,1,0,1); //Submétodo para reproducir el efecto.
                    pantalla_1.setText("");
                    pantalla_2.setText("");
                    suma = false;
                    resta = false;
                    multiplicacion = false;
                    division = false;
                    acumulador = 0.0;
                    decimal = false;
                    break;

            }
            error = false;
        }
        catch(Exception e) {
            sp_error.play(efecto_error,1,1,1,0,1); //Submétodo para reproducir el efecto.
            pantalla_1.setText("ERROR");
            pantalla_2.setText("");
            error = true;
            division = false;
            multiplicacion = false;
            resta = false;
            suma = false;
            acumulador = 0.0;
            decimal = false;
            //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
