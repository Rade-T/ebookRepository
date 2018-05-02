package ebookRepository.dto;

import ebookRepository.model.Ebook;

public class EbookDTO {

	private long id;

	private String title;

	private String author;

	private String keywords;

	private String publicationYear;

	private String filename;

	private String MIME;

	private long language;

	private long category;

	private long cataloguer;

	public EbookDTO(long id, String title, String author, String keywords, String publicationYear, String filename,
			String mIME, long languageId, long categoryId, long cataloguerId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
		this.filename = filename;
		MIME = mIME;
		this.language = languageId;
		this.category = categoryId;
		this.cataloguer = cataloguerId;
	}
	
	public EbookDTO(Ebook e) {
		this.id = e.getId();
		this.title = e.getTitle();
		this.author = e.getAuthor();
		this.keywords = e.getKeywords();
		this.publicationYear = e.getPublicationYear();
		this.filename = e.getFilename();
		this.MIME = e.getMIME();
		this.language = e.getLanguage().getId();
		this.category = e.getCategory().getId();
		this.cataloguer = e.getCataloguer().getId();
	}

	public EbookDTO() {
	}

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

	public String getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(String publicationYear) {
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

	public long getLanguage() {
		return language;
	}

	public void setLanguage(long languageId) {
		this.language = languageId;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long categoryId) {
		this.category = categoryId;
	}

	public long getCataloguer() {
		return cataloguer;
	}

	public void setCataloguer(long cataloguerId) {
		this.cataloguer = cataloguerId;
	}
}
