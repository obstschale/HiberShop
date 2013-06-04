package model;

/**
 * Klass Album: Ein Album besteht aus einem Namen, einen optionalen Interpreten
 * und einem Coverbild. Ausserdem setzt sich ein Album aus mehreren Medien
 * zusammen. (Beziehungen noch nicht beachtet)
 * @author Hans-Helge Buerger
 *
 */
public class Medium {
	
	private int id;
	private Type type;
	private Album album;
	private String titel;
	private String interpret;
	private float laenge;
	private float dateigroesse;
	private String pfad;
	private int angehoert;
	private int gekauft;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getInterpret() {
		return interpret;
	}
	public void setInterpret(String interpret) {
		this.interpret = interpret;
	}
	public float getLaenge() {
		return laenge;
	}
	public void setLaenge(float laenge) {
		this.laenge = laenge;
	}
	public float getDateigroesse() {
		return dateigroesse;
	}
	public void setDateigroesse(float dateigroesse) {
		this.dateigroesse = dateigroesse;
	}
	public String getPfad() {
		return pfad;
	}
	public void setPfad(String pfad) {
		this.pfad = pfad;
	}
	public int getAngehoert() {
		return angehoert;
	}
	public void setAngehoert(int angehoert) {
		this.angehoert = angehoert;
	}
	public int getGekauft() {
		return gekauft;
	}
	public void setGekauft(int gekauft) {
		this.gekauft = gekauft;
	}
	
}
