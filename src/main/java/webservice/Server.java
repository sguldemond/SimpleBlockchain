package webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;
import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by stasiuz on 01/11/2017.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at port: " + port);
        server.createContext("/", new RootHandler(port));
        server.createContext("/post", new PostHandler());
        server.setExecutor(null);
        server.start();
    }
}

class RootHandler implements HttpHandler {

    private int port;

    public RootHandler(int port) {
        this.port = port;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response = String.format("<h1>Server start is succesfull at port %s</h1>", port);
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

class PostHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
        BufferedReader br = new BufferedReader(isr);
        StringBuilder message = new StringBuilder();

        String input;
        while((input = br.readLine()) != null) {
            message.append(input);
        }

        JSONObject json = new JSONObject(message.toString());

        System.out.println(json.getString("amount"));

        String responce = "json received";
        httpExchange.sendResponseHeaders(200, responce.length());
        
    }
}