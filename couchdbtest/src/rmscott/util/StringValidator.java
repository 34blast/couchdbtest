/**
 * RMSCOTT Prototyping code
 */

package rmscott.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of the StringValidator class is to provide a generic validation
 * for Strings beyond what iws provided with the Java language.
 * 
 * @invariant $none
 * @author Richard M. Scott
 * @see java.lang.String
 */

public class StringValidator {
	static public final int SPACES_4 = 4;
	/**
	 * The EOL fied is a windows end of line String used for formatting toString
	 * methods.
	 */
	static public final String EOL = System.getProperty("line.separator");

	static public final String FILE_SAPARATOR = System
			.getProperty("file.separator");

	/**
	 * Constant EMPTY_STRING
	 */
	static public final String EMPTY_STRING = ""; //$NON-NLS-1$

	/**
	 * Constant QUOTE
	 */
	static public final char QUOTE = '"'; //$NON-NLS-1$

	/**
	 * Constant SINGLE_QUOTE
	 */
	static public final String SINGLE_QUOTE = "'"; //$NON-NLS-1$

	/**
	 * Constant SINGLE_QUOTE
	 */
	static public final char SPACE = ' '; //$NON-NLS-1$

	/**
	 * Constant SINGLE_QUOTE
	 */
	static public final char UNDERSCORE = '_'; //$NON-NLS-1$

	/**
	 * Constant TAB
	 */
	static public final char TAB = '\t'; // $NON_NLS-1$

	/**
	 * Constant CARRIAGE_RETURN
	 */
	static public final char CARRIAGE_RETURN = '\r'; //$NON-NLS-1$

	/**
	 * Constant COLON
	 */
	static public final char COLON = ':'; //$NON-NLS-1$
	/**
	 * Constant COLON
	 */
	static public final char LESS_THEN_CHARACTER = '<'; //$NON-NLS-1$
	/**
	 * Constant COLON
	 */
	static public final char GT_THEN_CHARACTER = '>'; //$NON-NLS-1$

	/**
	 * Constant HTML_COMMENT_START
	 */
	static public final String HTML_COMMENT_START = "<!--"; //$NON-NLS-1$
	/**
	 * Constant HTML_COMMENT_END
	 */
	static public final String HTML_COMMENT_END = "-->"; //$NON-NLS-1$

	/**
	 * Constant COLON
	 */
	static public final char COMMA = ','; //$NON-NLS-1$

	/**
	 * Constant LEFT_PAREN
	 */
	static public final char LEFT_PAREN = '('; //$NON-NLS-1$

	/**
	 * Constant RIGHT_PAREN
	 */
	static public final char RIGHT_PAREN = ')'; //$NON-NLS-1$

	/**
	 * Constant LINEFEED
	 */
	static public final char LINEFEED = '\n'; //$NON-NLS-1$

	/**
	 * Constant LINEFEED
	 */
	static public final char PERIOD = '.'; //$NON-NLS-1$

	/**
	 * Constant LINEFEED
	 */
	static public final char EXCLAMATION = '!'; //$NON-NLS-1$

	/**
	 * Constant QUESTIONMARK
	 */
	static public final char QUESTIONMARK = '?'; //$NON-NLS-1$

	/**
	 * Constant FEED
	 */
	static public final char FEED = '\f'; //$NON-NLS-1$

	/**
	 * Constant DASH
	 */
	static public final char DASH = '-'; //$NON-NLS-1$

	/**
	 * Constant SEMICOLON
	 */
	static public final char SEMICOLON = ';'; //$NON-NLS-1$

	/**
	 * Constant String of charcters invalid in a query parameter
	 */
	public static String invalidCharacters = "`~!@#$%^&*()+=-[]\\\';/{}|\":<>?";

	/**
	 * The purpose of the StringValidator method is to provide a default private
	 * constructor for this class.
	 */
	private StringValidator() {
	}

