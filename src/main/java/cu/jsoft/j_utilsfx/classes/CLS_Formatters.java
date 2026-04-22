/*
 * Copyright Joe1962
 * https://github.com/Joe1962
 */
package cu.jsoft.j_utilsfx.classes;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;

/**
 *
 * @author joe1962
 */
public class CLS_Formatters {
	public final static SimpleDateFormat FormatDate = new SimpleDateFormat("yyyy-MM-dd");
	public final static NumberFormat FormatCurr = NumberFormat.getCurrencyInstance();
	public final static NumberFormat FormatCurrNoGrouping = NumberFormat.getCurrencyInstance();
	public final static NumberFormat FormatInt = NumberFormat.getIntegerInstance();
	public final static NumberFormat FormatDec = NumberFormat.getNumberInstance();
	public final static NumberFormat FormatDecNoGrouping = NumberFormat.getNumberInstance();

	// Static initializer block - runs once when class is first referenced
	static {
		FormatInt.setGroupingUsed(true);

		FormatCurr.setCurrency(Currency.getInstance(Locale.US));
		FormatCurr.setGroupingUsed(true);

		FormatCurrNoGrouping.setCurrency(Currency.getInstance(Locale.US));
		FormatCurrNoGrouping.setGroupingUsed(false);

		FormatDec.setMinimumFractionDigits(2);
		FormatDec.setMaximumFractionDigits(2);
		FormatDec.setMinimumIntegerDigits(1);
		FormatDec.setGroupingUsed(true);

		FormatDecNoGrouping.setMinimumFractionDigits(2);
		FormatDecNoGrouping.setMaximumFractionDigits(2);
		FormatDecNoGrouping.setMinimumIntegerDigits(1);
		FormatDecNoGrouping.setGroupingUsed(false);
	}

}
