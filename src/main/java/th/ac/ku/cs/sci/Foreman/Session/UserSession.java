package th.ac.ku.cs.sci.Foreman.Session;

import th.ac.ku.cs.sci.Foreman.Model.User;

public final class UserSession {

    private static UserSession instance ;
    private User user ;

    private UserSession(User user) {
        this.user = user ;
    }

    public static UserSession getInstance(User user) {
        if (instance == null){
            instance = new UserSession(user);
        }
        return instance ;
    }

    public static void clearUserSession() {
        instance = null ;
    }

    public static User getUserInstance() {
        return instance.user;
    }

    public int getId() {
        return user.getId();
    }

}