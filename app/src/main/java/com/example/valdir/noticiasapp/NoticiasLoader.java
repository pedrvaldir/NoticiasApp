package com.example.valdir.noticiasapp;

/**
 * Created by VALDIR on 02/02/2018.
 */

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Carrega uma lista de noticias usando uma AsyncTask para realizar a
 * requisição de rede para a dada URL.
 */
public class NoticiasLoader extends AsyncTaskLoader<List<Noticia>> {

    //Utilizado para referenciar String na interface principal
    private static Context mContext;

    /** Tag para mensagens de log */
    private static final String LOG_TAG = NoticiasLoader.class.getName();

    /** URL da busca */
    private String mUrl;

    /**
     * Constrói um novo {@link NoticiasLoader}.
     *
     * @param context O contexto da activity
     * @param url A URL de onde carregaremos dados
     */
    public NoticiasLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * Está e uma thread de background.
     */
    @Override
    public List<Noticia> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Realiza requisição de rede, decodifica a resposta, e extrai uma lista de earthquakes.
        List<Noticia> Noticias = Uteis.buscarNoticias(mUrl);
        return Noticias;
    }
}