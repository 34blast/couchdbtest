/**
 * RMSCOTT Prototyping
 */
package rmscott.football;

import java.io.Serializable;

import rmscott.common.BaseBean;
import rmscott.util.StringValidator;

/**
 * @author rmscott
 *
 */
public class Team extends BaseBean implements Serializable, Comparable<BaseBean> {

	private static final long serialVersionUID = -9120762067417126642L;

	private String teamName = null;

	public String getTeamName() {
		return teamName;
	}

	public void setNameName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		result = prime * result + super.hashCode();

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;

		return super.equals(obj);
	}

	/**
	 * Compare to function returns 0 if equal, -1 if less, 1 if greater
	 * 
	 * @param pOther
	 * @return int
	 */
	@Override
	public int compareTo(BaseBean pOther) {
		final int BEFORE = -1;
		final int EQUAL = 0;
		// final int AFTER = 1;

		Team otherPerson = null;

		// this optimization is usually worthwhile, and can
		// always be added
		if (this == pOther)
			return EQUAL;

		if (!(pOther instanceof Team)) {
			return BEFORE;
		} else {
			otherPerson = (Team) pOther;
		}

		int comparision = StringValidator.compareToIgnoreCase(this.teamName, otherPerson.teamName);
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = super.compareTo(pOther);
		if (comparision != EQUAL) {
			return comparision;
		}

		// all comparisons have yielded equality
		// verify that compareTo is consistent with equals (optional)
		assert this.equals(pOther) : "compareTo inconsistent with equals.";

		return EQUAL;

	} // end of compareTo

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Team [_id=");
		sb.append(this.get_id());
		sb.append(", teamName=");
		sb.append(teamName);
		sb.append("]");

		return sb.toString();
		
	} // end of toString()

}
