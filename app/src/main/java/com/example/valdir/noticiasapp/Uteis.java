package com.example.valdir.noticiasapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by VALDIR on 01/02/2018.
 */

public class Uteis {

    /** Tag para mensagens de log */
    private static final String LOG_TAG = Uteis.class.getName();

    /**
     * Exemplo de resposta JSON para uma consulta API Guardian
     */
    private static final String RESPOSTA_JSON = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":12162,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1217,\"orderBy\":\"relevance\",\"results\":[{\"id\":\"politics/2018/jan/30/britains-memory-of-war-and-the-brexit-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-01-30T18:14:30Z\",\"webTitle\":\"Britain’s memory of war and the Brexit debate | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2018/jan/30/britains-memory-of-war-and-the-brexit-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/jan/30/britains-memory-of-war-and-the-brexit-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/jan/24/consumers-are-being-squeezed-two-views-from-monetary-policy-committee-insiders\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-01-24T18:13:30Z\",\"webTitle\":\"'The squeeze continues' – experts debate Brexit watch data\",\"webUrl\":\"https://www.theguardian.com/politics/2018/jan/24/consumers-are-being-squeezed-two-views-from-monetary-policy-committee-insiders\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/jan/24/consumers-are-being-squeezed-two-views-from-monetary-policy-committee-insiders\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2017/oct/03/the-guardian-view-on-the-tories-and-brexit-debate-what-debate\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2017-10-03T18:02:47Z\",\"webTitle\":\"The Guardian view on the Tories and Brexit: Debate? What debate? | Editorial\"," +
            "\"webUrl\":\"https://www.theguardian.com/commentisfree/2017/oct/03/the-guardian-view-on-the-tories-and-brexit-debate-what-debate\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2017/oct/03/the-guardian-view-on-the-tories-and-brexit-debate-what-debate\",\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"world/2017/sep/24/violence-against-women-transgender-debate\",\"type\":\"article\",\"sectionId\":\"world\",\"sectionName\":\"World news\",\"webPublicationDate\":\"2017-09-24T17:04:44Z\",\"webTitle\":\"Violence has no place in transgender debate | Letters\",\"webUrl\":\"https://www.theguardian.com/world/2017/sep/24/violence-against-women-transgender-debate\",\"apiUrl\":\"https://content.guardianapis.com/world/2017/sep/24/violence-against-women-transgender-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/blog/live/2017/nov/15/pmqs-may-corbyn-eu-withdrawal-bill-will-be-massacred-in-lords-unless-parts-are-rewritten-lewin-warns-may-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-11-15T22:59:11Z\",\"webTitle\":\"PMQs and MPs debate EU withdrawal bill – as it happened\",\"webUrl\":\"https://www.theguardian.com/politics/blog/live/2017/nov/15/pmqs-may-corbyn-eu-withdrawal-bill-will-be-massacred-in-lords-unless-parts-are-rewritten-lewin-warns-may-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/blog/live/2017/nov/15/pmqs-may-corbyn-eu-withdrawal-bill-will-be-massacred-in-lords-unless-parts-are-rewritten-lewin-warns-may-politics-live\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2017/nov/03/mps-to-debate-bill-to-reduce-voting-age-to-16\"," +
            "\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-11-03T07:00:17Z\",\"webTitle\":\"MPs to debate bill to reduce voting age to 16\",\"webUrl\":\"https://www.theguardian.com/politics/2017/nov/03/mps-to-debate-bill-to-reduce-voting-age-to-16\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/nov/03/mps-to-debate-bill-to-reduce-voting-age-to-16\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2017/aug/13/abusive-brexit-debate-insults-our-intelligence\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-08-13T18:29:26Z\",\"webTitle\":\"Abusive Brexit debate insults our intelligence | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2017/aug/13/abusive-brexit-debate-insults-our-intelligence\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/aug/13/abusive-brexit-debate-insults-our-intelligence\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"society/2017/oct/23/labour-and-rebel-tories-secure-debate-on-universal-credit-rollout\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2017-10-23T17:47:02Z\",\"webTitle\":\"Labour and rebel Tories secure debate on universal credit rollout\",\"webUrl\":\"https://www.theguardian.com/society/2017/oct/23/labour-and-rebel-tories-secure-debate-on-universal-credit-rollout\",\"apiUrl\":\"https://content.guardianapis.com/society/2017/oct/23/labour-and-rebel-tories-secure-debate-on-universal-credit-rollout\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2017/oct/12/brexit-talks-at-disturbing-deadlock-over-divorce-bill-says-eu-negotiator\"," +
            "\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-10-12T19:59:22Z\",\"webTitle\":\"EU withdrawal bill debate postponed as Brexit talks hit buffers\",\"webUrl\":\"https://www.theguardian.com/politics/2017/oct/12/brexit-talks-at-disturbing-deadlock-over-divorce-bill-says-eu-negotiator\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/oct/12/brexit-talks-at-disturbing-deadlock-over-divorce-bill-says-eu-negotiator\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2017/oct/09/shouting-the-other-side-down-wont-advance-the-brexit-debate\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2017-10-09T17:56:41Z\",\"webTitle\":\"Shouting the other side down won’t advance the Brexit debate | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2017/oct/09/shouting-the-other-side-down-wont-advance-the-brexit-debate\",\"apiUrl\":\"https://content.guardianapis.com/politics/2017/oct/09/shouting-the-other-side-down-wont-advance-the-brexit-debate\",\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    /**
     * Crie um construtor privado porque ninguém jamais deve criar um  objeto {@link Uteis}.
     * Esta classe destina-se apenas a manter variáveis estáticas e métodos, a qual pode ser acessado
     * diretamente do nome da classe ConsultaUteis (e uma instância de objeto do QueryUtils não é necessária).
     */
    private Uteis() {
    }

