package com.wiredelta.sample.companydetail.model;

/**
 * Created by Admin on 2/26/2016.
 */
public class Company {

    private String companyID,
            comapnyName,
            companyOwner,
            companyStartDate,
            companyDescription,
            companyDepartments;

    public Company() {
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getComapnyName() {
        return comapnyName;
    }

    public void setComapnyName(String comapnyName) {
        this.comapnyName = comapnyName;
    }

    public String getCompanyOwner() {
        return companyOwner;
    }

    public void setCompanyOwner(String companyOwner) {
        this.companyOwner = companyOwner;
    }

    public String getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(String companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public String getCompanyDepartments() {
        return companyDepartments;
    }

    public void setCompanyDepartments(String companyDepartments) {
        this.companyDepartments = companyDepartments;
    }

}
