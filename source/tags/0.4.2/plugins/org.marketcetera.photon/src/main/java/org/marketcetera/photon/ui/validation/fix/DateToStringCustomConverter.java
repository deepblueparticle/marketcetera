package org.marketcetera.photon.ui.validation.fix;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.core.databinding.conversion.Converter;

public class DateToStringCustomConverter extends Converter {
	public static final String MONTH_FORMAT = "MMM";

	public static final String SHORT_YEAR_FORMAT = "yy";

	public static final String LONG_YEAR_FORMAT = "yyyy";

	private SimpleDateFormat formatter;

	private boolean forceUppercase;

	/**
	 * Force uppercase for converted values. 
	 */
	public DateToStringCustomConverter(String dateFormatStr) {
		this(dateFormatStr, true);
	}
	
	/**
	 * @param dateFormatStr
	 *            a date format string of the form specified by
	 *            SimpleDateFormat. Prefer using the predefined date formats in
	 *            this class.
	 * @param forceUppercase
	 *            when true, all values will be returned as toUpperCase
	 */
	public DateToStringCustomConverter(String dateFormatStr, boolean forceUppercase) {
		super(java.util.Date.class, String.class);
		this.formatter = new SimpleDateFormat(dateFormatStr);
		this.forceUppercase = forceUppercase;
	}

	public Object convert(Object fromObject) {
		if (fromObject == null) {
			return null;
		}
		if (!(fromObject instanceof Date)) {
			throw new IllegalArgumentException("The value: " + fromObject
					+ " is not a valid date.");
		}
		Date fromDate = (Date) fromObject;
		String toDateString = null;
		synchronized (formatter) {
			toDateString = formatter.format(fromDate);
		}
		if (forceUppercase) {
			toDateString = toDateString.toUpperCase();
		}
		return toDateString;
	}

}