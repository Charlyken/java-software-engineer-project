package com.esigelec.service;

public class HoraireValidator {

    public HoraireValidator() {
    }

    public static boolean isHoraireValide(String heureDebut, String heureFin){
        if (heureDebut == null || heureFin == null){
            return false;
        }

        boolean heuresMatin = heureDebut.equals("08:30")
                && heureFin.equals("12:30");
        boolean heuresAprem = heureDebut.equals("13:30")
                && heureFin.equals("17:30");

        return heuresMatin || heuresAprem;
    }
    
}
