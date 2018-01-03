package ebookRepository.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebookRepository.converter.UserDTOtoUser;
import ebookRepository.converter.UserToUserDTO;
import ebookRepository.dto.UserDTO;
import ebookRepository.model.User;
import ebookRepository.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDTOtoUser toUser;
	
	@Autowired
	private UserToUserDTO toUserDTO;
	
	@RequestMapping(value="getUsers", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {

		List<UserDTO> users = toUserDTO.convert(userService.findAll());

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		UserDTO user = toUserDTO.convert(userService.findOne(id));
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
		
		User newUser = userService.save(toUser.convert(userDTO));
		return new ResponseEntity<>(toUserDTO.convert(newUser), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		UserDTO deleted = toUserDTO.convert(userService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}