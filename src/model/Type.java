package model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Klass Type: Ein Medientyp wird durch seinen Name und ein zugehoeriges
 * Icon beschrieben. Fuer die Speicherung in der DB benoetigt jeder Typ
 * noch eine ID
 * @author Hans-Helge Buerger
 *
 */
@Entity
@Table (name="TYPE")
public class Type {
	@Id	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String icon;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
