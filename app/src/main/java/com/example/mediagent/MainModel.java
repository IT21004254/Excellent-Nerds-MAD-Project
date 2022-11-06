package com.example.mediagent;

public class MainModel {

    String name, specialist, email, hospital, durl;

    MainModel()
    {

    }

    public MainModel(String name, String disease, String time, String number, String durl) {
        this.name = name;
        this.specialist = specialist;
        this.email = email;
        this.hospital = hospital;
        this.durl = durl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getDurl() {
        return durl;
    }

    public void setDurl(String durl) {
        this.durl = durl;
    }
}
