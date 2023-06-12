package users;

public class ChatUser implements IChatUser {

    private int id;
    private String name;
    private static boolean isOnline = false;


    public ChatUser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
