/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oi.promocoesweb.template.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mpma0
 */
public class DateUtils {
    public static Date parse(String s) throws ParseException, Exception{
        if(s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]")){
            return new SimpleDateFormat("yyyy-MM-dd").parse(s);
        }
        else if(s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]")){
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(s);
        }
        else if(s.matches("[2][0][0-9][0-9]-[0-1][0-9]-[0-3][0-9]T[0-2][0-9]:[0-5][0-9]:[0-5][0-9].[0-9]+(-[0][3][0][0])")){
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse(s);
        }
        else if(s.matches("[0-9]+")){
            return new Date(Long.parseLong(s));
        }
        else if(s.matches("[0-3][0-9]-[0-1][0-9]-[0-2][0-9]")){
            return new SimpleDateFormat("dd-MM-yy").parse(s);
        }
        else if(s.matches("[0-3][0-9]/[0-1][0-9]/[0-2][0-9]")){
            return new SimpleDateFormat("dd/MM/yy").parse(s);
        }
        else{
            throw new ParseException(s,0);
        }
    }
}
