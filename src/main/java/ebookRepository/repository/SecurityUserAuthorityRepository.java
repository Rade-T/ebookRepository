package ebookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebookRepository.model.user.SecurityUserAuthority;

@Repository
public interface SecurityUserAuthorityRepository extends JpaRepository<SecurityUserAuthority, Long> {

}