	/**
	 * The purpose of the isEmpty method is to return indication as to whether
	 * or not the given String is empty.
	 * 
	 * @pre $none
	 * @post $none
	 * 
	 * @param pString
	 *            A String containing the string to operate on.
	 * @return A boolean indicating if the String is empty.
	 */
	static public boolean isEmpty(String pString) {
		boolean isEmpty = false;

		if (pString == null || pString.trim().length() < 1) {
			isEmpty = true;
		}
		return isEmpty;

	} // end of isEmpty

	/**
	 * The purpose of the trim method trims the string if not null
	 * 
	 * @pre $none
	 * @post $none
	 * 
	 * @param pString
	 *            A String containing the string to operate on.
	 * @return A boolean indicating if the String is empty.
	 */
	static public String trim(String pString) {
		String returnString = null;

		if (pString != null) {
			returnString = pString.trim();
		}

		return returnString;

	} // end of trim

	/**
	 * The purpose of the replaceSpacesWith_ method is replace all spaces with
	 * an underscore.
	 * 
	 * @pre $none
	 * @post $none
	 * 
	 * @param pString
	 *            A String containing the string to operate on.
	 * @return A String with underscores instead of spaces.
	 */
	static public String replaceSpacesWith_(String pString) {
		String returnVal = pString;

		if (!StringValidator.isEmpty(pString)) {
			int len = pString.length();
			char[] charArray = new char[len];
			for (int idx = 0; idx < len; idx++) {
				char tempChar = pString.charAt(idx);
				if (tempChar == StringValidator.SPACE) {
					charArray[idx] = StringValidator.UNDERSCORE;
				} else {
					charArray[idx] = tempChar;
				}
			}
			returnVal = new String(charArray);
		}

		return returnVal;
	}

	/**
	 * The purpose of the replaceSpacesWith_ method is replace all spaces with
	 * an underscore.
	 * 
	 * @pre $none
	 * @post $none
	 * 
	 * @param pString
	 *            A String containing the string to operate on.
	 * @return A String with underscores instead of spaces.
	 */
	static public String replaceCommaWith_(String pString) {
		String returnVal = pString;

		if (!StringValidator.isEmpty(pString)) {
			int len = pString.length();
			char[] charArray = new char[len];
			for (int idx = 0; idx < len; idx++) {
				char tempChar = pString.charAt(idx);
				if (tempChar == StringValidator.COMMA) {
					charArray[idx] = StringValidator.UNDERSCORE;
				} else {
					charArray[idx] = tempChar;
				}
			}
			returnVal = new String(charArray);
		}

		return returnVal;
	}

	/**
	 * The purpose of the appendIndex method is add the passed in index to the
	 * string.
	 * 
	 * @pre $none
	 * @post $none
	 * 
	 * @param pString
	 *            A String containing the string to operate on.
	 * @param pIdx
	 *            An int index.
	 * @return String
	 */
	static public String appendIndex(String pString, int pIdx) {
		String returnVal = null;

		if (!StringValidator.isEmpty(pString)) {
			StringBuffer sb = new StringBuffer(pString);
			sb.append(" : "); //$NON-NLS-1$
			sb.append(pIdx);
			returnVal = sb.toString();
		}

		return returnVal;

	} // end of appendIndex

	/**
	 * The purpose of the prependIndex method is prepend the passed in index to
	 * the string.
	 * 
	 * @pre $none
	 * @post $none
	 * 
	 * @param pString
	 *            A String containing the string to operate on.
	 * @param pIdx
	 *            An int index.
	 * @return String
	 */
	static public String prependIndex(String pString, int pIdx) {
		String returnVal = null;

		if (!StringValidator.isEmpty(pString)) {
			String indexString = Integer.toString(pIdx);
			StringBuffer sb = new StringBuffer(indexString);
			sb.append(" : "); //$NON-NLS-1$
			sb.append(pString);
			returnVal = sb.toString();
		}

		return returnVal;

	} // end of prependIndex

