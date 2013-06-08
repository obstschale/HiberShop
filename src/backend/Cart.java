package backend;

import java.util.HashSet;
import java.util.Set;

import model.Medium;

public class Cart {

	private Set<Medium> media = new HashSet<Medium>();

	public Set<Medium> getMedia() {
		return media;
	}

	public void setMedia(Set<Medium> media) {
		this.media = media;
	}
	
}
