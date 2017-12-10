package com.example.hakankurt.turizmsikayet;

/**
 * Created by FURKAN on 3.12.2017.
 */

public class SikayetClass {
    public String Tarih;
    public String Baslik;
    public String Icerik;
    private String MarkaID;
    private String UyeID;
    private String UyeAdi;
    private String SikayetID;

    public SikayetClass()
    {

    }

    public SikayetClass(String Tarih, String Baslik, String Icerik, String MarkaID, String UyeID,String UyeAdi,String SikayetID)
    {
        this.Baslik=Baslik;
        this.Icerik=Icerik;
        this.Tarih=Tarih;
        this.MarkaID=MarkaID;
        this.UyeID=UyeID;
        this.UyeAdi=UyeAdi;
    }

    public String getMarkaID() {
        return MarkaID;
    }

    public void setMarkaID(String markaID) {
        MarkaID = markaID;
    }

    public String getUyeID() {
        return UyeID;
    }

    public void setUyeID(String uyeID) {
        UyeID = uyeID;
    }

    public String getUyeAdi() {
        return UyeAdi;
    }

    public void setUyeAdi(String uyeAdi) {
        UyeAdi = uyeAdi;
    }

    public String getSikayetID()
    {
        return this.SikayetID;
    }
    public void setSikayetID(String sikayetID)
    {
        this.SikayetID=sikayetID;
    }

}
