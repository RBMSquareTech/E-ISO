package com.rbmsquare.e_iso.Model;

public class OrderModel {

    private String iso;
    private String date;
    private String pending;
    private String delete;

    public OrderModel(String iso, String date, String pending, String delete) {
        this.iso = iso;
        this.date = date;
        this.pending = pending;
        this.delete = delete;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
