package com.example.gexemi;

public class UserClass {

    String Username;
    Integer Custid;
    String Phoneno;

    public UserClass(String username, Integer custid, String phoneno) {
        Username = username;
        Custid = custid;
        Phoneno = phoneno;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Integer getCustid() {
        return Custid;
    }

    public void setCustid(Integer custid) {
        Custid = custid;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }
}
