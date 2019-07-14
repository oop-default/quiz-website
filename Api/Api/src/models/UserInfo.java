package models;

public class UserInfo {
    private String first_name;
    private String last_name;
    private String username;
    private String image;
    private String status;
    private boolean is_admin;

    public UserInfo(String first_name, String last_name, String username, String image, String status, boolean is_admin) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.image = image;
        this.is_admin = is_admin;
        this.username = username;
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", is_admin=" + is_admin +
                '}';
    }
}