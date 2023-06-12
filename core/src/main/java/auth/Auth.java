package auth;

import java.util.Random;

public class Auth {

    public static int auth(String name, String psw) {

        //TODO Some Auth Logic with user login and psw;
        int userId = new Random().nextInt(10000);

        return userId;
    }

}
