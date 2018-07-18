package ebookRepository.model.user;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import ebookRepository.model.User;

/**
 * Mapping table between User and Authority
 * @author Goran
 *
 */
@Entity
public class SecurityUserAuthority {
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private SecurityAuthority authority;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SecurityAuthority getAuthority() {
		return authority;
	}

	public void setAuthority(SecurityAuthority authority) {
		this.authority = authority;
	}
}
