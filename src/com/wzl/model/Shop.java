package com.wzl.model;

public class Shop {
    private int sh_id;
    private float sh_price;
    private String sh_name;
    private  String sh_picpth;
    private  String sh_text;
    private  int ty_id;
    private  int sh_number;

    public int getSh_number() {
        return sh_number;
    }

    public void setSh_number(int sh_number) {
        this.sh_number = sh_number;
    }

    public int getSh_id() {
        return sh_id;
    }

    public void setSh_id(int sh_id) {
        this.sh_id = sh_id;
    }

    public float getSh_price() {
        return sh_price;
    }

    public void setSh_price(float sh_price) {
        this.sh_price = sh_price;
    }

    public String getSh_name() {
        return sh_name;
    }

    public void setSh_name(String sh_name) {
        this.sh_name = sh_name;
    }

    public String getSh_picpth() {
        return sh_picpth;
    }

    public void setSh_picpth(String sh_picpth) {
        this.sh_picpth = sh_picpth;
    }

    public String getSh_text() {
        return sh_text;
    }

    public void setSh_text(String sh_text) {
        this.sh_text = sh_text;
    }

    public int getTy_id() {
        return ty_id;
    }

    public void setTy_id(int ty_id) {
        this.ty_id = ty_id;
    }
}
