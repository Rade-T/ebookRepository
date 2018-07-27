package ebookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebookRepository.model.user.SecurityAuthority;

@Repository
public interface SecurityAuthorityRepository extends JpaRepository<SecurityAuthority, Long> {
	
}
