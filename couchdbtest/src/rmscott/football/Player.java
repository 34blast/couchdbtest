package rmscott.football;

import java.io.Serializable;

import rmscott.common.BaseBean;
import rmscott.common.Person;
import rmscott.util.StringValidator;

public class Player extends Person implements Serializable, Comparable<BaseBean> {

	private static final long serialVersionUID = 2120698598068312636L;

	private String position = null;
	private float ranking = new Float(0.0).floatValue();
	private String notes = null;
	private Team team = null;

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public float getRanking() {
		return ranking;
	}

	public void setRanking(float ranking) {
		this.ranking = ranking;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		result = prime * result + Float.floatToIntBits(ranking);
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (Float.floatToIntBits(ranking) != Float.floatToIntBits(other.ranking))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
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
		Player otherPlayer = null;

		// this optimization is usually worthwhile, and can
		// always be added
		if (this == pOther)
			return EQUAL;

		if (!(pOther instanceof Player)) {
			return BEFORE;
		} else {
			otherPlayer = (Player) pOther;
		}

		Float oneRank = new Float(this.getRanking());
		Float twoRank = new Float(otherPlayer.getRanking());
		int comparision = twoRank.compareTo(oneRank);
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(this.getPosition(), otherPlayer.getPosition());
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(this.getNotes(), otherPlayer.getNotes());
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
		StringBuffer sb = new StringBuffer("Player [position=");
		sb.append(position);
		sb.append(", ranking=");
		sb.append(ranking);
		sb.append(", notes=");
		sb.append(notes);
		sb.append("]");
		sb.append(StringValidator.EOL);
		sb.append("    ");
		sb.append(super.toString());
		sb.append(StringValidator.EOL);
		sb.append("    ");
		if (team != null) {
			sb.append(team.toString());
		} else {
			sb.append("team=null");
		}

		return sb.toString();
	}

}
