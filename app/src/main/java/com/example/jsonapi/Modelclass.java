package com.example.jsonapi;

public class Modelclass {
  String id;
  String city_name;
  String city_code;
  String user_id;
  String created_at;
  String updated_at;


    public Modelclass() {
    }

    public Modelclass(String id, String city_name, String city_code, String user_id, String created_at, String updated_at) {
        this.id = id;
        this.city_name = city_name;
        this.city_code = city_code;
        this.user_id = user_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
