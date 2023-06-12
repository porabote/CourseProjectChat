package server;

import files.Files;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AbstractServer implements IServerEndpoint {
    private static int port;
    private static String host;

    private static ServerSocket serverSocket;
    private static String CONFIG_FILE_PATH = System.getProperty("user.dir") + "/app/configs/configs.json";
    public int getPort() {
        return this.port;
    }
    public String getHost() {
        return this.host;
    }

    protected ServerSocket connect() throws IOException {

        if (serverSocket != null) {
            return serverSocket;
        }

        try (ServerSocket server = createServerSocket()) {
            this.onOpen(server);
            return server;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected ServerSocket createServerSocket() throws IOException {
        initConfig();
        return serverSocket = new ServerSocket(port);
    }

    private static void initConfig() {
        String content = Files.read(CONFIG_FILE_PATH);

        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        port = jsonObject.getAsJsonObject("chat").get("port").getAsInt();
        host = jsonObject.getAsJsonObject("chat").get("host").getAsString();

    }
}