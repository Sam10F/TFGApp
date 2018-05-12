package Session;

public final class Session {

    private static String userName, email, pswd;

    public Session(String usersName){
        userName = usersName;
    }

    public static void setUserName(String userName) {
        Session.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPswd() {
        return pswd;
    }

    public static void setPswd(String pswd) {
        Session.pswd = pswd;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }
}
