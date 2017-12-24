package ebookRepository.repository;

import java.util.List;

import ebookRepository.model.User;

public interface UserService {
	
	User findOne(Long id);
	
	List<User> findAll();
	
	User save(User user);
	
	List<User> save(List<User> users);
	
	User delete(Long id);
	
	List<User> delete(List<User> users);
}
