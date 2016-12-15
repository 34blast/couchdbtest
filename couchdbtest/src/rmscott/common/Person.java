/**
 * RMSCOTT Prototyping
 */
package rmscott.common;

import java.io.Serializable;

import rmscott.util.StringValidator;

/**
 * @author rmscott
 *
 */
public class Person extends BaseBean implements Serializable, Comparable<BaseBean> {

	private static final long serialVersionUID = -8198684392598670061L;

	private String firstName = null;
	private String middleName = null;
	private String lastName = null;
	private String nickName = null;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
		result = prime * result + ((nickName == null) ? 0 : nickName.hashCode());
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
		Person other = (Person) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (middleName == null) {
			if (other.middleName != null)
				return false;
		} else if (!middleName.equals(other.middleName))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
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

		Person otherPerson = null;

		// this optimization is usually worthwhile, and can
		// always be added
		if (this == pOther)
			return EQUAL;

		if (!(pOther instanceof Person)) {
			return BEFORE;
		} else {
			otherPerson = (Person) pOther;
		}
		int comparision = StringValidator.compareToIgnoreCase(this.lastName, otherPerson.lastName);
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(this.firstName, otherPerson.firstName);
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(this.middleName, otherPerson.middleName);
		if (comparision != EQUAL) {
			return comparision;
		}

		comparision = StringValidator.compareToIgnoreCase(this.nickName, otherPerson.nickName);
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
		StringBuffer sb = new StringBuffer("Person [firstName=");
		sb.append(firstName);
		sb.append(", middleName=");
		sb.append(middleName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", nickName=");
		sb.append(nickName);
		sb.append("]");
		sb.append(StringValidator.EOL);
		sb.append("    ");
		sb.append(super.toString());

		return sb.toString();
		
	} // end of toString()

}
