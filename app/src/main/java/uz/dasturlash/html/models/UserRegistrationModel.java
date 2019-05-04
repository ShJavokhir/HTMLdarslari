package uz.dasturlash.html.models;

public class UserRegistrationModel {
    private Integer id;
    private String username;
    private String first_name;
    private String email;
    private String region;
    private String password;

    public UserRegistrationModel(String username, String first_name, String email, String region, String password) {
        this.username = username;
        this.first_name = first_name;
        this.email = email;
        this.region = region;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getFirst_name() {
        return first_name;
    }


    public String getEmail() {
        return email;
    }

    public String getRegion() {
        return region;
    }



    public String getPassword() {
        return password;
    }
}
