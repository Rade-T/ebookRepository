package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.user.SecurityAuthority;
import ebookRepository.repository.SecurityAuthorityRepository;
import ebookRepository.service.SecurityAuthorityService;

@Transactional
@Service
public class JpaSecurityAuthorityService implements SecurityAuthorityService {
	
	@Autowired
	private SecurityAuthorityRepository securityAuthorityRepository;

	@Override
	public SecurityAuthority findOne(Long id) {
		return securityAuthorityRepository.findOne(id);
	}

	@Override
	public List<SecurityAuthority> findAll() {
		return securityAuthorityRepository.findAll();
	}

	@Override
	public SecurityAuthority save(SecurityAuthority securityAuthority) {
		return securityAuthorityRepository.save(securityAuthority);
	}

	@Override
	public List<SecurityAuthority> save(List<SecurityAuthority> securityAuthorities) {
		return securityAuthorityRepository.save(securityAuthorities);
	}

	@Override
	public void delete(Long id) {
		securityAuthorityRepository.delete(id);
	}

	@Override
	public void delete(List<SecurityAuthority> securityAuthorities) {
		securityAuthorityRepository.delete(securityAuthorities);
	}

}
