package ebookRepository.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebookRepository.model.User;
import ebookRepository.repository.UserRepository;
import ebookRepository.service.UserService;

@Transactional
@Service
public class JpaUserService implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> save(List<User> users) {
		return userRepository.save(users);
	}

	@Override
	public User delete(Long id) {
		User user = findOne(id);
		userRepository.delete(user);
		return user;
	}

	@Override
	public List<User> delete(List<User> users) {
		userRepository.delete(users);
		return null;
	}

}
