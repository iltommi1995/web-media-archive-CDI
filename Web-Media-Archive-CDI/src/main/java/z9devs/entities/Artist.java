package z9devs.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="artists")
@XmlRootElement
@NamedQuery(
	    name="Artist.getAll",
	    query="SELECT a FROM Artist a"
	)
public class Artist 
{
	// Props
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="name")
	private String name;
	@ManyToMany(mappedBy="artists", fetch=FetchType.LAZY)
	private List<Album> albums;
	
	// Getters & Setters
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
	public List<Album> getAlbums() {
		return albums;
	}
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}	
	
	// Methods to avoid recursion in json
	public void prepareJsonSingleArtist() {
		for(Album a : this.albums) 
		{
			a.setArtists(null);
			a.setGenres(null);
			a.setFormat(null);
			a.setRecord_company(null);
			a.setNotes(null);
			a.setBarcode(null);
		}
	}
}