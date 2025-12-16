package br.com.douglasinformatica.services;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.douglasinformatica.controllers.MainController;
import br.com.douglasinformatica.utils.Response;

public class ProdutoService {
    // all
    public void all(String pesquisa, Boolean isGtin) {
        // parametros
        String queryString = isGtin ? "&gtin=" + pesquisa
                : "&termo=" + URLEncoder.encode(pesquisa, StandardCharsets.UTF_8);

        // client
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(20)).build();

        // request
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://menorpreco.notaparana.pr.gov.br/api/v1/produtos?local=6gukbgyzh" + queryString
                        + "&offset=0&raio=2&data=-1&ordem=0"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:144.0) Gecko/20100101 Firefox/144.0")
                .timeout(Duration.ofSeconds(10))
                .build();

        // response
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());
        response.thenAcceptAsync(data -> {
            if (data.statusCode() != 200) {
                System.err.println("Erro ao requisitar. Código " + data.statusCode());
                return;
            }

            traduzirJson(data.body());
        });
    }

    // traduzir json
    public void traduzirJson(String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Response response = mapper.readValue(body, Response.class);
            MainController.setListaProdutos(response.getProdutos());
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
