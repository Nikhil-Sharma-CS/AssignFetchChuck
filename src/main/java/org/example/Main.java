package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        String urlString = "https://api.chucknorris.io/jokes/random";

        URL url;
        HttpURLConnection connection;
        int responseCode;

        //URL
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        //Connection
        try {
            connection = (HttpURLConnection) url.openConnection();
            //Extracting response Code
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //extract information from the connection object;

        if (responseCode == 200) {
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            StringBuilder ApiData = new StringBuilder();

            String readline;

            while (true) {
                try {
                    if (!((readline = in.readLine()) != null)) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                ApiData.append(readline);
            }

            //Closing buffer reader
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            JSONObject jsonApiresponse = new JSONObject(ApiData.toString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String prettyJson = gson.toJson(jsonApiresponse);
            System.out.println(prettyJson);
        }
        else
        {
            System.out.println("API call could not be made!!");
        }
    }
}