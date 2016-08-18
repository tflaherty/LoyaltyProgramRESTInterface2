package jpamappings;

import java.sql.Timestamp;
import java.util.UUID;
import java.util.Arrays;

import java.io.Serializable;

import org.hibernate.annotations.Type;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created programmatically on Wed Aug 10 17:35:53 EDT 2016
 * Database Version:  Microsoft SQL Server 2005 - 9.00.5069.00 (X64) 
	Aug 22 2012 18:02:46 
	Copyright (c) 1988-2005 Microsoft Corporation
	Developer Edition (64-bit) on Windows NT 6.1 (Build 7601: Service Pack 1)

 */

// put config.exposeIdsFor(CurveEntry.class); into RepositoryConfiguration to expose the ID fields via JSON
///@IdClass(CurveEntryPK.class)
@Entity
@Table(name = "CurveEntry", schema = "dbo", catalog = "BAG_IPM")
public class CurveEntry implements Serializable
{
/*
	@Id
	@Column(name = "Curve_fkey", nullable = false)
	private int curveCurveFkey;
	//public int getCurveCurveFkey() { return curveCurveFkey; }
	//public void setCurveCurveFkey(int curveCurveFkey) { this.curveCurveFkey = curveCurveFkey; }

	@Id
	@Column(name = "Position", nullable = false)
	private int position;
	//public int getPosition() { return position; }
	//public void setPosition(int position) { this.position = position; }
*/

	@EmbeddedId
	private CurveEntryPK id;
	public CurveEntryPK getId()
	{
		return id;
	}
	public void setId(CurveEntryPK id)
	{
		this.id = id;
	}

	// This is here because it connects us to the curve foreign key in the embedded it.
	// EmbeddedIds don't support relationship so this is what @MapsId is used for
	@MapsId("curveCurveFkey")
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Curve_fkey", nullable = false, referencedColumnName = "Curve_key")
	//@Column(name = "Curve_fkey", nullable = false)
	private Curve curve;
	public Curve getCurve() { return curve; };
	public void setCurve(Curve curve) { this.curve = curve; }

	@Basic
	@Column(name = "CurveEntry_key", nullable = false)
	private int curveEntryKey;
	public int getCurveEntryKey() { return curveEntryKey; }
	public void setCurveEntryKey(int curveEntryKey) { this.curveEntryKey = curveEntryKey; }

	@Basic
	@Column(name = "Value", nullable = false)
	private java.math.BigDecimal value;
	public java.math.BigDecimal getValue() { return value; }
	public void setValue(java.math.BigDecimal value) { this.value = value; }

	public CurveEntry()
	{
		return;
	}

///	public CurveEntry(CurveEntryPK curveEntryPK)
///	{
///		this.curveCurveFkey = curveEntryPK.getCurveCurveFkey();
///		this.position = curveEntryPK.getPosition();
///	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) { return true; }

		if (o == null || getClass() != o.getClass()) { return false; }

		CurveEntry that = (CurveEntry) o;

		if (getCurveEntryKey() != that.getCurveEntryKey()) { return false; }

		if (getValue() != that.getValue()) { return false; }

		return true;
	}
	@Override
	public int hashCode()
	{
		int result = 0;

		result = 31 * result + (getCurveEntryKey());
		result = 31 * result + (((getValue() != null) ? ((getValue().hashCode())) : 0));

		return result;
	}
}
