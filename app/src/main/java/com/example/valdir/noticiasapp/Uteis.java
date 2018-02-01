package com.example.valdir.noticiasapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by VALDIR on 01/02/2018.
 */

public class Uteis {

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

    /**
     * Retorne uma lista de objeto {@link Noticia} que foram construidos a partir
     * da analise da resposta JSON.
     */
    public static ArrayList<Noticia> ExtrairNoticias() {

        // Cria um arrayList vazio para ser adicionado os dados da noticia
        ArrayList<Noticia> noticias = new ArrayList<>();

        // Tente analisar a RESPOSTA_JSON. Se houver um problema com a forma como o JSON
        // está formatado, um objeto de exceção JSONException será lançado.
        // Pegue a exceção para que o aplicativo não ocorra e imprima a mensagem de erro nos logs.
        try {

            // TODO: Analise a resposta dada pela sequência RESPOSTA_JSON e
            // crie uma lista de objetos de noticias com os dados correspondentes
            JSONObject respostaBaseJson = new JSONObject(RESPOSTA_JSON);
            JSONObject noticiasObject = respostaBaseJson.getJSONObject("response");
            JSONArray arraynoticias = noticiasObject.getJSONArray("results");

            for (int i = 0; i < arraynoticias.length(); i++) {
                JSONObject noticiaAtual = arraynoticias.getJSONObject(i);

                String titulo = noticiaAtual.getString("webTitle");
                String secao = noticiaAtual.getString("sectionName");
                //String time = propriedades.getString("webUrl");

                Noticia noticia = new Noticia(titulo, secao);
                noticias.add(noticia);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            //Se um erro for lançado ao executar qualquer uma das instruções acima no bloco "try"
            // com a mensagem da exceção.
            Log.e("ConsultaUteis", "Problema ao analisar o resultado de noticias Json", e);
        }

        // Retorna uma lista com as noticias
        return noticias;

    }
}
