package ebookRepository.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ebookRepository.model.Ebook;

@Repository
public interface EbookRepository extends JpaRepository<Ebook, Long> {

}
