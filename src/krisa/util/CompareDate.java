/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package krisa.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;

/**
 *
 * @author KZ
 */
public class CompareDate {
    public static int compare(long longpast){
        
        DateTime today =new DateTime();
        DateTime past = new DateTime(longpast);
        
      int days = Days.daysBetween(new DateTime(past), new DateTime(today)).getDays();
        return days;
    }
    public static void main(String[] args){
    CompareDate cd = new CompareDate();
        System.out.println(cd.compare(1000));
    }
}
