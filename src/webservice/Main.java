package webservice;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Created by stasiuz on 01/11/2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int port = 9000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at port: " + port);
        server.createContext("/", new RootHandler(port));
        server.setExecutor(null);
        server.start();
    }
}