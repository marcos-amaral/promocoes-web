package com.contax.templateweb.util;

/**
 * @author alexander.junior
 */
public class TimeOffSetUtil {

    public static String getDescricao(String timeOffset) {
        switch (timeOffset) {
            case "day":
                timeOffset = "Dados do dia";
                break;
            case "hour":
                timeOffset = "Dados última hora";
                break;
            case "min30":
                timeOffset = "Dados 30 minutos";
                break;
            case "min15":
                timeOffset = "Dados 15 minutos";
                break;
            case "min5":
                timeOffset = "Dados 5 minutos";
                break;
        }
        return timeOffset;
    }
}
