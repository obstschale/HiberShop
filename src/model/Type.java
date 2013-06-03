package model;

/**
 * Klass Type: Ein Medientyp wird durch seinen Name und ein zugehoeriges
 * Icon beschrieben. Fuer die Speicherung in der DB benoetigt jeder Typ
 * noch eine ID
 * @author Hans-Helge Buerger
 *
 */
public class Type {
	
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
