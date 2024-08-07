package com.celisapp.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.stereotype.Service;

import com.celisapp.data.vo.v1.security.NewUserVO;
import com.celisapp.model.User;
import com.celisapp.repositories.UserRepository;

@Service
public class UserServices implements UserDetailsService {
	
	 
	private Logger logger = Logger.getLogger(UserServices.class.getName());
	
	@Autowired
	UserRepository repository;
	
	public UserServices(UserRepository repository) {
		this.repository = repository;
	}
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("Finding one user by name " + username + "!");
		var user = repository.findByUsername(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("Username " + username + " not found!");
		}
	}
	
	public UserDetails createUser(NewUserVO data) {
		var user = new User();
		user.setUserName(data.getUsername());
		user.setEmail(data.getEmail());
		user.setPassword( getPasswordEncripted(data.getPassword()));
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		var newUser = repository.save(user);
		var newUserDetails = repository.findByUsername(newUser.getUserName());
		return newUserDetails ;
	}
	
	private String getPasswordEncripted(String password) {
		 Map<String, PasswordEncoder> encoders = new HashMap<>();
	      
	      Pbkdf2PasswordEncoder pbkdf2Encoder =
	      		new Pbkdf2PasswordEncoder(
	  				"", 8, 185000,
	  				SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
	      
	      encoders.put("pbkdf2", pbkdf2Encoder);
	      DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
	      passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
	      
	      String passwordEncripted = passwordEncoder.encode(password);
	      System.out.println("My hash result1 " + passwordEncripted);
	      passwordEncripted = passwordEncripted.split("}")[1];
	      System.out.println("My hash result1 " + passwordEncripted);
		return passwordEncripted;
		
	}
}
