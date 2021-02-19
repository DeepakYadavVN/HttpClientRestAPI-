package com.qa.data;

//POJO----Plain old java objects
//Marshling> java class to java object to JSON object and JSON to Java object ......that >>>>>>Utility provide by jackson API
public class Users {
    String name;
    String Job;
    String Id;
    String CreatedAt;
    String updatedAt;


    public Users() {

    }

    public Users(String name, String job) {
        this.name = name;
        this.Job = job;
    }

    public String getUpadtedAt() {
        return updatedAt;
    }

    public void setUpadtedAt(String upadtedAt) {
        this.updatedAt = upadtedAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return Job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        Job = job;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getId() {
        return Id;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

}
