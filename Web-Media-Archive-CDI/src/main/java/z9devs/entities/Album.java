package z9devs.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="albums")
@NamedQuery(
		name="Album.getAll",
		query="SELECT a FROM Album a"
	)
public class Album 
{
	// Props
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="barcode")
	private String barcode;
	@Column(name="release_year")
	private int release_year;
	@Column(name="print_year")
	private int print_year;
	@Column(name="vote")
	private double vote;
	@Column(name="notes")
	private String notes;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
		name ="albums_artists",
		joinColumns= {@JoinColumn(name="id_album") },
		inverseJoinColumns = {@JoinColumn(name="id_artist") }
	)
	private List<Artist> artists;
	
	@ManyToMany
	@JoinTable(
		name ="albums_genres",
		joinColumns= {@JoinColumn(name="id_album") },
		inverseJoinColumns = {@JoinColumn(name="id_genre") }
	)
	private List<Genre> genres;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="format_id")
	private Format format;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="record_company_id")
	private RecordCompany record_company;
	
	
	// Getters & Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public int getRelease_year() {
		return release_year;
	}
	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}
	public int getPrint_year() {
		return print_year;
	}
	public void setPrint_year(int print_year) {
		this.print_year = print_year;
	}
	public double getVote() {
		return vote;
	}
	public void setVote(double vote) {
		this.vote = vote;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public List<Genre> getGenres() {
		return genres;
	}
	public void setGenres(List<Genre> genres) {
		this.genres = genres;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	public Format getFormat() {
		return format;
	}
	public void setFormat(Format format) {
		this.format = format;
	}
	public RecordCompany getRecord_company() {
		return record_company;
	}
	public void setRecord_company(RecordCompany record_company) {
		this.record_company = record_company;
	}
}