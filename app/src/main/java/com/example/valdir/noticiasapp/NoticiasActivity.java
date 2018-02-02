package com.example.valdir.noticiasapp;

import android.content.Intent;
import android.net.Uri;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NoticiasActivity extends AppCompatActivity implements LoaderCallbacks<List<Noticia>>{

    public static final String LOG_TAG = NoticiasActivity.class.getName();

    /**
     * Valor constante para o ID do loader de earthquake. Podemos escolher qualquer inteiro.
     * Isto só importa realmente se você estiver usando múltiplos loaders.
     */
    private static final int NOTICIAS_LOADER_ID = 1;

    private String REQUISICAO_URL_APIGUARDIAN = "https://content.guardianapis.com/search?q=curitiba&api-key=test";

    private NoticiasAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        // Crie um novo adaptador que leve uma lista vazia de noticias como entrada
        mAdapter = new NoticiasAdapter(NoticiasActivity.this, new ArrayList<Noticia>());

        // Encontra uma referencia de {@link ListView} no layout
        ListView noticiasListView = (ListView) findViewById(R.id.lista);

        mAdapter = new NoticiasAdapter(NoticiasActivity.this, new ArrayList<Noticia>());

        // Defina o adaptador no {@link ListView}
        // então a lista pode ser preenchida na interface do usuário
        noticiasListView.setAdapter(mAdapter);

        final LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(NOTICIAS_LOADER_ID, null, NoticiasActivity.this);

        //quando alguma noticia for clicada acionar um intent para abrir a url da noticia
        noticiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                                        // Achar a noticia que foi clicada
                                                        Noticia noticiaAtual = mAdapter.getItem(position);

                                                        // Converte o URL String em um objeto URI (para passar no construtor de Intent)
                                                        Uri noticiaUri = Uri.parse(noticiaAtual.getUrl());

                                                        // Cria um novo intent para visualizar a URI da noticia
                                                        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, noticiaUri);

                                                        // Envia o intent para lançar uma nova activity
                                                        startActivity(websiteIntent);

                                                    }
                                                }
        );
    }

    @Override
    public Loader<List<Noticia>> onCreateLoader(int i, Bundle bundle) {
        // Criar um novo loader para a dada URL

        return new NoticiasLoader(this, REQUISICAO_URL_APIGUARDIAN);
    }

    @Override
    public void onLoadFinished(Loader<List<Noticia>> loader, List<Noticia> noticias) {

        // Limpa o adapter de dados de livros anteriores
        mAdapter.clear();

        // Se há uma lista válida de {@link livro}s, então os adiciona ao data set do adapter.
        // Isto ativará a atualização da ListView.
        if (noticias != null && !noticias.isEmpty()) {
            mAdapter.addAll(noticias);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Noticia>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}