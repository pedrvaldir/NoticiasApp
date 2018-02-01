package com.example.valdir.noticiasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class NoticiasActivity extends AppCompatActivity {

    public static final String LOG_TAG = NoticiasActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        // Cria uma lista falsa de noticias
        ArrayList<Noticia> noticias = new ArrayList<Noticia>();

        noticias.add(new Noticia("Titulo1","Secao"));
        noticias.add(new Noticia("Titulo2","Secao2"));
        noticias.add(new Noticia("Titulo3","Secao3"));
        noticias.add(new Noticia("Titulo4","Secao4"));
        noticias.add(new Noticia("Titulo5","Secao5"));

        NoticiasAdapter noticiasAdapter = new NoticiasAdapter(this, noticias);

        // Encontra uma referencia de {@link ListView} no layout
        ListView noticiasListView = (ListView) findViewById(R.id.lista);


        noticiasListView.setAdapter(noticiasAdapter);

        // Defina o adaptador no {@link ListView}
        // então a lista pode ser preenchida na interface do usuário
        noticiasListView.setAdapter(noticiasAdapter);
    }
}
