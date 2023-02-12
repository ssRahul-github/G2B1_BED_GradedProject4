package com.greatlearning.employee.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class DomainUserDetails implements UserDetails {
	
	private final String username;
	private final String password;
	private final List<GrantedAuthority> authorities;
	public DomainUserDetails(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.authorities=user.getRoles()
				.stream()
				.map(Role::getRoleName)
				.map(roleName->"ROLE_"+roleName)
				.map(SimpleGrantedAuthority::new)	
				.collect(Collectors.toList());
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		System.out.println(" **********************************");
		System.out.println(this.authorities);
		System.out.println(" **********************************");
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
		
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}