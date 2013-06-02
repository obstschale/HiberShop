package model;

/**
 * Klasse Medium: ein Medium kann ein Audio- oder ein Videomedium sein. Es
 * hat immer einen Medientyp und kann - muss aber nicht - Bestandteil eines
 * Albums sein. Zudem enthaelt es die folgenden Informationen, die der Benutzer
 * in der fertigen Anwendung selber eingeben wird:
 * * Titel
 * * Interpret
 * * Laenge
 * * Dateigroesse
 * * Pfad zur Mediendatei
 * 
 * Zudem hat ein Medium zwei Attribute, die spaeter automatisch durch die
 * Anwendung gesetzt werden:
 * * Ein Attribut, in dem gespeichert wird, wie oft das Medium angehoert wurde.
 * * Ein weiteres Attribut, in dem gespeichert wird, wie oft ein Medium gekauft
 * wurde.
 * 
 * (Beziehungen noch nicht beachtete)
 * @author Hans-Helge Buerger
 *
 */
public class Album {
	
	String name;
	String interpret;
	String cover;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInterpret() {
		return interpret;
	}
	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
}
