package com.example.gexemi;

public class UserClass {

    String Username;
    Integer Custid;
    String Phoneno;
    String SerialNo;
    String Cust_status;
    String Date;
    String Imei_No;


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

    public String getSerialNo() {
        return SerialNo;
    }

    public void setSerialNo(String serialNo) {
        SerialNo = serialNo;
    }

    public String getCust_status() {
        return Cust_status;
    }

    public void setCust_status(String cust_status) {
        Cust_status = cust_status;
    }

    public String getDate() {
        return Date;
    }

    public String getImei_No() {
        return Imei_No;
    }



    public void setDate(String date) {
        Date = date;
    }

    public UserClass(String username, Integer custid, String phoneno, String serialNo, String cust_status, String date,String imei_No) {
        Username = username;
        Custid = custid;
        Phoneno = phoneno;
        SerialNo = serialNo;
        Cust_status = cust_status;
        Date = date;
        Imei_No = imei_No;
    }
}