package ebookRepository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Ebook")
public class Ebook {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Title", columnDefinition="varchar(80)")
	private String title;
	
	@Column(name="Author", columnDefinition="varchar(120)")
	private String author;
	
	@Column(name="keywords", columnDefinition="varchar(120)")
	private String keywords;
	
	@Column(name="PublicationYear", columnDefinition="int")
	private int publicationYear;
	
	@Column(name="Filename", columnDefinition="varchar(200)")
	private String filename;
	
	@Column(name="MIME", columnDefinition="varchar(100)")
	private String MIME;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Language language;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Category category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private User cataloguer;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMIME() {
		return MIME;
	}
	public void setMIME(String mIME) {
		MIME = mIME;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public User getCataloguer() {
		return cataloguer;
	}
	public void setCataloguer(User cataloguer) {
		this.cataloguer = cataloguer;
	}
}
