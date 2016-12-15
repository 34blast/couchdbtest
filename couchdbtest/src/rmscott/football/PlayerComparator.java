/**
 * RMSCOTT - prototyping
 */
package rmscott.football;

import java.util.Comparator;

import rmscott.common.PersonComparator;
import rmscott.util.StringValidator;

public class PlayerComparator implements Comparator<Player> {

	/**
	 * Sort by highest ranking then by other parameters
	 */
	@Override
	public int compare(Player pOne, Player pTwo) {
		final int EQUAL = 0;

		// this optimization is usually worthwhile, and can
		// always be added
		if (pOne == pTwo)
			return EQUAL;

		Float oneRank = new Float(pOne.getRanking());
		Float twoRank = new Float(pTwo.getRanking());
		int comparision = twoRank.compareTo(oneRank);
		if (comparision != EQUAL) {
			return comparision;
		}

		PersonComparator superComparator = new PersonComparator();
		comparision = superComparator.compare(pOne, pTwo);
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(pOne.getPosition(), pTwo.getPosition());
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(pOne.getNotes(), pTwo.getNotes());
		if (comparision != EQUAL) {
			return comparision;
		}
		
		// all comparisons have yielded equality
		// verify that compareTo is consistent with equals (optional)
		assert pOne.equals(pTwo) : "compareTo inconsistent with equals.";

		return EQUAL;

	} // end of compare

}
