package webservice;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by stasiuz on 01/11/2017.
 */
public class RootHandler implements HttpHandler {

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
