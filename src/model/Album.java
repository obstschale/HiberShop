package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
 * @author Hans-Helge Buerger
 *
 */
@Entity
@Table (name="ALBUM")
public class Album {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(nullable=false)
	private String name;
	private String interpret;
	@Column(nullable=false)
	private String cover;
	@OneToMany(fetch=FetchType.LAZY)
	private List<Medium> media = new ArrayList<Medium>();
	
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
	public List<Medium> getMedia() {
		return media;
	}
	public void setMedia(List<Medium> media) {
		this.media = media;
	}
}
