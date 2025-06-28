/*
 * Copyright Joe1962
 */
package cu.jsoft.j_utilsfxlite.subs;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author informaticos
 */
public class SUB_UtilsDateTime {
	private static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

	/**
	 *
	 * @param from the value of from
	 * @param to the value of to
	 * @return the int
	 */
	public static int DiffInDays(Date from, Date to) {
		return (int) ((to.getTime() - from.getTime()) / MILLISECONDS_IN_DAY);
	}

	/**
	 *
	 * @return
	 */
	public static Date getTodayDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 *
	 * @param MyFormatter the value of MyFormatter
	 * @return
	 */
	public static String getTodayString(SimpleDateFormat MyFormatter) {
		return MyFormatter.format(Calendar.getInstance().getTime());
	}

	public static String getNowForFilename() {
		SimpleDateFormat MyFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		//return MyFormatter.format(Instant.now());
		return MyFormatter.format(getTodayDate());
	}

    public static LocalDate daysAfter(LocalDate theDate, int days) {
		  return theDate.plusDays(days);
    }

	public static Timestamp getCurrTimestampSQL() {
		return new Timestamp(System.currentTimeMillis());
	}

	private static Timestamp DateSQL2TimestampSQL(Date MyDate, String MyTime) throws ParseException {
		String strDate = new SimpleDateFormat("yyyy-MM-dd").format(MyDate);
		Date tmpDate = new Date();
		tmpDate = new SimpleDateFormat("yyyy-MM-dd'_'HH:mm:ss").parse(strDate + "_" + MyTime);
		return new Timestamp(tmpDate.getTime());
	}

	public static String TimestampSQL2TimeString(Timestamp MyTimestamp) {
		Calendar MC = Calendar.getInstance();
		MC.setTimeInMillis(MyTimestamp.getTime());
		int MyHours = MC.get(Calendar.HOUR_OF_DAY);
		int MyMinutes = MC.get(Calendar.MINUTE);
		int MySeconds = MC.get(Calendar.SECOND);
		return String.valueOf(MyHours) + ":" + String.valueOf(MyMinutes) + ":" + String.valueOf(MySeconds);
	}

	public static java.sql.Date JavaDate2SQLDate(Date MyDate) {
		return new java.sql.Date(MyDate.getTime());
	}

	/**
	 *
	 * @param javadate the value of javadate
	 * @return 
	 */
	public static java.sql.Date java2sqlDate(Date javadate) {
		return java.sql.Date.valueOf(String.format("%1$tY-%1$tm-%1$te", javadate));
	}

	/**
	 *
	 * @param sqldate the value of sqldate
	 * @return 
	 */
	public static Date sql2javaDate(java.sql.Date sqldate) {
		Date myDate = java.sql.Date.valueOf(sqldate.toString());
		return myDate;
	}

}
