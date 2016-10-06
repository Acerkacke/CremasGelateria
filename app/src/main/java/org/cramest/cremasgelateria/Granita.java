package org.cramest.cremasgelateria;

import android.graphics.Bitmap;

/**
 * Created by Luca on 06/10/2016.
 */

public class Granita {

    public String nome;
    public Bitmap immagine;

    public Granita(){

    }

    public Granita(String nome){
        this.nome = nome;
    }

    public Granita(String nome, Bitmap immagine){
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
