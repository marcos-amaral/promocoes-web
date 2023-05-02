/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.oi.promocoesweb.template.entity.ConfigOfertasPre;
import com.oi.promocoesweb.template.utils.JsonUtils;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mpma0
 */
public class Teste {

    public static void main(String[] args) {
        Pattern COLUMN_PATTERN = Pattern.compile("([a-zA-Z0-9_]*)(!\\[(.*)\\])?(.*)?");
        String value = "NAOCLIENTEPROMOSEMANAL_INFORMAPROMO_![144]";
        Matcher matcher = COLUMN_PATTERN.matcher(value);

        String s = "NaoClientePromoSemanal_InformaPromo_USSD_50_73_GP1.wav".toUpperCase();
        if (value.contains("!")) {
            if (matcher.matches()) {
                System.out.println("MATCHES!!!!");
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(2));
                System.out.println(matcher.group(3));
                System.out.println(s.contains(matcher.group(1)));
                System.out.println(s.replace(matcher.group(1), "").contains(matcher.group(3)));

            }
        }
    }

    public static void main2(String[] args) throws IOException, ParseException {
        String json = "{\"id\":1,\"title\":\"Yellow Submarine\",\"releaseDate\":\"1969-01-17\",\"artist\":{\"id\":1,\"name\":\"The Beatles\"},\"label\":{\"id\":1,\"name\":\"Apple\"}}";
        ObjectMapper mapper = new ObjectMapper();
        try {
            Object jsonObject = mapper.readValue(json, Object.class);
            if (jsonObject instanceof java.util.ArrayList) {
                Object obj = ((java.util.ArrayList) jsonObject).get(0);
                String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
                System.out.println(prettyJson);
            } else {
                String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
                System.out.println(prettyJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void format(String s) {
        System.out.println(s);
        System.out.println("YYYY-MM-DD          : " + (s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")));
        System.out.println("YYYY-MM-DDTHH:mm:SS : " + (s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]")));
        System.out.println("long                : " + (s.matches("[0-9]+")));
        System.out.println("DD-MM-YY            : " + (s.matches("[0-3][0-9]-[0-1][0-9]-[0-2][0-9]")));
        System.out.println("------------------------------------------------------------");
    }

    private static Date parse(String s) throws ParseException {
        if (s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")) {
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        } else if (s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]")) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(s);
        } else if (s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9].[0-9]+(-[0][3][0][0])")) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(s);
        } else if (s.matches("[0-9]+")) {
            return new Date(Long.parseLong(s));
        } else if (s.matches("[0-3][0-9]-[0-1][0-9]-[0-2][0-9]")) {
            return new SimpleDateFormat("dd-MM-yy").parse(s);
        } else {
            throw new ParseException(s, 0);
        }

    }
}
