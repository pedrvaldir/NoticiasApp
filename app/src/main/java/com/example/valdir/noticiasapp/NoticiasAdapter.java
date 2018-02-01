package com.example.valdir.noticiasapp;

/**
 * Created by VALDIR on 01/02/2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NoticiasAdapter extends ArrayAdapter<Noticia> {


    public NoticiasAdapter(Activity context, ArrayList<Noticia> livros) {
        super(context, 0, livros);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemListaView = convertView;

        if(itemListaView == null) {
            itemListaView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_lista, parent, false);
        }

        Noticia noticiasAtual = getItem(position);

        TextView tituloTextView = (TextView) itemListaView.findViewById(R.id.titulo);
        tituloTextView.setText(noticiasAtual.getTitulo());

        TextView secaoTextView = (TextView) itemListaView.findViewById(R.id.secao);
        secaoTextView.setText(noticiasAtual.getSecao());

        return itemListaView;

    }
}