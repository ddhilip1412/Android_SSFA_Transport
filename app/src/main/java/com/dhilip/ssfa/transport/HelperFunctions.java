package com.dhilip.ssfa.transport;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Dhilip on 6/11/2016.
 */
public class HelperFunctions
{
    public static Date GetDateFrom(String dateString, String dateFormat)
    {
        SimpleDateFormat currentDateFormat = new SimpleDateFormat(dateFormat);
        Date convertedDate = new Date();
        try
        {
            convertedDate = currentDateFormat.parse(dateString);
        } catch (ParseException e)
        {
//             TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public static String GetStringFrom(Date date, String dateFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String convertedString = formatter.format(date);
        return convertedString;
    }
}
