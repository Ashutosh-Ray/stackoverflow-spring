package com.stackoverflow.services.genai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class GPTApiClient {

    private static String getTokenForAiapi(String clientId, String clientSecret, String authUrl) throws IOException {
        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
        HttpPost post = new HttpPost(authUrl);
        post.setHeader("Authorization", "Basic " + credentials);
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Access-Control-Allow-Origin", "*");

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {
            if (response.getCode() != 200) {
                throw new IOException("Token retrieval failed");
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.getEntity().getContent());
            return rootNode.get("access_token").asText();
        }
    }

    public String getResponseFromGpt(String question) throws IOException {
        String tokenServiceURL = "https://tfe-india-genai-9gqs3m10.authentication.eu10.hana.ondemand.com/oauth/token?grant_type=client_credentials";
        String clientId = "sb-b20be619-6fc3-449f-a899-ac2333da55f6!b391340|aicore!b540";
        String clientSecret = "b1da677d-486c-4242-95f1-88abf9db1723$o8_59_gZufPf83buu0O4BoyGV0K_mwTTLKU3NfmD3OA=";

        String accessToken = getTokenForAiapi(clientId, clientSecret, tokenServiceURL);

        String jsonBody = "{\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"" + question + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"max_tokens\": 100,\n" +
                "  \"temperature\": 0.0,\n" +
                "  \"frequency_penalty\": 0,\n" +
                "  \"presence_penalty\": 0,\n" +
                "  \"stop\": \"null\"\n" +
                "}";

        HttpPost post = new HttpPost("https://api.ai.prod.eu-central-1.aws.ml.hana.ondemand.com/v2/inference/deployments/d2959af40a8b5659/chat/completions?api-version=2023-05-15");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("AI-Resource-Group", "default");
        post.setHeader("Authorization", "Bearer " + accessToken);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));

        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(post)) {
            if (response.getCode() != 200) {
                throw new IOException("Request to GPT API failed");
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response.getEntity().getContent());
            return rootNode.get("choices").get(0).get("message").get("content").asText();
        }
    }

}