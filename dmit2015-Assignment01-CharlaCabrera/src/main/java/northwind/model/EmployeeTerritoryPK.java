package northwind.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EmployeeTerritories database table.
 * 
 */
@Embeddable
public class EmployeeTerritoryPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EmployeeID", insertable=false, updatable=false)
	private int employeeID;

	@Column(name="TerritoryID", insertable=false, updatable=false)
	private String territoryID;

	public EmployeeTerritoryPK() {
	}
	public int getEmployeeID() {
		return this.employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getTerritoryID() {
		return this.territoryID;
	}
	public void setTerritoryID(String territoryID) {
		this.territoryID = territoryID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EmployeeTerritoryPK)) {
			return false;
		}
		EmployeeTerritoryPK castOther = (EmployeeTerritoryPK)other;
		return 
			(this.employeeID == castOther.employeeID)
			&& this.territoryID.equals(castOther.territoryID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.employeeID;
		hash = hash * prime + this.territoryID.hashCode();
		
		return hash;
	}
}