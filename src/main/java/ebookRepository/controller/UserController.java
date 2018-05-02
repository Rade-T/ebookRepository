package ebookRepository.controller;

import ebookRepository.dto.UserDTO;
import ebookRepository.model.User;
import ebookRepository.service.CategoryService;
import ebookRepository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
    private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.findAll();
		List<UserDTO> usersDTO = new ArrayList<>();
        for (User user : users) {
            usersDTO.add(new UserDTO(user));
        }
		return new ResponseEntity<>(usersDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		UserDTO user = new UserDTO(userService.findOne(id));
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

		User newUser = new User();
		newUser.setFirstname(userDTO.getFirstname());
		newUser.setLastname(userDTO.getLastname());
		newUser.setPassword(userDTO.getPassword());
		newUser.setType(userDTO.getType());
		newUser.setUsername(userDTO.getUsername());
		newUser.setCategory(categoryService.findOne(userDTO.getCategoryId()));

		userDTO = new UserDTO(userService.save(newUser));
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		UserDTO deleted = new UserDTO(userService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}