package ebookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebookRepository.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
