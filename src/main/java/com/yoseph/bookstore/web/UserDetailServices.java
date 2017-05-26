package com.yoseph.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yoseph.bookstore.domain.User;
import com.yoseph.bookstore.domain.UserRepository;


/**
 * This class is used by spring security to authenticate and authorize user
 **/
@Service
public class UserDetailServices implements UserDetailsService{
	private final UserRepository repository;
	
	@Autowired
	public UserDetailServices(UserRepository userRepository) {
		this.repository = userRepository;
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    } 
}
