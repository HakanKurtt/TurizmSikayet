package com.example.hakankurt.turizmsikayet;

/**
 * Created by FURKAN on 3.12.2017.
 */

public class Sikayet {
    public String Tarih,Baslik,Icerik,MarkaID,UyeID;

    public Sikayet(String Tarih,String Baslik,String Icerik,String MarkaID,String UyeID)
    {
        this.Baslik=Baslik;
        this.Icerik=Icerik;
        this.Tarih=Tarih;
        this.MarkaID=MarkaID;
        this.UyeID=UyeID;
    }
}
