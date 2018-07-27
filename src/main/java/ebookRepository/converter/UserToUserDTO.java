package ebookRepository.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

import ebookRepository.dto.UserDTO;
import ebookRepository.model.User;

public class UserToUserDTO implements Converter<User, UserDTO> {
	
	@Override
	public UserDTO convert(User arg0) {
		UserDTO u = new UserDTO();
		u.setId(arg0.getId());
		u.setFirstname(arg0.getFirstname());
		u.setLastname(arg0.getLastname());
		u.setPassword(arg0.getPassword());
		u.setUsername(arg0.getUsername());
//		u.setType(arg0.getType());
		u.setCategoryId( arg0.getCategory().getId() );
		return u;
	}

	public List<UserDTO> convert(List<User> users) {
		List<UserDTO> retval = new ArrayList<>();
		for (User u : users) {
			retval.add(convert(u));
		}
		return retval;
	}
}
