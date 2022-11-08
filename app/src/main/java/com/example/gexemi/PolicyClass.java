package com.example.gexemi;

public class PolicyClass {

    String PolicyNumber ;
    String VendorName ;
    String VendorCode;

    public PolicyClass(String policyNumber, String vendorName, String vendorCode) {
        PolicyNumber = policyNumber;
        VendorName = vendorName;
        VendorCode = vendorCode;
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

    public String getVendorCode() {
        return VendorCode;
    }

    public void setVendorCode(String vendorCode) {
        VendorCode = vendorCode;
    }
}
