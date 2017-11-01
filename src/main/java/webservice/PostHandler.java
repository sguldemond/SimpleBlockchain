package webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * Created by stasiuz on 01/11/2017.
 */
public class PostHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder message = new StringBuilder();

        String input;
        while((input = br.readLine()) != null) {
            message.append(input);
        }

        System.out.printf(message.toString());

        JSONObject json = new JSONObject(message.toString());


        StringBuilder response = new StringBuilder();
        response.append(String.format("FROM: %s \n", json.get("from")));
        response.append(String.format("TO: %s \n", json.get("to")));
        response.append(String.format("AMOUNT: %s", json.get("amount")));

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
