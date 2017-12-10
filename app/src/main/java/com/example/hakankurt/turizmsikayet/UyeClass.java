package com.example.hakankurt.turizmsikayet;

/**
 * Created by FURKAN on 10.12.2017.
 */

public class UyeClass {

    private String UyeMail;
    private String UyeID;
    private String UyeAdi;

    public UyeClass(){

    }

    public UyeClass(String uyeAdi,String uyeID,String uyeMail)
    {
        this.UyeAdi=uyeAdi;
        this.UyeMail=uyeMail;
        this.UyeID=uyeID;
    }

    public String getUyeAdi() {
        return this.UyeAdi;
    }

    public void setUyeAdi(String uyeAdi) {
        this.UyeAdi = uyeAdi;
    }

    public String getUyeID() {
        return this.UyeID;
    }

    public void setUyeID(String uyeID) {
        this.UyeID = uyeID;
    }

    public String getUyeMail() {
        return this.UyeMail;
    }

    public void setUyeMail(String uyeMail) {
        this.UyeMail = uyeMail;
    }

}
