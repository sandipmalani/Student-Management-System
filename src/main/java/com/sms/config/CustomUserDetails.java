package com.sms.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.sms.entities.Admin;
import com.sms.entities.Student;
import com.sms.entities.Teacher;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static CustomUserDetails buildAdmin(Admin admin) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new CustomUserDetails(admin.getEmail(), admin.getPassword(), authorities);
    }

    public static CustomUserDetails buildTeacher(Teacher teacher) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_TEACHER"));
        return new CustomUserDetails(teacher.getEmail(), teacher.getPassword(), authorities);
    }

    public static CustomUserDetails buildStudent(Student student) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT"));
        return new CustomUserDetails(student.getEmail(), student.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Add logic as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Add logic as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Add logic as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Add logic as needed
    }

    
}