	/**
	 * Takes a String of comma-delimited values and creates an array of String
	 * objects.
	 * 
	 * @param pIds
	 * @return String[]
	 */
	public static String[] buildArrayOfStrings(String pIds) {
		String[] vos = null;
		if (pIds != null) {
			StringTokenizer st = new StringTokenizer(pIds, ","); /* NOI18N */
			if (st.countTokens() > 0) { /* NOI18N */
				vos = new String[st.countTokens()]; /* NOI18N */
				for (int i = 0; st.hasMoreTokens(); i++) { /* NOI18N */
					vos[i] = st.nextToken().trim(); /* NOI18N */
				}
			}
		}
		return vos;
	}

	/**
	 * removeHTMLComment removes the HTML comment from the given string.
	 * 
	 * @param pString
	 * @return String
	 */
	public static String removeHTMLComment(String pString) {
		if (pString == null) {
			return null;
		}

		if (pString.indexOf(HTML_COMMENT_START) == -1) { /* NOI18N */
			return pString;
		} else {
			while (pString.indexOf(HTML_COMMENT_START) != -1) { /* NOI18N */

				pString = removeHTMLCommentRecurse(pString);
			}
		}

		return pString;
	}

	/**
	 * Check whether a string is null or empty string
	 * 
	 * @param pStr
	 *            String
	 * @return boolean
	 */
	public static boolean isNullorEmpty(String pStr) {
		if (pStr == null || "".equals(pStr.trim()) || //$NON-NLS-1$
				pStr.trim().length() < 1
				|| "null".equalsIgnoreCase(pStr.trim())) { //$NON-NLS-1$
			return true;
		}
		return false;
	}

	/**
	 * This is a recurse function used by removeHTMLComment to remove the HTML
	 * comment part from the string.
	 * 
	 * @param pString
	 * @return
	 */
	private static String removeHTMLCommentRecurse(String pString) {
		// check if start of HTML comment (<!--) exist
		if (pString.indexOf(HTML_COMMENT_START) != -1) { /* NOI18N */
			// get the index of start html comment(<!--)
			int startCommentIndex = pString.indexOf(HTML_COMMENT_START); /* NOI18N */

			// check if end of HTML comment (-->) exist
			if (pString.substring(startCommentIndex).indexOf(HTML_COMMENT_END) != -1) { /* NOI18N */
				// get the index of end html comment(<!--)
				int endCommentIndex = startCommentIndex
						+ pString.substring(startCommentIndex).indexOf(
								HTML_COMMENT_END); /* NOI18N */

				if (startCommentIndex == 0) {
					// if startCommentIndex == 0 then get entire string after
					// end comment
					pString = pString.substring(endCommentIndex + 3); /* NOI18N */
				} else {
					// if startCommentIndex != 0 then get string before start
					// index + after end index
					String temp = ""; //$NON-NLS-1$
					temp = pString.substring(0, startCommentIndex); /* NOI18N */
					temp = temp + pString.substring(endCommentIndex + 3); /* NOI18N */
					pString = temp;
				}
			} else {
				// if end of HTML comment (-->) does not exist then get the
				// string up to start of comment.
				pString = pString.substring(0, startCommentIndex); /* NOI18N */
			}
		}

		return pString;
	}

