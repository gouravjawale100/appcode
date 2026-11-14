package com.demo;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class App {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress("0.0.0.0", port), 0);
        server.createContext("/health", ex -> respond(ex, 200, "OK", "text/plain; charset=utf-8"));
        server.createContext("/", ex -> {
            String html = "<!doctype html><html><head><title>Release Demo</title></head><body>"
                + "<h1 id='welcome'>Welcome to the Release Demo App</h1>"
                + "<p id='status'>Application is running.</p>"
                + "<label for='name'>Your name</label><input id='name' type='text'/>"
                + "<button id='greet' onclick=\"document.getElementById('result').innerText='Hello, '+document.getElementById('name').value+'!'\">Greet</button>"
                + "<p id='result'></p></body></html>";
            respond(ex, 200, html, "text/html; charset=utf-8");
        });
        server.start();
        System.out.println("Release Demo App started on port " + port);
    }
    private static void respond(HttpExchange ex, int code, String body, String type) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        ex.getResponseHeaders().set("Content-Type", type);
        ex.sendResponseHeaders(code, bytes.length);
        try (var out = ex.getResponseBody()) { out.write(bytes); }
    }
}
