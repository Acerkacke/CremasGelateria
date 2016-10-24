package org.cramest.cremasgelateria;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class GraniteActivity extends Activity implements DataHandler,BitmapHandler{

    ListaGranite listaGranite = new ListaGranite();
    CustomList clAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Conn.getInstance(this).isNetworkAvailable()) {
            setContentView(R.layout.activity_granite);
            mostraGraniteDiOggi();
        } else {
            setContentView(R.layout.no_conn);
        }
    }

    private void mostraGraniteDiOggi(){
        Conn.getInstance(this).GetDataFromWebsite(this, "getGranite", null, "");
    }

    @Override
    public void HandleData(String data) {
        listaGranite = creaGraniteDaData(data);
        if(listaGranite != null) {
            aggiornaListViewGranite();
            System.out.println("Nella lista ci sono " + listaGranite.size() + " gelati");
        }else{
            setContentView(R.layout.no_gelati_oggi);
        }
    }
    private void aggiornaListViewGranite(){
        ListView lv = (ListView) findViewById(R.id.listViewGranite);
        if(clAdapter == null){
            clAdapter = new CustomList(GraniteActivity.this, listaGranite);
            lv.setAdapter(clAdapter);
            System.out.println("Creato clAdapter");
        }else{
            clAdapter.notifyDataSetChanged();
        }
        int index = lv.getFirstVisiblePosition();

        System.out.println("Aggiornato, index:" + index);
        //lv.smoothScrollToPosition(index);
    }

    public ListaGranite creaGraniteDaData(String data){
        System.out.println("Data : " + data);
        if(!data.equals(null) && !data.equals("") && !data.equals(" ")) {
            ListaGranite Lista = new ListaGranite();
            String[] linee = data.split(";"); //Essendo data una sola riga, dividiamo per un simbolo cosi da avere piu gelati
            for (int i = 0; i < linee.length; i++) {
                String[] pezzi = linee[i].split("@");   //Es."Amarena@amarena.com/immagine.png;" oppure "Amarena§;"
                if (!pezzi[1].equals("§")) {
                    GestoreImmagini gestoreImmagini = new GestoreImmagini();
                    gestoreImmagini.getBitmapFromURL(this, pezzi[1], i);
                    Lista.aggGranita(new Granita(pezzi[0]));
                } else {
                    Lista.aggGranita(new Granita(pezzi[0]));
                }
            }
            return Lista;
        }else{
            return null;
        }
    }

    @Override
    public void HandleBitmap(Bitmap image, int IDPosizione) {
        GestoreImmagini gestoreImmagini = new GestoreImmagini();
        listaGranite.getGranita(IDPosizione).setImmagine(image);
        aggiornaListViewGranite();
    }
}
