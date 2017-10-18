package northwind.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EmployeeTerritories database table.
 * 
 */
@Entity
@Table(name="EmployeeTerritories")
@NamedQuery(name="EmployeeTerritory.findAll", query="SELECT e FROM EmployeeTerritory e")
public class EmployeeTerritory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EmployeeTerritoryPK id;

	public EmployeeTerritory() {
	}

	public EmployeeTerritoryPK getId() {
		return this.id;
	}

	public void setId(EmployeeTerritoryPK id) {
		this.id = id;
	}

}