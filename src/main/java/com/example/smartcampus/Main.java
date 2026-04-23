package com.example.smartcampus;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class Main {

    public static final String BASE_URI = "http://localhost:8080/api/v1/";

    public static HttpServer startServer() {
        final ResourceConfig config = new ResourceConfig()
                .packages("com.example.smartcampus");

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), config);
    }

    public static void main(String[] args) {
        HttpServer server = startServer();
        System.out.println("Smart Campus API is running.");
        System.out.println("Base URL: " + BASE_URI);
        System.out.println("Press Ctrl + C to stop.");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("Server stopped.");
            server.shutdownNow();
        }
    }
}