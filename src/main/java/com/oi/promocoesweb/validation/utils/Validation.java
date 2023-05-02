/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.validation.utils;

import com.oi.promocoesweb.validation.enums.ValidationEnum;

/**
 *
 * @author mpma0
 */
public class Validation {

    public static final String DDD_WARN = "cell-ddd-warn";
    public static final String DDD_2X_WARN = "cell-ddd2x-warn";
    public static final String WAV_WARN = "cell-wav-warn";
    public static final String QTD_WAV_WARN = "cell-qtd-wav-warn";
    public static final String LIST_WARN = "cell-list-warn";
    public static final String KEY_WARN = "cell-key-warn";
    public static final String NORMAL = "cell-normal";

    public static String getWarnMessage(ValidationEnum validation, String fieldName, Object obj) {
        if (obj != null) {
            String value;
            switch (validation) {
                case SEQUENCIA_VALORES:
                    value = (String) obj;

                    if ("ddd".equals(fieldName)) {
                        if (!value.matches("([1-9][1-9])((,[1-9][1-9])+)?")) {
                            return DDD_WARN;
                        }
                    }
                    else{
                        if (value.contains("\n") || value.contains(" ") || value.contains(",,")) {
                            return LIST_WARN;
                        }
                    }
                    break;
                case NOME_WAV:
                    value = (String) obj;
                    String[] wavs = value.toUpperCase().split("\\|");

                    for (String wav : wavs) {
                        if (!wav.matches("([A-Z0-9_]+\\.WAV)((\\|[A-Z0-9_]+\\.WAV)+)?")) {
                            return WAV_WARN;
                        }
                    }
                    break;
                case VALOR_UNICO:
                    value = (String) obj;
                    
                    if ("id_bfpg".equals(fieldName)) {
                        if (!value.matches("([1-9][0-9])(\\.[1-9])([0-9])?")) {
                            return KEY_WARN;
                        }
                    }
                    else if("id_ofertaocs".equals(fieldName)){
                        if (!value.matches("([1-9][0-9][0-9][0-9][0-9][0-9][0-9])")) {
                            return KEY_WARN;
                        }
                    }
                    else if("id_campanhasiebel".equals(fieldName)){
                        if (!value.matches("([1-9]-[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])")) {
                            return KEY_WARN;
                        }
                    }
                    else if("id_ofertasiebel".equals(fieldName)){
                        if (!value.matches("([1-9]-[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])")) {
                            return KEY_WARN;
                        }
                    }
                    else if("idbeneficio".equals(fieldName)){
                        if (!value.matches("([1-9][0-9])")) {
                            return KEY_WARN;
                        }
                    }
                    else if("idprograma".equals(fieldName)){
                        if (!value.matches("([1-9])([0-9])?")) {
                            return KEY_WARN;
                        }
                    }
                    break;
            }
        }

        return "cell-normal";
    }
}
