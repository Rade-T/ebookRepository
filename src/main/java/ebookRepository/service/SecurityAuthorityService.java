package ebookRepository.service;

import java.util.List;

import ebookRepository.model.user.SecurityAuthority;

public interface SecurityAuthorityService {
	
	SecurityAuthority findOne(Long id);
	
	List<SecurityAuthority> findAll();
	
	SecurityAuthority save(SecurityAuthority securityAuthority);
	
	List<SecurityAuthority> save(List<SecurityAuthority> securityAuthorities);
	
	void delete(Long id);
	
	void delete(List<SecurityAuthority> securityAuthorities);
}
