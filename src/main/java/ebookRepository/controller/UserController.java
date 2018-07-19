package ebookRepository.controller;

import ebookRepository.dto.UserDTO;
import ebookRepository.model.User;
import ebookRepository.service.CategoryService;
import ebookRepository.service.UserService;
import ebookRepository.security.AuthenticationFacade;
import ebookRepository.security.TokenUtils;
import ebookRepository.dto.LoginDTO;
import ebookRepository.dto.TokenDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private AuthenticationFacade authenticationFacade;

	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) {

		try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            String genToken = tokenUtils.generateToken(details);
            return new ResponseEntity<TokenDTO>(new TokenDTO(genToken), 
            		HttpStatus.OK);
        } catch (Exception ex) {
        	System.out.println(ex.getStackTrace());
            return new ResponseEntity<TokenDTO>(new TokenDTO(""), HttpStatus.BAD_REQUEST);
        }
	}
	
	@RequestMapping(value = "/role", method = RequestMethod.GET)
	public ResponseEntity<String> role() {
		Authentication authentication = authenticationFacade.getAuthentication();
		Object[] authorities = authentication.getAuthorities().toArray();
		String role = "spectator";
		if (authorities.length > 0) {
			role = authorities[0].toString();
		}
		return new ResponseEntity<>(role, HttpStatus.OK);
	}
	
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
//		if (user == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}

		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

		User newUser = new User();
		newUser.setFirstname(userDTO.getFirstname());
		newUser.setLastname(userDTO.getLastname());
		newUser.setPassword(userDTO.getPassword());
		newUser.setUsername(userDTO.getUsername());
		//newUser.setCategory(categoryService.findOne(userDTO.getCategoryId()));

		userDTO = new UserDTO(userService.save(newUser));
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		UserDTO deleted = new UserDTO(userService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}