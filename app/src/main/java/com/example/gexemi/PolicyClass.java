package com.example.gexemi;

public class PolicyClass {

    String PolicyNumber ;
    String VendorName ;
    String Cust_name;
    String date;
    String PhoneNo;

    public PolicyClass(String policyNumber, String vendorName, String cust_name, String date,String phoneNo) {
        PolicyNumber = policyNumber;
        VendorName = vendorName;
        Cust_name = cust_name;
        PhoneNo = phoneNo;
        this.date = date;
    }

    public String getPolicyNumber() {
        return PolicyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        PolicyNumber = policyNumber;
    }

    public String getVendorName() {
        return VendorName;
    }

    public void setVendorName(String vendorName) {
        VendorName = vendorName;
    }

    public String getCust_name() {
        return Cust_name;
    }

    public void setCust_name(String cust_name) {
        Cust_name = cust_name;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}