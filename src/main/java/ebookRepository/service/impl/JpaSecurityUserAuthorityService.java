package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.user.SecurityUserAuthority;
import ebookRepository.repository.SecurityUserAuthorityRepository;
import ebookRepository.service.SecurityUserAuthorityService;

@Transactional
@Service
public class JpaSecurityUserAuthorityService implements SecurityUserAuthorityService {

	@Autowired
	private SecurityUserAuthorityRepository securityUserAuthorityRepository;

	@Override
	public SecurityUserAuthority findOne(Long id) {
		return securityUserAuthorityRepository.findOne(id);
	}

	@Override
	public List<SecurityUserAuthority> findAll() {
		return securityUserAuthorityRepository.findAll();
	}

	@Override
	public SecurityUserAuthority save(SecurityUserAuthority securityUserAuthority) {
		return securityUserAuthorityRepository.save(securityUserAuthority);
	}

	@Override
	public List<SecurityUserAuthority> save(List<SecurityUserAuthority> securityAuthorities) {
		return securityUserAuthorityRepository.save(securityAuthorities);
	}

	@Override
	public void delete(Long id) {
		securityUserAuthorityRepository.delete(id);
	}

	@Override
	public void delete(List<SecurityUserAuthority> securityAuthorities) {
		securityUserAuthorityRepository.delete(securityAuthorities);
	}
}
