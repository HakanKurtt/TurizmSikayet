package com.example.hakankurt.turizmsikayet;

/**
 * Created by FURKAN on 3.12.2017.
 */

public class Sikayet {
    public String Tarih;
    public String Baslik;
    public String Icerik;
    private String MarkaID;
    private String UyeID;

    public Sikayet()
    {

    }

    public Sikayet(String Tarih,String Baslik,String Icerik,String MarkaID,String UyeID)
    {
        this.Baslik=Baslik;
        this.Icerik=Icerik;
        this.Tarih=Tarih;
        this.MarkaID=MarkaID;
        this.UyeID=UyeID;
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


}
