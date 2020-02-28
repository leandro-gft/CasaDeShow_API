package br.com.gft.casadeshowapi.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gft.casadeshowapi.domain.Role;
import br.com.gft.casadeshowapi.domain.User;
import br.com.gft.casadeshowapi.repository.UserRepository;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  User user = userRepository.findByUsername(username);
		  Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		  Role role = user.getRole();
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
	        	
		  return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	
	
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		  User user = userRepository.findByUsername(username);
//	        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//	        for (Role role : user.getRoles()){
//	            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//	        }	
//	       	        
//
//	        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//}
}