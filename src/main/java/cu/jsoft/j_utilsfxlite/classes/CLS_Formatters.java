/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfxlite.classes;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author joe1962
 */
public class CLS_Formatters {
	public static SimpleDateFormat FormatDate = new SimpleDateFormat("yyyy-MM-dd");
	public static NumberFormat FormatCurr = NumberFormat.getCurrencyInstance();
	public static NumberFormat FormatInt = NumberFormat.getIntegerInstance();
	public static NumberFormat FormatDec = NumberFormat.getNumberInstance();
	public static NumberFormat FormatDecNoGrouping = NumberFormat.getNumberInstance();
	static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

	public static String getTodayString(SimpleDateFormat MyFormatter) {
		return MyFormatter.format(Calendar.getInstance().getTime());
	}

	public CLS_Formatters() {
		//FormatDate = new SimpleDateFormat("yyyy-MM-dd");

		//FormatCurr = NumberFormat.getCurrencyInstance();

		//FormatInt = NumberFormat.getIntegerInstance();
		FormatInt.setGroupingUsed(true);

		//FormatDec = NumberFormat.getNumberInstance();
		FormatDec.setMinimumFractionDigits(2);
		FormatDec.setMaximumFractionDigits(2);
		FormatDec.setMinimumIntegerDigits(1);
		FormatDec.setGroupingUsed(true);

		//FormatDecNoGrouping = NumberFormat.getNumberInstance();
		FormatDecNoGrouping.setMinimumFractionDigits(2);
		FormatDecNoGrouping.setMaximumFractionDigits(2);
		FormatDecNoGrouping.setMinimumIntegerDigits(1);
		FormatDecNoGrouping.setGroupingUsed(false);
	}



	public static CLS_Formatters getInstance() {
		return CLS_FormattersHolder.INSTANCE;
	}
	
	private static class CLS_FormattersHolder {
		private static final CLS_Formatters INSTANCE = new CLS_Formatters();
	}

}
