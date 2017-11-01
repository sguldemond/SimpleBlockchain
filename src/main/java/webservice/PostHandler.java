package webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;

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

        JSONObject transaction = new JSONObject(message.toString());

        File node = new File("node.json");
        FileWriter file;
        if(node.exists()) {
            file = new FileWriter(node);
        } else {
            file = new FileWriter("node.json");
        }

        file.append(transaction.toString());
        file.flush();


        StringBuilder response = new StringBuilder();
        response.append(String.format("FROM: %s \n", transaction.get("from")));
        response.append(String.format("TO: %s \n", transaction.get("to")));
        response.append(String.format("AMOUNT: %s", transaction.get("amount")));

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.toString().getBytes());
        os.close();
    }
}
