/**
 * RMSCOTT Prototyping code
 */

package rmscott.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Class DateUtil
 * 
 * @author rmscott
 * 
 *         To change this generated comment edit the template variable
 *         "typecomment": Window>Preferences>Java>Templates. To enable and
 *         disable the creation of type comments go to
 *         Window>Preferences>Java>Code Generation.
 */
final public class DateUtil {

	/**
	 * The first day of the year
	 */
	static public final int FIRST_DAY_OF_YEAR = 1;

	/**
	 * The number of days in a year.
	 */
	static public final int LAST_DAY_OF_YEAR = 365;

	/**
	 * The number of days in a year minus one.
	 */
	static public final int LAST_DAY_OF_YEAR_MINUS_ONE = 364;

	static public final int DAY_IN_MILLISECONDS = 1000 * 60 * 60 * 24;

	/**
	 * Constructor for this class.
	 * 
	 */
	private DateUtil() {
	}

	/**
	 * Method getFileTimeStamp
	 * 
	 * @return String
	 */
	static public String getFileTimeStamp() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer(cal.get(Calendar.YEAR));
		sb.append(cal.get(Calendar.YEAR));
		sb.append("-"); //$NON-NLS-1$
		sb.append(new Integer(cal.get(Calendar.MONTH) + 1));
		sb.append("-"); //$NON-NLS-1$
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(" "); //$NON-NLS-1$
		sb.append(cal.get(Calendar.HOUR));
		sb.append(":"); //$NON-NLS-1$
		sb.append(cal.get(Calendar.MINUTE));
		sb.append(":"); //$NON-NLS-1$
		sb.append(cal.get(Calendar.SECOND));
		sb.append("."); //$NON-NLS-1$
		sb.append(cal.get(Calendar.MILLISECOND));

