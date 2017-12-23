package ebookRepository.model;

public class Ebook {
	private long id;
	private String title;
	private String author;
	private String keywords;
	private int publicationYear;
	private String filename;
	private String MIME;
	private Language language;
	private Category category;
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
