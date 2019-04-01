package ru.clsoft;

import ru.clsoft.data.Item;

import java.io.IOException;

public class Work {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        Item item = new Item();
        httpClient.writeData(item);
    }
}
