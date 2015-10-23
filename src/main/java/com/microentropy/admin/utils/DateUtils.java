package com.microentropy.admin.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title.
 * <p/>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2015-08-19
 */
public class DateUtils {
    public static final String AM_PM_DATETIME_FORMAT = "MM/dd/yyyy hh:mm a";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static Timestamp parseAMPMFormat(String datetimeString) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(AM_PM_DATETIME_FORMAT);
        final Date date = dateFormat.parse(datetimeString);
        return new Timestamp(date.getTime());
    }

    public static String formatDatetime(Date datetime) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
        return dateFormat.format(datetime);
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT).format(date);
    }
}