		return sb.toString();

	} // end of getFileTimeStamp

	/**
	 * Method getLogTimeStamp
	 * 
	 * @return String
	 */
	static public String getLogTimeStamp() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer(Calendar.YEAR);
		sb.append(cal.get(Calendar.YEAR));
		sb.append("/"); //$NON-NLS-1$
		sb.append(cal.get(Calendar.MONTH));
		sb.append("/"); //$NON-NLS-1$
		sb.append(cal.get(Calendar.DAY_OF_MONTH));
		sb.append(" "); //$NON-NLS-1$
		sb.append(cal.get(Calendar.HOUR));
		sb.append(":"); //$NON-NLS-1$
		sb.append(cal.get(Calendar.MINUTE));
		sb.append(" "); //$NON-NLS-1$
		sb.append(cal.get(Calendar.SECOND));
		sb.append(" "); //$NON-NLS-1$
		sb.append(cal.get(Calendar.MILLISECOND));

		return sb.toString();

	} // end of getLogTimeStamp

	/**
	 * Method addOneDay adds one day to the day given.
	 * 
	 * @param pDate
	 *            A java.util.Date
	 * @return java.util.Date
	 */
	static public java.util.Date addOneDay(java.util.Date pDate) {

		java.util.Date utilDate = null;

		if (pDate != null) {
			utilDate = DateUtil.addDays(pDate, 1);
		}

		return utilDate;

	} // end of addOneDay

	/**
	 * Method minusOneDay minuses one day to the day given.
	 * 
	 * @param pDate
	 *            A java.util.Date
	 * @return java.util.Date
	 */
	static public java.util.Date minusOneDay(java.util.Date pDate) {

		java.util.Date utilDate = null;

		if (pDate != null) {
			utilDate = DateUtil.minusDays(pDate, 1);
		}

		return utilDate;

	} // end of minusOneDay

	/**
	 * Method addDays adds days to the day given.
	 * 
	 * @param pDate
	 *            A java.util.Date
	 * @param pNumDays
	 *            An int containing the number of days to add.
	 * @return java.util.Date
	 */
	static public java.util.Date addDays(java.util.Date pDate, int pNumDays) {

		java.util.Date utilDate = null;

		if (pNumDays < FIRST_DAY_OF_YEAR) {
			throw new RuntimeException("Programming Error: days must be > 1"); //$NON-NLS-1$
		} else if (pNumDays > LAST_DAY_OF_YEAR_MINUS_ONE) {
			throw new RuntimeException("Programming Error: days must be <= 364"); //$NON-NLS-1$
		}
		if (pDate != null) {
			int dayOfYear = 0;
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(pDate);
			dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			if ((dayOfYear + pNumDays) <= DateUtil.LAST_DAY_OF_YEAR) {
				cal.add(Calendar.DAY_OF_YEAR, pNumDays);
			} else {
				int days = dayOfYear + pNumDays - DateUtil.LAST_DAY_OF_YEAR;
				int year = cal.get(Calendar.YEAR);
				cal.set(Calendar.YEAR, year + 1);
				cal.set(Calendar.DAY_OF_YEAR, days);
			}
			utilDate = cal.getTime();
		}

		return utilDate;

	} // end of addDays

	/**
	 * Method minusDays minuses the number of days given.
	 * 
	 * @param pDate
	 *            A java.util.Date
	 * @param pNumDays
	 *            An int containing the number of days to minus
	 * @return java.util.Date
	 */
	static public java.util.Date minusDays(java.util.Date pDate, int pNumDays) {

		java.util.Date utilDate = null;

		if (pNumDays < FIRST_DAY_OF_YEAR) {
			throw new RuntimeException("Programming Error: days must be > 1"); //$NON-NLS-1$
		} else if (pNumDays > LAST_DAY_OF_YEAR_MINUS_ONE) {
			throw new RuntimeException("Programming Error: days must be <= 364"); //$NON-NLS-1$
		}
		if (pDate != null) {
			int dayOfYear = 0;
			GregorianCalendar cal = new GregorianCalendar();
			cal.setTime(pDate);

			dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
			if (dayOfYear > (DateUtil.FIRST_DAY_OF_YEAR + pNumDays)) {
				cal.add(Calendar.DAY_OF_YEAR, pNumDays);
			} else {
				int days = DateUtil.LAST_DAY_OF_YEAR + pNumDays;
				int year = cal.get(Calendar.YEAR);
				cal.set(Calendar.YEAR, year - 1);
				cal.set(Calendar.DAY_OF_YEAR, days);
			}
			utilDate = cal.getTime();
		}

		return utilDate;

	} // end of minusDays

	/**
	 * Method convertSQLDateToUtilDate
	 * 
	 * @param pSqlDate
	 *            A java.sql.Date
	 * @return java.util.Date
	 */
	static public java.util.Date convertSQLDateToUtilDate(java.sql.Date pSqlDate) {
		java.util.Date utilDate = null;

		if (pSqlDate != null) {
			new java.util.Date(pSqlDate.getTime());
		}

		return utilDate;

	} // end of convertSQLDateToUtilDate

	/**
	 * Method convertUtilDateToSQLDate
	 * 
	 * @param pUtilDate
	 *            A java.util.Date
	 * @return java.util.Date
	 */
	static public java.sql.Date convertUtilDateToSQLDate(
			java.util.Date pUtilDate) {
		java.sql.Date sqlDate = null;

		if (pUtilDate != null) {
			new java.sql.Date(pUtilDate.getTime());
		}
		return sqlDate;

	} // end of convertSQLDateToUtilDate

	/**
	 * The purpose of the getDateAsString is to get the data as a String in its
	 * current locale.
	 * 
	 * @param pDate
	 *            A java.util.Date
	 * @return java.util.Date
	 */
	static public String getDateAsString(java.util.Date pDate) {
		String returnString = null;
		if (pDate != null) {
			// Locale currentLocale = Locale.getDefault();
			// DateFormat dateFormatter = DateFormat.getDateInstance(
			// DateFormat.DEFAULT, currentLocale);
			// returnString = dateFormatter.format(pDate);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
			returnString = sdf.format(pDate);
		}
		return returnString;

	} // end of convertSQLDateToUtilDate

	/**
	 * The purpose of the getTimestampAsString is to get the Timestamp as String
	 * in its current locale
	 * 
	 * @param pTimestamp
	 *            A java.sql.Timestamp
	 * @param pLocale
	 * @return
	 */
	static public String getTimestampAsString(java.sql.Timestamp pTimestamp,
			Locale pLocale) {
		String returnString = null;
		if (pTimestamp != null) {
			if (Locale.US.equals(pLocale)) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy H:m:s"); //$NON-NLS-1$
				returnString = sdf.format(pTimestamp);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy H:m:s"); //$NON-NLS-1$
				returnString = sdf.format(pTimestamp);
			}
		}
		return returnString;
	}

	/**
	 * The purpose of the getDateAsString is to get the data as a String in its
	 * current locale.
	 * 
	 * @param pDate
	 *            A java.util.Date
	 * @return java.util.Date
	 * @param pLocale
	 */
	static public String getDateAsStringByLocale(java.util.Date pDate,
			Locale pLocale) {
		String returnString = null;
		if (pDate != null) {
			if (Locale.US.equals(pLocale)) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
				returnString = sdf.format(pDate);
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
				returnString = sdf.format(pDate);
			}
		}
		return returnString;

	} // getDateAsStringByLocale

	/**
	 * The purpose of the convertStringToDate is to covert the String data to a
	 * date
	 * 
	 * @param pDate
	 * @return java.sql.Date
	 * @throws ParseException
	 */
	static public java.sql.Date convertStringToSQLDate(String pDate)
			throws ParseException {
		java.sql.Date date = null;
		SimpleDateFormat sdf = null;
		if (pDate.indexOf("/") > -1) { //$NON-NLS-1$
			sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
		} else if (pDate.indexOf("-") > -1) { //$NON-NLS-1$
			sdf = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
		} else {
			sdf = new SimpleDateFormat();
		}
		date = new java.sql.Date(sdf.parse(pDate).getTime());

		return date;

	} // end of convertStringToDate

	/**
	 * The purpose of the convertStringToDate is to covert the String data to a
	 * date
	 * 
	 * @param pDate
	 * @return java.util.Date
	 * @throws ParseException
	 */
	static public java.util.Date convertStringToDate(String pDate)
			throws ParseException {
		java.util.Date date = null;
		SimpleDateFormat sdf = null;
		if (pDate.indexOf("/") > -1) { //$NON-NLS-1$
			sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
		} else if (pDate.indexOf("-") > -1) { //$NON-NLS-1$
			sdf = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
		} else {
			sdf = new SimpleDateFormat();
		}
		date = sdf.parse(pDate);

		return date;

	} // end of convertStringToDate

	/**
	 * Get the GregorianCalendar
	 * 
	 * @param pDate
	 * @return GregorianCalendar
	 * @throws ParseException
	 */
	static public java.util.GregorianCalendar getDateCalender(String pDate)
			throws ParseException {
		java.util.Date date = null;
		SimpleDateFormat sdf = null;
		java.util.GregorianCalendar cal = null;

		if (pDate.indexOf("/") > -1) { //$NON-NLS-1$
			sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
		} else if (pDate.indexOf("-") > -1) { //$NON-NLS-1$
			sdf = new SimpleDateFormat("dd-MM-yyyy"); //$NON-NLS-1$
		} else {
			sdf = new SimpleDateFormat();
		}
		date = sdf.parse(pDate);
		cal = new GregorianCalendar();
		cal.setTime(date);

		return cal;

	} // end of getDateCalender

	/**
	 * Get the current Timestamp
	 * 
	 * @return Timestamp
	 * @throws ParseException
	 */
	static public java.sql.Timestamp getTimeStamp() {
		Date date;
		SimpleDateFormat sdf = null;
		java.sql.Timestamp timeStampDate = null;
		try {
			sdf = new SimpleDateFormat();
			date = (Date) sdf.parse(sdf.format(new Date()));
			timeStampDate = new Timestamp(date.getTime());
		} catch (ParseException parseEx) {
		}
		return timeStampDate;
	}

	/**
	 * 
	 * @param dateRange
	 * @return
	 */
	public static Date[] getDateRange(String dateRange) {
		String[] terms = null;
		String lowerTerm = null;
		String upperTerm = null;
		Date[] dates = new Date[2];

		if (dateRange == null) {
			return dates;
		}

		terms = dateRange.split("_");

		if (terms == null || terms.length < 3) {
			return dates;
		}

		Calendar calendar = Calendar.getInstance();

		// Convert lower range to Date object, if it's value as NA then
		// treat as null.
		lowerTerm = terms[1];
		if (!"NA".equalsIgnoreCase(lowerTerm)) {
			try {
				calendar.add(Calendar.DATE, Integer.parseInt(lowerTerm));
				dates[0] = calendar.getTime();
			} catch (NumberFormatException e) {
				// do nothing.
			}
		}

		// Convert lower range to Date object, if it's value as NA then
		// treat as null.
		upperTerm = terms[2];
		if (!"NA".equalsIgnoreCase(upperTerm)) {
			try {
				calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, Integer.parseInt(upperTerm));
				dates[1] = calendar.getTime();
			} catch (NumberFormatException e) {
				// do nothing.
			}
		}
		return dates;
	}

	/**
	 * Get the message time difference
	 * 
	 * @param pStartDate
	 *            A starting date
	 * @throws ParseException
	 */
	static public String logTimeDifference(long pStartTime, String pMessage) {
		long timeDiff = 0;
		long endTime = System.currentTimeMillis();
		;
		timeDiff = endTime - pStartTime;
		double doubleTimeDiff = timeDiff / (double) 1000;
		StringBuffer sb = new StringBuffer(pMessage);
		sb.append(" in seconds : ");
		sb.append(doubleTimeDiff);
		return sb.toString();

	} // end of logTimeDifference

	/**
	 * returns the number of days since the start date and end date
	 * 
	 * @param pStartDate
	 * @param pEndDate
	 * @return int
	 */
	static public int daysBetweenStartAndEnd(Date pStartDate, Date pEndDate) {
		long timeDiff = pEndDate.getTime() - pStartDate.getTime();
		int days = (int) (timeDiff / (1000 * 60 * 60 * 24));
		return days;

	} // end of daysBetweenStartAndEnd

	/**
	 * returns the number of days since the start date and end date
	 * 
	 * @param pStartDate
	 * @param pEndDate
	 * @return int
	 */
	static public int daysBetweenStartAndEnd(long pStartTime, long pEndTime) {
		long timeDiff = pEndTime - pStartTime;
		int days = (int) (timeDiff / (1000 * 60 * 60 * 24));
		return days;

	} // end of daysBetweenStartAndEnd

	/**
	 * Checks if the two dates are equivalent
	 * 
	 * @param pFirstDate
	 * @param pSecondDate
	 * @return boolean
	 */
	static public boolean areDatesEquvalent(Date pFirstDate, Date pSecondDate) {
		if (pFirstDate == null) {
			if (pSecondDate != null)
				return false;
		} else if (!pFirstDate.equals(pSecondDate)) {
			return false;

		}
		return true;

	} // end areDatesEquvalent

	/**
	 * Checks if the java.sql.Timestamp objects are equivalent
	 * 
	 * @param pFirstTime
	 * @param pSecondTime
	 * @return boolean
	 */
	static public boolean areTimestampsEquvalent(Timestamp pFirstTime,
			Timestamp pSecondTime) {
		if (pFirstTime == null) {
			if (pSecondTime != null)
				return false;
		} else if (!pFirstTime.equals(pSecondTime)) {
			return false;

		}
		return true;

	} // end areTimestampsEquvalent

	/**
	 * Gets a long date as 1 month ago
	 * 
	 * @return
	 */
	static public long getOneYearAgoasLong() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		cal.set(Calendar.YEAR, (year - 1));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a long date as 1 month ago
	 * 
	 * @return
	 */
	static public long getOneMonthAgoasLong() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, (month - 1));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a long date as 1 month ago
	 * 
	 * @param pMonthsAgo
	 * @return
	 */
	static public long getMonthsAgoasLong(int pMonthsAgo) {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		cal.set(Calendar.MONTH, (month - pMonthsAgo));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a long date for 1 day ago
	 * 
	 * @return long
	 */
	static public long getOneDayAgoasLong() {
		Calendar cal = Calendar.getInstance();
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, (dayOfYear - 1));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a Date date for 1 day ago for current date
	 * 
	 * @return Date
	 */
	static public Date getOneDayAgoasDate() {
		long longTime = DateUtil.getOneDayAgoasLong();
		Date date = new Date(longTime);
		return date;
	}

	/**
	 * Gets a Date date for 1 day ago for current date
	 * 
	 * @param pNoTime
	 *            - boolean if true remove the time and keep just the date part
	 * @return Date
	 */
	static public Date getOneDayAgoasDate(boolean pNoTime) {
		long longTime = DateUtil.getOneDayAgoasLong();
		Date date = new Date(longTime);
		if (pNoTime) {
			date = DateUtil.getDateWithNoTime(date);
		}
		return date;
	}

	/**
	 * Takes a input date removes the time and returns that new date
	 * 
	 * @return Date
	 */
	static public Date getDateWithNoTime(Date pDate) {
		if (pDate == null)
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$
		Date newDate = null;
		try {
			newDate = sdf.parse(sdf.format(pDate));
		} catch (ParseException parseEx) {
		}
		return newDate;
	}

	/**
	 * Gets a long date for 1 day ago
	 * 
	 * @param pNumDays
	 * @return long
	 */
	static public long getDaysAgoasLong(int pNumDays) {
		Calendar cal = Calendar.getInstance();
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, (dayOfYear - pNumDays));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a long date x number of days ago from passed in date
	 * 
	 * @param pLongTime
	 * @param pNumDays
	 * @return long
	 */
	static public long getDaysAgoAsLong(long pLongTime, int pNumDays) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(pLongTime);
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, (dayOfYear - pNumDays));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a Date x number of days ago from passed in date
	 * 
	 * @param pNumDays
	 * @return Date
	 */
	static public Date getDaysAgoasDate(Date pDate, int pNumDays) {
		Date daysAgo = null;
		if (pDate != null) {
			long currentDateAsLong = pDate.getTime();
			long newTimeAsLong = DateUtil.getDaysAgoAsLong(currentDateAsLong,
					pNumDays);
			daysAgo = new Date(newTimeAsLong);
		}

		return daysAgo;
	}

	/**
	 * Gets a Date x number of days ago from the current date
	 * 
	 * @return Date
	 */
	static public Date getDaysAgoasDate(int pNumDays) {
		Date daysAgo = DateUtil.getDaysAgoasDate(new Date(), pNumDays);
		return daysAgo;
	}

	/**
	 * Gets a long date passed in hours ago
	 * 
	 * @param pHoursAgo
	 * @return long
	 */
	static public long getHoursAgoasLong(int pHoursAgo) {
		Calendar cal = Calendar.getInstance();
		int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
		cal.set(Calendar.HOUR_OF_DAY, (hourOfDay - pHoursAgo));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a long date passed in minutes ago
	 * 
	 * @param pMinutesAgo
	 * @return long
	 */
	static public long getMinutesAgoasLong(int pMinutesAgo) {
		Calendar cal = Calendar.getInstance();
		int minutesAgo = cal.get(Calendar.MINUTE);
		cal.set(Calendar.MINUTE, (minutesAgo - pMinutesAgo));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets a long date x number of days forward from passed in date
	 * 
	 * @param pNumDays
	 * @return long
	 */
	static public long getDaysForwardAsLong(Date pDate, int pNumDays) {
		long longTime = 0;
		if (pDate != null) {
			longTime = pDate.getTime();
			longTime = DateUtil.getDaysForwardAsLong(longTime, pNumDays);
		}

		return longTime;
	}

	/**
	 * Gets a Date x number of days forward from passed in date
	 * 
	 * @param pNumDays
	 * @return Date
	 */
	static public Date getDaysForwardAsDate(Date pDate, int pNumDays) {
		Date daysForward = null;
		if (pDate != null) {
			long currentDateAsLong = pDate.getTime();
			long newTimeAsLong = DateUtil.getDaysForwardAsLong(
					currentDateAsLong, pNumDays);
			daysForward = new Date(newTimeAsLong);
		}

		return daysForward;
	}

	/**
	 * Gets a Date x number of days forward from passed in date
	 * 
	 * @param pNumDays
	 * @return Date
	 */
	static public Date getDaysForwardAsDate(int pNumDays) {
		Date daysForward = DateUtil.getDaysForwardAsDate(new Date(), pNumDays);
		return daysForward;
	}

	/**
	 * Gets a long date x number of days forward from passed in date
	 * 
	 * @param pLongTime
	 * @param pNumDays
	 * @return long
	 */
	static public long getDaysForwardAsLong(long pLongTime, int pNumDays) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(pLongTime);
		int dayOfYear = cal.get(Calendar.DAY_OF_YEAR);
		cal.set(Calendar.DAY_OF_YEAR, (dayOfYear + pNumDays));
		long longTime = cal.getTimeInMillis();
		return longTime;
	}

	/**
	 * Gets the current date with no time
	 * 
	 * @return Date
	 */
	static public Date getCurrentDateNoTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); //$NON-NLS-1$

		Date currentDateWithZeroTime = new Date();
		try {
			currentDateWithZeroTime = sdf.parse(sdf
					.format(currentDateWithZeroTime));
		} catch (ParseException parseEx) {
		}
		return currentDateWithZeroTime;
	}
}
