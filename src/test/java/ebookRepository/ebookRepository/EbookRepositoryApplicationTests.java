package ebookRepository.ebookRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ebookRepository.model.Category;
import ebookRepository.service.CategoryService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EbookRepositoryApplicationTests {

	@Autowired
	private CategoryService categoryService;
	
	@Test
	public void contextLoads() {
		Category category = new Category();
		category.setName("Test category");
		categoryService.save(category);
	}

}
