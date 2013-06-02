package model;

/**
 * Klass Album: Ein Album besteht aus einem Namen, einen optionalen Interpreten
 * und einem Coverbild. Ausserdem setzt sich ein Album aus mehreren Medien
 * zusammen. (Beziehungen noch nicht beachtet)
 * @author Hans-Helge Buerger
 *
 */
public class Medium {
	
	int type;
	int album;
	String titel;
	String interpret;
	float laenge;
	float dateigroesse;
	String pfad;
	int angehoert;
	int gekauft;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getAlbum() {
		return album;
	}
	public void setAlbum(int album) {
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
