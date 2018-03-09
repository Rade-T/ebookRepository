package ebookRepository.dto;

import ebookRepository.model.Category;

public class CategoryDTO {
	
	private long id;

	private String name;
	
	public CategoryDTO() {}
	
	public CategoryDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category c) {
		this.id = c.getId();
		this.name = c.getName();
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
