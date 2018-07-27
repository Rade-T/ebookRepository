package ebookRepository.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ebookRepository.dto.LoginDTO;
import ebookRepository.dto.TokenDTO;
import ebookRepository.dto.UserDTO;
import ebookRepository.model.User;
import ebookRepository.model.user.SecurityAuthority;
import ebookRepository.model.user.SecurityUserAuthority;
import ebookRepository.security.AuthenticationFacade;
import ebookRepository.security.TokenUtils;
import ebookRepository.service.CategoryService;
import ebookRepository.service.SecurityAuthorityService;
import ebookRepository.service.SecurityUserAuthorityService;
import ebookRepository.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SecurityAuthorityService securityAuthorityService;
	
	@Autowired
	private SecurityUserAuthorityService securityUserAuthorityService;

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
		User createdUser = userService.save(newUser);
		//newUser.setCategory(categoryService.findOne(userDTO.getCategoryId()));
		SecurityAuthority userAuthority = securityAuthorityService.findOne(new Long(1));
		SecurityUserAuthority securityUserAuthority = new SecurityUserAuthority();
		securityUserAuthority.setAuthority(userAuthority);
		securityUserAuthority.setUser(createdUser);
		securityUserAuthorityService.save(securityUserAuthority);
		userDTO = new UserDTO(createdUser);
		return new ResponseEntity<>(userDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/change-data", method=RequestMethod.POST)
	public ResponseEntity<String> editUser(@RequestBody Map<String, Object> data) {
		String username = authenticationFacade.getAuthentication().getName();
		User u = userService.findByUsername(username);
		String newUsername = (String)data.get("username");
		if (userService.findByUsername(newUsername) != null) {
			return new ResponseEntity<String>("Username exists", HttpStatus.BAD_REQUEST);
		}
		u.setFirstname((String)data.get("firstname"));
		u.setLastname((String)data.get("lastname"));
		u.setUsername(newUsername);
		userService.save(u);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.PUT)
	public ResponseEntity<String> changePassword(@RequestBody Map<String, Object> json) {
		String newPassword = (String)json.get("newPassword");
		String oldPassword = (String)json.get("oldPassword");
		String username = authenticationFacade.getAuthentication().getName();
		User u = userService.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!(encoder.matches(oldPassword, u.getPassword() ))) {
			return new ResponseEntity<String>("Old password not true", HttpStatus.BAD_REQUEST);
		}
		
		u.setPassword(encoder.encode(newPassword));
		userService.save(u);
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
		UserDTO deleted = new UserDTO(userService.delete(id));

		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}