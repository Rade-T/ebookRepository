package ebookRepository.dto;

public class LanguageDTO {
	
	private long id;
	
	private String name;
	
	public LanguageDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public LanguageDTO() {
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
