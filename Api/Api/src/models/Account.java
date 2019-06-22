package models;

public class Account {
    private String username;
    private String password;
    private String firstname;
    private String secondname;
    private String email;
    private String gender;


    public Account(String username,String password,String firstname,String secondname,String email,String gender){
        this.email=email;
        this.password=password;
        this.username=username;
        this.firstname=firstname;
        this.secondname=secondname;
        this.gender=gender;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        String result = "username: "+username+"\n"
                +"password: "+password+"\n"
                +"firstname: "+firstname+"\n"
                +"secondname: "+secondname+"\n"
                +"email: "+email+"\n"
                +"gender: "+gender;
        return result;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
