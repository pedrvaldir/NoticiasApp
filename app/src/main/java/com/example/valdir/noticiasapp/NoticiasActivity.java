package com.example.valdir.noticiasapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NoticiasActivity extends AppCompatActivity {

    public static final String LOG_TAG = NoticiasActivity.class.getName();
    private String url = "https://content.guardianapis.com/search?q=curitiba&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);

        // Cria uma lista falsa de noticias
        ArrayList<Noticia> noticias = Uteis.ExtrairNoticias(url);

               NoticiaAsyncTask task = new NoticiaAsyncTask();
               task.execute(url);
    }


    private class NoticiaAsyncTask extends AsyncTask<String,Void, ArrayList<Noticia>>{

        /**
         * Esse método é chamado por uma thread em segundo plano, então, podemos executar
         * operações mais lentas como conexões de rede
         * <p>
         * Não é correto atualizar a UI usando uma operação em segundo plano, então, apenas
         * retornamos {@link ArrayList<Noticia>} como resultado.
         */
        @Override
        protected ArrayList<Noticia> doInBackground(String... urls) {

            // Não execute o pedido se não houver URLs ou se for nula
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }

            // Execute a solicitação HTTP para dados de noticias e processe a resposta.
            ArrayList<Noticia> resultado = Uteis.buscarNoticias(urls[0]);

            return resultado;
        }

        /**
         * Esse método é chamado na UI principal depois que o trabalho em segundo plano foi executado.
         * <p>
         * Está correto modificar a UI com esse método. Pegamos o objeto {@link ArrayList<Noticia>} (que
         * foi retornado do doInBackground()) e atualizamos a tela.
         */
        @Override
        protected void onPostExecute(ArrayList<Noticia> resultado) {

            // Se não houver resultados, não faça nada.
            if (resultado == null) {
                return;
            }

            updateUi(resultado);
        }
    }

    private void updateUi(ArrayList<Noticia> ListaNoticia) {

        final NoticiasAdapter noticiasAdapter = new NoticiasAdapter(this, ListaNoticia);

        // Encontra uma referencia de {@link ListView} no layout
        ListView noticiasListView = (ListView) findViewById(R.id.lista);

        // Defina o adaptador no {@link ListView}
        // então a lista pode ser preenchida na interface do usuário
        noticiasListView.setAdapter(noticiasAdapter);

        //quando alguma noticia for clicada acionar um intent para abrir a url da noticia
        noticiasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                                                        // Achar a noticia que foi clicada
                                                        Noticia noticiaAtual = noticiasAdapter.getItem(position);

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

}