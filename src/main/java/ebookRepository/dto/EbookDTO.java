package ebookRepository.dto;

public class EbookDTO {

	private long id;

	private String title;

	private String author;

	private String keywords;

	private int publicationYear;

	private String filename;

	private String MIME;

	private long languageId;

	private long categoryId;

	private long cataloguerId;

	public EbookDTO(long id, String title, String author, String keywords, int publicationYear, String filename,
			String mIME, long languageId, long categoryId, long cataloguerId) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.keywords = keywords;
		this.publicationYear = publicationYear;
		this.filename = filename;
		MIME = mIME;
		this.languageId = languageId;
		this.categoryId = categoryId;
		this.cataloguerId = cataloguerId;
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

	public long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public long getCataloguerId() {
		return cataloguerId;
	}

	public void setCataloguerId(long cataloguerId) {
		this.cataloguerId = cataloguerId;
	}
}
