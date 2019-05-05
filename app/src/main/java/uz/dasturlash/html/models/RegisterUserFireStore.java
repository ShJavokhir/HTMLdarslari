package uz.dasturlash.html.models;

public class RegisterUserFireStore {
    String user_name = "";
    String familiya = "";

    public RegisterUserFireStore(String user_name, String familiya) {
        this.user_name = user_name;
        this.familiya = familiya;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getFamiliya() {
        return familiya;
    }

    public void setFamiliya(String familiya) {
        this.familiya = familiya;
    }
}
