package org.cramest.cremasgelateria;

import android.graphics.Bitmap;

/**
 * Created by User on 23/09/2016.
 */

public class Gelato {
    int ID;
    String nome;
    Bitmap immagine;

    public Gelato(){

    }
    public Gelato(String nome){
        this.nome = nome;
    }
    public Gelato(String nome, Bitmap immagine){
        this.nome = nome;
        this.immagine = immagine;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setImmagine(Bitmap immagine){
        this.immagine = immagine;
    }

    public String toString(){
        return nome;
    }
}

