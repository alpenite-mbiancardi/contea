package com.alpenite.tea.documentale.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateCommon {

	public static final String DB_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";
	public static final String USER_DATE_FORMAT = "dd/MM/yyyy";
	public static final String USER_DATE_FORMAT2 = "dd-MMM-yyyy";
	public static final String CALENDAR_DATE_FORMAT = "EEE MMM dd HH:mm:ss z yyyy";
	public static final String JSON_DATE_FORMAT = "EEE MMM dd HH:mm:ss 'CET' yyyy";

	public static String convertStringDateToStringDB(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(CALENDAR_DATE_FORMAT,
				Locale.US);

		try {
			final Date correctDate = sdf.parse(stringDate);
			sdf = new SimpleDateFormat(DB_DATE_FORMAT);
			return sdf.format(correctDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String convertFormatDate(Date userDate) {

		SimpleDateFormat sdf = new SimpleDateFormat(CALENDAR_DATE_FORMAT);

		sdf = new SimpleDateFormat(USER_DATE_FORMAT);
		return sdf.format(userDate);
	}

	public static Integer convertDateToIntegerDB(Date userDate) {
		if (userDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(CALENDAR_DATE_FORMAT,
					Locale.US);

			sdf = new SimpleDateFormat(DB_DATE_FORMAT);
			return Integer.valueOf(sdf.format(userDate));
		} else {
			return null;
		}
	}

	public static String convertStringDBToStringUser(String stringDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(DB_DATE_FORMAT);
		try {
			final Date correctDate = sdf.parse(stringDate);
			sdf = new SimpleDateFormat(USER_DATE_FORMAT);
			return sdf.format(correctDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String convertStringUserToStringDb(String stringDate, boolean isfirst) {
		SimpleDateFormat sdf = new SimpleDateFormat(USER_DATE_FORMAT);
		try {
			System.out.println(" stringDate -------------------------------: "
					+ stringDate);
			
			if (stringDate != null) {
				Calendar calCorrectDate = Calendar.getInstance();
				
				final Date correctDate = sdf.parse(stringDate);
				calCorrectDate.setTime(correctDate);
				
				Calendar now = Calendar.getInstance();
				
				/*calCorrectDate.add(Calendar.HOUR,now.get(Calendar.HOUR_OF_DAY));
				calCorrectDate.add(Calendar.MINUTE,now.get(Calendar.MINUTE));
				calCorrectDate.add(Calendar.SECOND,now.get(Calendar.SECOND));
				calCorrectDate.add(Calendar.MILLISECOND,now.get(Calendar.MILLISECOND));*/
				
				if(!isfirst){
					calCorrectDate.add(Calendar.HOUR,23);
					calCorrectDate.add(Calendar.MINUTE,now.get(Calendar.MINUTE));
					calCorrectDate.add(Calendar.SECOND,now.get(Calendar.SECOND));
					calCorrectDate.add(Calendar.MILLISECOND,now.get(Calendar.MILLISECOND));
				}
				
				sdf = new SimpleDateFormat(DateCommon.DB_DATE_FORMAT);
				
				String s = sdf.format(calCorrectDate.getTime());
				
				String result = s + "+01:00";
				
				System.out.println(" result date -------------------------------: "
						+ result);
				
				return result;
			} else {
				return null;
			}

		} catch (ParseException e) {
			return null;
		}
	}

	public static String convertIntegerDBToStringUser(Object objectDate) {
		if (objectDate instanceof Integer) {
			return convertStringDBToStringUser(String.valueOf(objectDate));
		}
		return null;
	}

	public static Date convertDateDBToStringUser(Object objectDate) {
		if (objectDate instanceof Integer) {
			return convertGeneraleStringDateToDate(String.valueOf(objectDate));
		}
		return null;
	}

	public static Integer getIncrMonthsToDB(Integer montsIncr) {
		Date current = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.add(Calendar.MONTH, montsIncr);
		current = cal.getTime();
		return convertDateToIntegerDB(current);
	}

	public static Date convertStringDateToDate(String stringDate, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(stringDate);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date convertStringToDate(String stringDate) {
		System.out.println("convertStringToDate  stringDate -------------------------------: "
				+ stringDate);
		
		DateFormat df = new SimpleDateFormat("y-m-d");
		Date result = null;

		if (df != null) {
			try {
				result = df.parse(stringDate);
			} catch (ParseException e) {
				result = null;
			}
		}

		return result;
	}

	public static Date convertGeneraleStringDateToDate(String stringDate) {
		SimpleDateFormat sdf = null;
		Date result = null;

		if (stringDate.length() == DB_DATE_FORMAT.length()) {
			sdf = new SimpleDateFormat(DB_DATE_FORMAT);
		} else if (stringDate.length() == USER_DATE_FORMAT.length()) {
			sdf = new SimpleDateFormat(USER_DATE_FORMAT);
		} else if (stringDate.length() == CALENDAR_DATE_FORMAT.length()) {
			sdf = new SimpleDateFormat(CALENDAR_DATE_FORMAT);
		} else if (stringDate.length() == JSON_DATE_FORMAT.length()) {
			sdf = new SimpleDateFormat(JSON_DATE_FORMAT);
		}

		if (sdf != null) {
			try {
				result = sdf.parse(stringDate);
			} catch (ParseException e) {
				result = null;
			}
		}

		return result;
	}

	/**
	 * Converte il bigdecimal in date
	 * 
	 * @param bd
	 * @return
	 */
	public static Date convertBigDecimalToDate(BigDecimal bd) {
		BigDecimal dayFrom1970 = bd.subtract(new BigDecimal(2440588));
		BigDecimal time = dayFrom1970.multiply(new BigDecimal(86400000));
		Date date = new Date();
		date.setTime(time.longValue());
		return date;
	}

	public static boolean isOuPeoplesoftCode(String code) {
		/* deve iniziare con IT ed essere alfanumerico da 6 caratteri */
		String pattern = "[I]{1}[T]{1}[a-zA-Z_0-9]{4}";
		return code.matches(pattern);
	}

	public static String convertDateToString(String pattern, Date fromDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.US);
		return formatter.format(fromDate);
	}

	public static Date removeTimeFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		// Set time fields to zero
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	public static Date nextDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		cal.add(Calendar.DAY_OF_YEAR, 1); // <--

		return cal.getTime();
	}

	public static boolean isValidDate(String pattern, String inDate) {

		if (inDate == null)
			return false;

		// set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		if (inDate.trim().length() != dateFormat.toPattern().length())
			return false;

		dateFormat.setLenient(false);

		try {
			dateFormat.parse(inDate.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public static boolean isGreaterThan(Date date1, Date date2) {
		if (date1 != null && date2 != null)
			if (date1.compareTo(date2) > 0) {
				return true;
			}
		return false;
	}

	public static boolean isEq(Date date1, Date date2) {
		if (date1 != null && date2 != null)
			if (date1.compareTo(date2) == 0) {
				return true;
			}
		return false;
	}

	/**
	 * Metodo di utilit� che restituisce un timestamp rappresentante l'istante
	 * di chiamata.
	 * 
	 * @return il timestamp generato.
	 */
	public static Timestamp getTimestamp() {
		Timestamp timestamp = new Timestamp(Calendar.getInstance()
				.getTimeInMillis());
		return timestamp;
	}
}
