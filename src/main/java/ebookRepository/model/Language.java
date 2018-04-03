package ebookRepository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ebookRepository.dto.LanguageDTO;

@Entity
@Table(name="Language")
public class Language {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="Name", columnDefinition="varchar(30)")
	private String name;
	
	public Language(LanguageDTO l) {
		this.id = l.getId();
		this.name = l.getName();
	}
	
	public Language() {
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
