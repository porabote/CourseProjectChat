package server.endpoint;

import client.ClientThread;
import files.Files;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import messages.MessagesBroker;

public abstract class AbstractServer implements IServerEndpoint {
    private static int port;
    private static String host;


    private static boolean isConnected = false;

    public int getPort() {
        return this.port;
    }

    public String getHost() {
        return this.host;
    }


//    protected void broadcast(String message) {
//
//      //  ArrayList<ServerThread> clientsCopy;
////        synchronized (this.clientThreadsList) {
////            clientsCopy = new ArrayList<>(this.clientThreadsList);
////        }
//        for (ServerThread socketThread : MessagesBroker.getClients()) {
//            socketThread.sendMessage(message);
//        }
//    }

    protected void connect() throws IOException {

        initConfig();

        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server connected on ");// + this.getHost() + ":" + this.getPort());

            while (true) {

                Socket clientSocket = server.accept();
                System.out.println("User login");

                ServerThread serverThread = new ServerThread(clientSocket, Server.getInstance());
                serverThread.start();
                MessagesBroker.subscribe(serverThread);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean getIsConnected() {
        return isConnected;
    }

    private static void initConfig() {
        String content = Files.read(System.getProperty("user.dir") + "/app/configs/configs.json");

        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        port = jsonObject.getAsJsonObject("chat").get("port").getAsInt();
        host = jsonObject.getAsJsonObject("chat").get("host").getAsString();

    }

    public void onClose() throws IOException {
        System.out.println(this.port);
    }

    public void onMessage() throws IOException {
        System.out.println(this.port);
    }

    public void onError(Throwable throwable) {
        System.out.println(this.port);
    }

}
