package org.cramest.cremasgelateria;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Date;

public class ActivityGelati extends Activity implements DataHandler,BitmapHandler {

    private SwipeRefreshLayout swipeContainer;

    ListaGelati listaGelati = new ListaGelati();
    CustomList clAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Conn.getInstance(this).isNetworkAvailable()) {

            setContentView(R.layout.activity_gelati_prodotti);
            swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    aggiornaListViewGelati();
                    swipeContainer.setRefreshing(false);
                }
            });
            //mettiCaricamento();
            mostraGelatiDiOggi();
            //togliCaricamento();
        } else {
            setContentView(R.layout.no_conn);
        }



    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    /*private void mettiCaricamento(){
        WebView web = (WebView) findViewById(R.id.WebCaricamento);
        web.setBackgroundColor(Color.TRANSPARENT); //for gif without background
        web.loadUrl("file:///android_asset/gif.html");
    }

    private void togliCaricamento(){
        WebView web = (WebView) findViewById(R.id.WebCaricamento);
        web.setVisibility(View.INVISIBLE);
    }*/

    private void mostraGelatiDiOggi(){
        Conn.getInstance(this).GetDataFromWebsite(this, "getTodayIcecreams", null, "");
    }

    @Override
    public void HandleData(String data) {
        listaGelati = creaGelatiDaData(data);
        if(listaGelati != null) {
            aggiornaListViewGelati();
            System.out.println("Nella lista ci sono " + listaGelati.size() + " gelati");
        }else{
            setContentView(R.layout.no_gelati_oggi);
        }
    }

    private void aggiornaListViewGelati(){
        ListView lv = (ListView) findViewById(R.id.listViewGelati);
        if(clAdapter == null){
            clAdapter = new CustomList(ActivityGelati.this, listaGelati);
            lv.setAdapter(clAdapter);
            System.out.println("Creato clAdapter");
        }else{
            clAdapter.notifyDataSetChanged();
        }
        int index = lv.getFirstVisiblePosition();

        System.out.println("Aggiornato, index:" + index);
        //lv.smoothScrollToPosition(index);
    }

    public ListaGelati creaGelatiDaData(String data){
        if(!data.equals(null) && !data.equals("") && !data.equals(" ")) {
            ListaGelati Lista = new ListaGelati();
            String[] linee = data.split(";"); //Essendo data una sola riga, dividiamo per un simbolo cosi da avere piu gelati
            for (int i = 0; i < linee.length; i++) {
                String[] pezzi = linee[i].split("@");   //Es."Amarena_www.amarena.com/immagine.png"
                if (!pezzi[1].equals("§")) {
                    GestoreImmagini gestoreImmagini = new GestoreImmagini();
                    gestoreImmagini.getBitmapFromURL(this, pezzi[1], i);
                    Lista.aggGelato(new Gelato(pezzi[0]));
                } else {
                    Lista.aggGelato(new Gelato(pezzi[0]));
                }
            }
            return Lista;
        }else{
            return null;
        }
    }

    @Override
    public void HandleBitmap(Bitmap image,int posizione) {
        GestoreImmagini gestoreImmagini = new GestoreImmagini();
        //Bitmap immagineRitagliata = gestoreImmagini.getResizedBitmap(image,image.getWidth(),image.getWidth());
        listaGelati.getGelato(posizione).setImmagine(image);
        aggiornaListViewGelati();
    }
}
