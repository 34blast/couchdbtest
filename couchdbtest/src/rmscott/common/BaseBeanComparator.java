/**
 * RMSCOTT - prototyping
 */
package rmscott.common;

import java.util.Comparator;

import rmscott.util.StringValidator;

public class BaseBeanComparator implements Comparator<BaseBean> {

	@Override
	public int compare(BaseBean pOne, BaseBean pTwo) {
		final int EQUAL = 0;

		// this optimization is usually worthwhile, and can
		// always be added
		if (pOne == pTwo)
			return EQUAL;

		int comparision = StringValidator.compareToIgnoreCase(pOne.get_id(), pTwo.get_id());
		if (comparision != EQUAL) {
			return comparision;
		}

		// all comparisons have yielded equality
		// verify that compareTo is consistent with equals (optional)
		assert pOne.equals(pTwo) : "compareTo inconsistent with equals.";

		return EQUAL;

	} // end of compare
	
}
