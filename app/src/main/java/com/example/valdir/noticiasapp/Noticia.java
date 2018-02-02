package com.example.valdir.noticiasapp;

/**
 * Created by VALDIR on 01/02/2018.
 */

public class Noticia {

    /** Título da noticia */
    String mTitulo;
    /** Nome da seção que pertence */
    String mSecao;

    /** Endereço da noticia completa */
    String mUrl;


    public Noticia(String titulo, String secao, String url) {
        mTitulo = titulo;
        mSecao = secao;
        mUrl= url;
    }


    public String getTitulo() {
        return mTitulo;
    }

    public String getSecao() {
        return mSecao;
    }

    public String getUrl() {
        return mUrl;
    }

}
