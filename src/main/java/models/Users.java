package models;

import java.util.ArrayList;

 class Data{
    public int id;
    public String email;
    public String first_name;
    public String last_name;
    public String avatar;
}

public class Users{
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<Data> data;
    public Support support;
}

  class Support{
    public String url;
    public String text;
}