package com.wiredelta.sample.companydetail.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Admin on 2/26/2016.
 */
public class Company implements Parcelable {

    private String companyID,
            comapnyName,
            companyOwner,
            companyStartDate,
            companyDescription,
            companyDepartments;

    public Company() {
    }

    public Company(String companyID, String comapnyName, String companyOwner, String companyStartDate, String companyDescription, String companyDepartments) {
        this.companyID = companyID;
        this.comapnyName = comapnyName;
        this.companyOwner = companyOwner;
        this.companyStartDate = companyStartDate;
        this.companyDescription = companyDescription;
        this.companyDepartments = companyDepartments;
    }

    protected Company(Parcel in) {
        companyID = in.readString();
        comapnyName = in.readString();
        companyOwner = in.readString();
        companyStartDate = in.readString();
        companyDescription = in.readString();
        companyDepartments = in.readString();
    }

    public static final Creator<Company> CREATOR = new Creator<Company>() {
        @Override
        public Company createFromParcel(Parcel in) {
            return new Company(in);
        }

        @Override
        public Company[] newArray(int size) {
            return new Company[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(companyID);
        dest.writeString(comapnyName);
        dest.writeString(companyOwner);
        dest.writeString(companyStartDate);
        dest.writeString(companyDescription);
        dest.writeString(companyDepartments);
    }
}
