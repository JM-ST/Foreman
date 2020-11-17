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

    public void clearUserSession() {
        user = null;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id=" + user.getId() +
                ", userName='" + user.getName() + '\'' +
                ", role='" + user.getRole() + '\'' +
                '}';
    }

    public static User getUserInstance() {
        return instance.user;
    }

    public int getId() {
        return user.getId();
    }

    public String getUserName() {
        return user.getName();
    }

    public String getRole() {
        return user.getRole().toString();
    }
}