	/**
	 * This method validates if the string pEmail is a valid email id.
	 * 
	 * @param pEmail
	 * @return true if valid email id. Else false.
	 */
	public static boolean isValidEmailString(String pEmail) {
		// Regular Expression for Email Address validation
		Pattern p = Pattern
				.compile("^[\\w_\\-%\\.]+@[\\w_\\-%\\.]+\\.[a-zA-Z]{2,6}$");

		Matcher m = p.matcher(pEmail);

		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method converts the InputStream to String.
	 * 
	 * @param is
	 * @return
	 * @throws Exception
	 */
	public static String convertStreamToString(InputStream is)
			throws IOException {
		BufferedReader reader = null;
		StringBuffer sb = null;
		String line = null;

		reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		sb = new StringBuffer();

		while ((line = reader.readLine()) != null) {
			sb.append(line).append(EOL);
		}
		is.close();
		return sb.toString();
	}

	/**
	 * This method adds the data section to the given tag in the given xml.
	 * 
	 * @param pXML
	 * @param pTag
	 * @param pValue
	 * @param pClosed
	 * @return
	 */
	public static String addDataToXMLTag(String pXML, String pTag,
			String pValue, boolean pClosed) {

		String value = getXMLNodeValue(pXML, pTag);
		if (value != null && !value.trim().equals("")) {
			if (value.indexOf(pValue) > -1) {
				return pXML;
			}
		}

		String group = null;
		Pattern p = null;
		Matcher m = null;
		StringBuffer replacement = null;
		StringBuffer xml = null;

		StringBuffer patternBuffer = new StringBuffer();
		patternBuffer.append("(?m)(?s)(?i)<").append(pTag);
		patternBuffer.append("[^>]*>(.*?)</");
		patternBuffer.append(pTag).append(">");

		p = Pattern.compile(patternBuffer.toString(), Pattern.CASE_INSENSITIVE);
		m = p.matcher(pXML);

		replacement = null;
		xml = new StringBuffer();

		boolean found = false;
		while (m.find()) {
			found = true;
			group = m.group();
			if (group.indexOf(pValue) == -1) {
				replacement = new StringBuffer(group);
				if (pClosed) {
					replacement.insert(replacement.indexOf("/>"), ">" + pValue);
					replacement.replace(replacement.indexOf("/>"),
							replacement.indexOf("/>") + 2, "</" + pTag + ">");
				} else {
					replacement.insert(replacement.indexOf(">") + 1, pValue);
					replacement.insert(replacement.indexOf("</"), "");
				}
				if (replacement.indexOf(pValue + "</") < 0) {
					replacement.replace(replacement.indexOf(pValue),
							replacement.indexOf(pValue) + pValue.length(),
							pValue + ",");
				}

				m.appendReplacement(xml, replacement.toString());
			}
		}

		if (!found) {
			patternBuffer = new StringBuffer();
			patternBuffer.append("<").append(pTag).append("/>");
			p = Pattern.compile(patternBuffer.toString(),
					Pattern.CASE_INSENSITIVE);
			m = p.matcher(pXML);

			replacement = null;
			xml = new StringBuffer();

			found = false;
			while (m.find()) {
				found = true;
				group = m.group();
				if (group.indexOf(pValue) == -1) {
					replacement = new StringBuffer(group);
					if (pClosed) {
						replacement.insert(replacement.indexOf("/>"), ">"
								+ pValue);
						replacement.replace(replacement.indexOf("/>"),
								replacement.indexOf("/>") + 2, "</" + pTag
										+ ">");
					} else {
						replacement
								.insert(replacement.indexOf(">") + 1, pValue);
						replacement.insert(replacement.indexOf("</"), "");
					}
					if (replacement.indexOf(pValue + "</") < 0) {
						replacement.replace(replacement.indexOf(pValue),
								replacement.indexOf(pValue) + pValue.length(),
								pValue + ",");
					}

					m.appendReplacement(xml, replacement.toString());
				}
			}
		}

		if (!found) {
			return pXML;
		} else {
			m.appendTail(xml);
			return xml.toString();
		}
	}

	/**
	 * This method adds the data section to the given tag in the given xml.
	 * 
	 * @param pXML
	 * @param pTag
	 * @param pValue
	 * @param pClosed
	 * @return
	 */
	public static String removeDataFromXMLTag(String pXML, String pTag,
			String pValue) {

		String value = getXMLNodeValue(pXML, pTag);
		if (value == null || value.trim().equals("")) {
			return pXML;
		}

		if (value.indexOf(pValue) < 0) {
			return pXML;
		}

		String group = null;
		Pattern p = null;
		Matcher m = null;
		StringBuffer replacement = null;
		StringBuffer xml = null;

		StringBuffer patternBuffer = new StringBuffer();
		patternBuffer.append("<").append(pTag);
		patternBuffer.append("[^>]*>(.*?)</");
		patternBuffer.append(pTag).append(">");

		p = Pattern.compile(patternBuffer.toString(), Pattern.CASE_INSENSITIVE);
		m = p.matcher(pXML);

		replacement = null;
		xml = new StringBuffer();

		while (m.find()) {
			group = m.group();
			if (group.indexOf(pValue) > -1) {
				replacement = new StringBuffer(group);
				if (replacement.indexOf(pValue + ",") > -1) {
					replacement.replace(replacement.indexOf(pValue + ","),
							replacement.indexOf(pValue + ",") + pValue.length()
									+ 1, "");
				} else if (replacement.indexOf(pValue + "<") > -1) {
					if (replacement.indexOf("," + pValue + "<") > -1)
						replacement.replace(
								replacement.indexOf("," + pValue + "<"),
								replacement.indexOf("," + pValue + "<")
										+ pValue.length() + 1, "");
					else
						replacement.replace(
								replacement.indexOf(pValue + "<"),
								replacement.indexOf(pValue + "<")
										+ pValue.length(), "");
				}
				m.appendReplacement(xml, replacement.toString());
			}
		}

		m.appendTail(xml);
		return xml.toString();
	}

	/**
	 * This method adds the CData section to the given tag in the given xml.
	 * 
	 * @param pXML
	 * @param pTag
	 * @return
	 */
	public static String addCDataToXMLTag(String pXML, String pTag) {
		String group = null;
		Pattern p = null;
		Matcher m = null;
		StringBuffer replacement = null;
		StringBuffer xml = null;

		StringBuffer patternBuffer = new StringBuffer();
		patternBuffer.append("<").append(pTag);
		patternBuffer.append("[^>]*>(.*?)</");
		patternBuffer.append(pTag).append(">");

		p = Pattern.compile(patternBuffer.toString(), Pattern.CASE_INSENSITIVE);
		m = p.matcher(pXML);

		replacement = null;
		xml = new StringBuffer();

		while (m.find()) {
			group = m.group();
			if (group.indexOf("<![CDATA[") == -1) {
				replacement = new StringBuffer(group);
				replacement.insert(replacement.indexOf(">") + 1, "<![CDATA[");
				replacement.insert(replacement.indexOf("</"), "]]>");

				m.appendReplacement(xml, replacement.toString());
			}
		}
		m.appendTail(xml);
		return xml.toString();
	}

	/**
	 * 
	 * @param xml
	 * @param tagName
	 * @return
	 */
	public static String getXMLNodeValue(String xml, String tagName) {
		String group = null;
		Pattern p = null;
		Matcher m = null;

		if (xml == null) {
			return null;
		}

		StringBuffer patternBuffer = new StringBuffer();
		patternBuffer.append("(?m)(?s)(?i)<");
		patternBuffer.append(tagName);
		patternBuffer.append("[^>]*>(.*?)</");
		patternBuffer.append(tagName);
		patternBuffer.append(">");

		p = Pattern.compile(patternBuffer.toString(), Pattern.CASE_INSENSITIVE);
		m = p.matcher(xml);

		while (m.find()) {
			group = m.group();
			group = group.replace("<![CDATA[", "");
			group = group.replace("]]>", "");

			return group.substring(group.indexOf(">") + 1,
					group.lastIndexOf("</")).trim();
		}
		return null;
	}

	/**
	 * 
	 * @param pCommanSeperatedString
	 *            A String containing a comma seperated String
	 * @return String[]
	 */
	public static String[] getStringArrayFromCommaSeperatedString(
			String pCommanSeperatedString) {
		String[] stringArray = null;
		stringArray = pCommanSeperatedString.split(",");
		return stringArray;
	}

	/**
	 * 
	 * @param pCommanSeperatedString
	 *            A String containing a comma seperated String
	 * @param pSeperator
	 *            A String containing a comma separator
	 * @return String[]
	 */
	public static String[] getStringArrayFromString(
			String pCommanSeperatedString, String pSeperator) {
		String[] stringArray = null;
		stringArray = pCommanSeperatedString.split(pSeperator);
		return stringArray;
	}

	/**
	 * The method is used to check if the 'values' string array contains the
	 * given 'value' string.
	 * 
	 * @param values
	 * @param value
	 * @return boolean
	 */
	public static boolean isExists(String[] values, String value) {
		if (values != null && value != null) {
			for (String toCheck : values) {
				if (toCheck.equalsIgnoreCase(value)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * The method is used to check if the two values two strings are equivalent
	 * allowing null or empty to be passed
	 * 
	 * @param pValue1
	 * @param pValue2
	 * 
	 * @return boolean
	 */
	public static boolean isEquivalant(String pValue1, String pValue2) {
		boolean theSame = false;
		if (StringValidator.isEmpty(pValue1)) {
			if (StringValidator.isEmpty(pValue2)) {
				theSame = true;
			} else {
				theSame = false;
			}
		} else if (StringValidator.isEmpty(pValue2)) {
			theSame = false;
		} else {
			theSame = pValue1.equals(pValue2);
		}

		return theSame;

	} // end of isEquivalant

	/**
	 * The method is used to check if the two values two strings are equivalent
	 * allowing null or empty to be passed
	 * 
	 * @param pValue1
	 * @param pValue2
	 * 
	 * @return boolean
	 */
	public static boolean isEquivalantIgnoreCase(String pValue1, String pValue2) {
		boolean theSame = false;
		if (StringValidator.isEmpty(pValue1)) {
			if (StringValidator.isEmpty(pValue2)) {
				theSame = true;
			} else {
				theSame = false;
			}
		} else if (StringValidator.isEmpty(pValue2)) {
			theSame = false;
		} else {
			theSame = pValue1.equalsIgnoreCase(pValue2);
		}

		return theSame;

	} // end of isEquivalantIgnoreCase

	/**
	 * This method verifies whether the value passed in the parameter contains
	 * any invalid characters if found it will return a boolean value of true
	 * else false
	 * 
	 * @param pParameter
	 * @return boolean
	 */
	public static boolean isParameterInvalid(String pParameter) {

		boolean isParamInvalid = false;
		for (int i = 0; i < pParameter.length(); i++) {
			if ((invalidCharacters.indexOf(pParameter.charAt(i)) != -1)) {
				isParamInvalid = true;
			}
		}
		return isParamInvalid;
	} // end of isParameterInvalid

	/**
	 * The method is used to check if the first parameter pString contains the
	 * second parameter pValue
	 * 
	 * @param pString
	 * @param pValue
	 * 
	 * @return boolean
	 */
	public static boolean containsIgnoreCase(String pString, String pValue) {
		boolean contained = false;
		if (!StringValidator.isEmpty(pString)) {
			if (!StringValidator.isEmpty(pValue)) {
				String lowerString = pString.toLowerCase().trim();
				String lowerValue = pValue.toLowerCase().trim();
				contained = lowerString.contains(lowerValue);
			}
		}

		return contained;

	} // end of containsIgnoreCase
	
	/**
	 * The method is used return a String of X spaces
	 * 
	 * @param pString
	 * 
	 * @return boolean
	 */
	public static String addXSpaces(int pSpaces) {
		StringBuffer sb = new StringBuffer();
		for( int idx = 0; idx < pSpaces; idx++) {
			sb.append(" ");
		}

		return sb.toString();

	} // end of addSpaces

	/**
	 * Compares 2 String and handles the case where one or both are null
	 * @param pOne
	 * @param pTwo
	 * @return int
	 */
	public static int compareToIgnoreCase(final String pOne, final String pTwo) {
	    if (pOne == null || pTwo == null) {
		    if (pOne == null && pTwo == null) {
		        return 0;
		    } else {
		        return (pOne == null) ? -1 : 1;
		    }
	    }

	    return pOne.compareToIgnoreCase(pTwo);
	}

}
