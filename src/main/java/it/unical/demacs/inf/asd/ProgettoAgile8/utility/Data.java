package it.unical.demacs.inf.asd.ProgettoAgile8.utility;

import java.util.ArrayList;

public class Data {
    public static String convertiData(String data){
        String[] giorni = data.split("T",2);
        String[] dataCorretta = giorni[0].split("-");
        String[] oreMinuti = giorni[1].split(":",2);
        var s =dataCorretta[2]+"-"+dataCorretta[1]+"-"+dataCorretta[0]+" alle ore "+oreMinuti[0]+":"+oreMinuti[1];
        return s;
    }
}
