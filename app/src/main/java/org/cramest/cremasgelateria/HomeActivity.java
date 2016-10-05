package org.cramest.cremasgelateria;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        impostaAltezzaElementi();
        impostaOnClick();
        eAperta();
    }

    private void eAperta(){
        TextView testo = (TextView)findViewById(R.id.textView4);
        String giorno = GestoreTempo.getGiornoSett();
        int apreAlleOre = 11;
        int apreAlleMinuti = 30;
        int chiudeAlleOre = 22;     //DA CAMBIARE, COSI NON FUNZIONA DEVO FARE TIPO DATEDIFF, PER ORA VA BENE
        int chiudeAlleMinuti = 30;
        switch (giorno){
            case "Mon":
            case "Tue":
            case "Wed":
            case "Thu":
            case "Fri":
                apreAlleOre=14;
                break;
            case "Sat":
            case "Sun":
                apreAlleOre=11;
                break;
        }
        int ore = Integer.parseInt(GestoreTempo.getHour());
        int minuti = Integer.parseInt(GestoreTempo.getMinutes());
        if(ore > apreAlleOre && ore < chiudeAlleOre){
            testo.setText("Ora aperto");
            testo.setTextColor(getResources().getColor(R.color.ColorGreen));
        }else{
            testo.setText("Chiuso");
            testo.setTextColor(getResources().getColor(R.color.ColorRed));
        }
    }

    private void impostaOnClick(){
        ImageView imgView1 = (ImageView)findViewById(R.id.imageView1);
        ImageView imgView2 = (ImageView)findViewById(R.id.imageView2);
        ImageView imgView3 = (ImageView)findViewById(R.id.imageView3);
        ImageView imgView4 = (ImageView)findViewById(R.id.imageView4);
        imgView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ActivityGelati.class);
                startActivity(intent);
            }
        });
    }

    private void impostaAltezzaElementi(){

        int altezzaSchermo = getAltezzaSchermo();

        int altezzaUno = (int)altezzaSchermo/4;
       //Primo
        ImageView imgView1 = (ImageView)findViewById(R.id.imageView1);
        imgView1.getLayoutParams().height = altezzaUno;
        imgView1.requestLayout();
        ((TextView)findViewById(R.id.textView1)).setHeight(altezzaUno);
        //Secondo
        ImageView imgView2 = (ImageView)findViewById(R.id.imageView2);
        imgView2.getLayoutParams().height = altezzaUno;
        imgView2.requestLayout();
        ((TextView)findViewById(R.id.textView2)).setHeight(altezzaUno);
        //Terzo
        ImageView imgView3 = (ImageView)findViewById(R.id.imageView3);
        imgView3.getLayoutParams().height = altezzaUno; //tanto per compensare se magari c'è qualche pixel vuoto
        imgView3.requestLayout();
        ((TextView)findViewById(R.id.textView3)).setHeight(altezzaUno);
        //Quarto
        ImageView imgView4 = (ImageView)findViewById(R.id.imageView4);
        imgView4.getLayoutParams().height = altezzaUno+3; //tanto per compensare se magari c'è qualche pixel vuoto
        imgView4.requestLayout();
        ((TextView)findViewById(R.id.textView4)).setHeight(altezzaUno);

    }

    private int getAltezzaSchermo(){
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        int altezzaTitolo = ((TextView)findViewById(R.id.textViewTitle)).getLayoutParams().height;
        int pxHeight = outMetrics.heightPixels;
        int statusBarHeight = 0;
        int actionBarHeight = 0;
        int resource = getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resource > 0) {
            statusBarHeight = getApplicationContext().getResources().getDimensionPixelSize(resource);
        }
        int resource2 = getApplicationContext().getResources().getIdentifier("action_bar_height", "dimen", "android");
        if (resource2 > 0) {
            actionBarHeight = getApplicationContext().getResources().getDimensionPixelSize(resource2);
        }
        pxHeight -= actionBarHeight;
        pxHeight -= statusBarHeight;
        return pxHeight-altezzaTitolo;
    }
}
