package org.cramest.cremasgelateria;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 06/10/2016.
 */

public class ListaGranite {

    private ArrayList<Granita> lista;

    public ListaGranite(Granita[] granite){
        this.lista = new ArrayList<Granita>(Arrays.asList(granite));
    }
    public ListaGranite(){
        lista = new ArrayList<>();
    }

    public Granita getGranita(int pos){
        return lista.get(pos);
    }

    public void aggGranita(Granita granita){
        lista.add(granita);
    }

    public String[] getNomiAsArray(){
        String[] nomi = new String[lista.size()];
        for (int i=0;i<lista.size();i++){
            nomi[i] = lista.get(i).nome;
        }
        return nomi;
    }

    public Bitmap[] getImmaginiAsArray(){
        Bitmap[] immagini = new Bitmap[lista.size()];
        for (int i=0;i<lista.size();i++){
            immagini[i] = lista.get(i).immagine;
        }
        return immagini;
    }

    public ArrayList<String> getNomiAsArrayList(){
        ArrayList<String> nomi = new ArrayList<String>(lista.size());
        for (int i=0;i<lista.size();i++){
            nomi.add(lista.get(i).nome);
        }
        return nomi;
    }

    public ArrayList<Bitmap> getImmaginiAsArrayList(){
        ArrayList<Bitmap> immagini = new ArrayList<Bitmap>(lista.size());
        for (int i=0;i<lista.size();i++){
            immagini.add(lista.get(i).immagine);
        }
        return immagini;
    }

    public int size(){
        return lista.size();
    }

    public ArrayList<Granita> getLista(){
        return lista;
    }
}
