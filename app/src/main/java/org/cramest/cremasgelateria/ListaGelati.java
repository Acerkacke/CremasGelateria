package org.cramest.cremasgelateria;

import android.graphics.Bitmap;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 27/09/2016.
 */

public class ListaGelati {

    private ArrayList<Gelato> lista;

    public ListaGelati(Gelato[] gelati){
        this.lista = new ArrayList<Gelato>(Arrays.asList(gelati));
    }
    public ListaGelati(){
        lista = new ArrayList<>();
    }

    public Gelato getGelato(int pos){
        return lista.get(pos);
    }

    public void aggGelato(Gelato gelato){
        lista.add(gelato);
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

    public ArrayList<Gelato> getLista(){
        return lista;
    }

}
