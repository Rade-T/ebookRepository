package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.UserDTO;
import ebookRepository.model.User;
import ebookRepository.service.CategoryService;

public class UserDTOtoUser implements Converter<UserDTO, User> {

	@Autowired
	private CategoryService categoryService;

	@Override
	public User convert(UserDTO arg0) {
		User u = new User();
		u.setId(arg0.getId());
		u.setFirstname(arg0.getFirstname());
		u.setLastname(arg0.getLastname());
		u.setPassword(arg0.getPassword());
		u.setUsername(arg0.getUsername());
//		u.setType(arg0.getType());
		u.setCategory( categoryService.findOne(arg0.getCategoryId()) );
		return u;
	}
	
	public List<User> convert(List<UserDTO> users) {
		List<User> retval = new ArrayList<>();
		for (UserDTO u : users) {
			retval.add(convert(u));
		}
		return retval;
	}
}
