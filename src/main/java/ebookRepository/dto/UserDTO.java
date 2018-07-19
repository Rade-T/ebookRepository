package ebookRepository.dto;

import ebookRepository.model.User;

public class UserDTO {
	
	private long id;

	private String firstname;

	private String lastname;

	private String username;

	private String password;

	private String type;

	private long categoryId;

	public UserDTO(long id, String firstname, String lastname, String username, String password) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		//this.type = type;
		//this.categoryId = categoryId;
	}
	
	public UserDTO(User u) {
		this.id = u.getId();
		this.firstname = u.getFirstname();
		this.lastname = u.getLastname();
		this.username = u.getUsername();
		this.password = u.getPassword();
		//this.type = u.getType();
		//this.categoryId = u.getCategory().getId();
	}

	public UserDTO() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
}
