package fi.hh.swd20.shaderlib.domain;

public class UserDto {
    private String username;
    private String email;
    private String password;

    public UserDto() {
        super();
        this.username = "";
        this.email = "";
        this.password = "";
    }

    public UserDto(String username, String email, String password) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return username + " - " + email;
    }

}
