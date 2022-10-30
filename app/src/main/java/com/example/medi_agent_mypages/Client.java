package com.example.medi_agent_mypages;

public class Client {
    private String Location;
    private String date;
    private String Time;
    private String Name;
    private String Age;
    private String Appointmentno;

    public String getAppointmentno() {
        return Appointmentno;
    }

    public void setAppointmentno(String appointmentno) {
        Appointmentno = appointmentno;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public Client()
   {

   }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
