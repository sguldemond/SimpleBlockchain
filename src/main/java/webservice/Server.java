package webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.JSONObject;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.ArrayList;

/**
 * Created by stasiuz on 01/11/2017.
 */
public class Server {

    public ArrayList<JSONObject> nodeTransactions = new ArrayList<>();

    public static void main(String[] args) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void start() throws IOException {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at port: " + port);
        server.createContext("/", new RootHandler(port));
        server.createContext("/post", new PostHandler());
        server.setExecutor(null);
        server.start();
    }
}