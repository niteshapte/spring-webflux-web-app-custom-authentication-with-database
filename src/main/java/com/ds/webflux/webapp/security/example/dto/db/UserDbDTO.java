package com.ds.webflux.webapp.security.example.dto.db;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDbDTO implements UserDetails {

	@Serial
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Integer Id;

	@JsonProperty("access_level_id")
	private Integer accessLevelId;
	
	@JsonProperty("access_name")
	private String accessName;
	
	@JsonProperty("access_level")
	private String accessLevel;
	
	@JsonProperty("user_uid")
	private String userUid;
	
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("middle_name")
	private String middleName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("is_active")
	private String isActive;
	
	@JsonProperty("is_deleted")
	private String isDeleted;
	
	@JsonProperty("last_login")
	private LocalDateTime lastLogin;
	
	public Collection<? extends GrantedAuthority> authorities;
	
	private boolean enabled;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;
    
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountNonExpired;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountNonLocked;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(getAccessLevel()));
	}
}