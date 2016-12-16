/**
 * RMSCOTT - prototyping
 */
package rmscott.common;

import java.io.Serializable;

/**
 * @author rmscott
 *
 */
public class BaseBean implements Serializable, Comparable<BaseBean> {

	private static final long serialVersionUID = -3081305268881713251L;

	private String _id = null;
	private String _rev = null;

	/**
	 * Constructor for this class
	 */
	public BaseBean() {

	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	@Override
	public int compareTo(BaseBean pOther) {
		final int EQUAL = 0;

		// this optimization is usually worthwhile, and can
		// always be added
		if (this == pOther)
			return EQUAL;

		int comparison = this._id.compareTo(pOther._id);
		if (comparison != EQUAL)
			return comparison;

		comparison = this._rev.compareTo(pOther._rev);
		if (comparison != EQUAL)
			return comparison;

		// all comparisons have yielded equality
		// verify that compareTo is consistent with equals (optional)
		assert this.equals(pOther) : "compareTo inconsistent with equals.";

		return EQUAL;

	} // end of compareTo

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_id == null) ? 0 : _id.hashCode());
		result = prime * result + ((_rev == null) ? 0 : _rev.hashCode());
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
		BaseBean other = (BaseBean) obj;
		if (_id == null) {
			if (other._id != null)
				return false;
		} else if (!_id.equals(other._id))
			return false;
		if (_rev == null) {
			if (other._rev != null)
				return false;
		} else if (!_rev.equals(other._rev))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("BaseBean [_id=");
		sb.append(_id);
		sb.append(", _rev=");
		sb.append(_rev);
		sb.append(", className=");
		sb.append(this.getClass().getName());
		sb.append("]");
		return sb.toString();

	} // end of toString()

}
