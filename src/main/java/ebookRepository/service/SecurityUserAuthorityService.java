package ebookRepository.service;

import java.util.List;

import ebookRepository.model.user.SecurityUserAuthority;

public interface SecurityUserAuthorityService {

	SecurityUserAuthority findOne(Long id);
	
	List<SecurityUserAuthority> findAll();
	
	SecurityUserAuthority save(SecurityUserAuthority securityUserAuthority);
	
	List<SecurityUserAuthority> save(List<SecurityUserAuthority> securityAuthorities);
	
	void delete(Long id);
	
	void delete(List<SecurityUserAuthority> securityAuthorities);
}