    public static ArrayList<Noticia> buscarNoticias(String requisicaoUrl) {

        // Cria um objeto URL
        URL url = criarUrl(requisicaoUrl);


        String respostaJson = null;

        // Execute a solicitação HTTP para o URL e receba uma resposta JSON de volta
        try {
            respostaJson = requisicaoHttp(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Erro ao fechar o fluxo de entrada", e);
        }

        // crie uma lista de objetos de terremoto com os dados correspondentes.
        // Extraia campos relevantes da resposta JSON e retorne arraylist {@link Event}
        ArrayList<Noticia> noticias = ExtrairNoticias(respostaJson);

        // Return the {@link Event}
        return noticias;
    }

    /**
     * Retorna o novo URL do URL fornecido.
     */
    private static URL criarUrl(String stringUrl) {

        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Erro ao criar url ", e);
        }
        return url;
    }

    /**
     * Faça uma solicitação HTTP para o URL fornecido e devolva um String como a resposta das noticias.
     */
    private static String requisicaoHttp(URL url) throws IOException {
        String respostaJson = "";

        // Se o URL for nulo, volte mais cedo.
        if (url == null) {
            return respostaJson;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // Se a solicitação foi bem sucedida (código de resposta 200),
            //  então leia o fluxo de entrada e analise a resposta.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                respostaJson = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Código de resposta de erro: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problema ao recuperar os resultados de noticias JSON.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return respostaJson;
    }

    /**
     * Retorne uma lista de objeto {@link Noticia} que foram construidos a partir
     * da analise da resposta JSON.
     */
    public static ArrayList<Noticia> ExtrairNoticias(String noticiasJSON) {

        // Se a String JSON for vazia ou nula, então retorne logo mais
        if (TextUtils.isEmpty(noticiasJSON)) {
            return null;
        }

        // Tente analisar a RESPOSTA_JSON. Se houver um problema com a forma como o JSON
        // está formatado, um objeto de exceção JSONException será lançado.
        // Pegue a exceção para que o aplicativo não ocorra e imprima a mensagem de erro nos logs.
        try {

            // Cria um arrayList vazio para ser adicionado os dados da noticia
            ArrayList<Noticia> noticiasArray = new ArrayList<>();

            // TODO: Analise a resposta dada pela sequência RESPOSTA_JSON e
            // cria uma lista de informações dos dados correspondentes a noticia
            JSONObject respostaBaseJson = new JSONObject(noticiasJSON);
            JSONObject noticiasObject = respostaBaseJson.getJSONObject("response");
            JSONArray arrayInformacoes = noticiasObject.getJSONArray("results");

            for (int i = 0; i < arrayInformacoes.length(); i++) {
                JSONObject noticiaAtual = arrayInformacoes.getJSONObject(i);

                String titulo = noticiaAtual.getString("webTitle");
                String secao = noticiaAtual.getString("sectionName");
                String url = noticiaAtual.getString("webUrl");

                Noticia noticia = new Noticia(titulo, secao, url);
                noticiasArray.add(noticia);
            }

            // Crie uma lista de Objetos {@link ArrayList<Noticia>}
            return new ArrayList<Noticia>(noticiasArray);

        } catch (JSONException e) {
            e.printStackTrace();
            //Se um erro for lançado ao executar qualquer uma das instruções acima no bloco "try"
            // com a mensagem da exceção.
            Log.e("ConsultaUteis", "Problema ao analisar o resultado de noticias Json", e);
        }

        return null;
    }

    /**
     * Converta o {@link InputStream} em uma String que contém toda a resposta JSON do servidor.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
