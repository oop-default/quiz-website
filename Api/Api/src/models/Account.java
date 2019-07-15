package models;

public class Account {
    private String username;
    private String password;
    private String firstname;
    private String secondname;
    private String gender;
    private int id;


    public Account(String username,String password,String firstname,String secondname,String gender){
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

    @Override
    public String toString() {
        String result = "username: "+username+"\n"
                +"password: "+password+"\n"
                +"firstname: "+firstname+"\n"
                +"secondname: "+secondname+"\n"
                +"gender: "+gender;
        return result;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    @Override
    public boolean equals(Object obj) {

        Account ac = (Account)obj;
        if(ac.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
