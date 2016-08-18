package jpamappings;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.Arrays;

import java.io.Serializable;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/// this was added to use this class as an embedded ID
@Embeddable
public class CurveEntryPK
		implements Serializable
{
	/* non-foreign key way of doing things
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Curve_fkey", nullable = false, referencedColumnName = "Curve_key")
	//@Column(name = "Curve_fkey", nullable = false)
	private Curve curve;
	public Curve getCurve() { return curve; }
	public void setCurve(Curve curve) { this.curve = curve; }

	public CurveEntryPK(int curveCurveFkey, int position)
	{
		this.curve = new Curve();
		this.curve.setId(curveCurveFkey);
		this.position = position;
	}
*/

/*	 foreign key way of doing things */
	@Column(name = "Curve_fkey", nullable = false)
	private int curveCurveFkey;
	public int getCurveCurveFkey() { return curveCurveFkey; }
	public void setCurveCurveFkey(int curveCurveFkey) { this.curveCurveFkey = curveCurveFkey; }

	public CurveEntryPK(int curveCurveFkey, int position)
	{
		this.curveCurveFkey = curveCurveFkey;
		this.position = position;
	}
/*	 end of foreign key way of doing things */

	@Column(name = "Position", nullable = false)
	private int position;
	public int getPosition() { return position; }
	public void setPosition(int position) { this.position = position; }


	public CurveEntryPK()
	{
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }

		if (o == null || getClass() != o.getClass()) { return false; }

		CurveEntryPK that = (CurveEntryPK) o;

		/* foreign key way */
		if (getCurveCurveFkey() != that.getCurveCurveFkey()) { return false; }

		/* non-foreign key way
		if (getCurve() != that.getCurve()) { return false; }
		*/

		if (getPosition() != that.getPosition()) { return false; }

		return true;
	}

	@Override
	/* foreign key way */
	public String toString()
	{
		return curveCurveFkey + "||" + position;
	}

	/* non-foreign key
	public String toString()
	{
		return getCurve().getId() + "||" + position;
	}
	*/

	@Override
	public int hashCode()
	{
		int result = 0;

		/* foreign key way */
		result = 31 * result + ((getCurveCurveFkey()));

		/* non-foreign key
		result = 31 * result + ((getCurve().getId()));
		result = 31 * result + (getPosition());
		*/
		return result;
	}
}
