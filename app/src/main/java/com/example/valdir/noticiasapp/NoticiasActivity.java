package com.example.valdir.noticiasapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NoticiasActivity extends AppCompatActivity {

    public static final String LOG_TAG = NoticiasActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        // Cria uma lista falsa de noticias
        ArrayList<String> noticias = new ArrayList<>();
        noticias.add("San Francisco");
        noticias.add("London");
        noticias.add("Tokyo");


        // Encontra uma referencia de {@link ListView} no layout
        ListView noticiasListView = (ListView) findViewById(R.id.lista);

        // Cria uma nova {@link ArrayAdapter} de noticias
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, noticias);

        // Defina o adaptador no {@link ListView}
        // então a lista pode ser preenchida na interface do usuário
        noticiasListView.setAdapter(adapter);
    }
}
