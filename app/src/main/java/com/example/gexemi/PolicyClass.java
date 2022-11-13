package com.example.gexemi;

public class PolicyClass {

    String PolicyNumber ;
    String VendorName ;
    String shop_name;
    Integer policyID;

    public PolicyClass(String policyNumber, String vendorName, String shop_name, Integer policyID) {
        PolicyNumber = policyNumber;
        VendorName = vendorName;
        this.shop_name = shop_name;
        this.policyID = policyID;
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

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public Integer getPolicyID() {
        return policyID;
    }

    public void setPolicyID(Integer policyID) {
        this.policyID = policyID;
    }
}