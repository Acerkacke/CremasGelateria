package org.cramest.cremasgelateria;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.cramest.cremasgelateria.R;

import java.util.ArrayList;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final ListaGelati listaGelati;
    private final ListaGranite listaGranite;

    public CustomList(Activity context, ListaGelati listaGelati) {
        super(context, R.layout.list_single, listaGelati.getNomiAsArray());
        this.context = context;
        this.listaGelati = listaGelati;
        listaGranite = null;
    }
    public CustomList(Activity context, ListaGranite listaGranite) {
        super(context, R.layout.list_single, listaGranite.getNomiAsArray());
        this.context = context;
        this.listaGranite = listaGranite;
        listaGelati = null;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        if(listaGelati != null) {
            txtTitle.setText(listaGelati.getGelato(position).nome);
            if (listaGelati.getGelato(position).immagine != null) {
                imageView.setImageBitmap(listaGelati.getGelato(position).immagine);
            }
        }else{
            txtTitle.setText(listaGranite.getGranita(position).nome);
            if (listaGranite.getGranita(position).immagine != null) {
                imageView.setImageBitmap(listaGranite.getGranita(position).immagine);
            }
        }
        return rowView;
    }
}