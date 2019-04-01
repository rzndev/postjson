package ru.clsoft;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import ru.clsoft.data.Item;
import ru.clsoft.data.ItemReduced;

import java.io.IOException;

public class HttpClient {

    private ObjectMapper objectMapper;

    HttpClient() {
        objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void writeData(Item item) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost("http://clsoft.ru");
            HttpResponse response;
            String jsonString = objectMapper.writeValueAsString(item);

            ItemReduced reduced = objectMapper.readValue(jsonString, ItemReduced.class);

            StringEntity params = new StringEntity(jsonString);
            request.addHeader("content-type", "application/json");
            request.setEntity(params);
            response = httpClient.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            httpClient.close();
        }

    }
}
