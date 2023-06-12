package client;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import files.Files;
import messages.MessagesBroker;
import users.ChatUser;

import java.io.*;
import java.net.Socket;

public abstract class AbstractClient implements IClientEndpoint {
    private int port;
    private String host;
    protected ChatUser user;

    public int getPort() {
        return this.port;
    }

    public String getHost() {
        return this.host;
    }

    public void connect() throws IOException {

        initConfig();

        try (Socket clientSocket = new Socket(host, port);) {

            Thread clientThread = new ClientThread(clientSocket, this.user);
            clientThread.start();
            clientThread.join();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private void initConfig() {
        String content = Files.read(System.getProperty("user.dir") + "/app/configs/configs.json");

        JsonObject jsonObject = new JsonParser().parse(content).getAsJsonObject();
        this.port = jsonObject.getAsJsonObject("chat").get("port").getAsInt();
        this.host = jsonObject.getAsJsonObject("chat").get("host").getAsString();

    }

    //    public void onOpen(Socket socket, BufferedReader in,  PrintWriter out) throws IOException {
//    }

    public void onClose()  throws IOException {
        System.out.println(this.port);
    }

    public void onMessage()  throws IOException {
        System.out.println(this.port);
    }

    public void onError(Throwable throwable) {
        System.out.println(this.port);
    }

}
