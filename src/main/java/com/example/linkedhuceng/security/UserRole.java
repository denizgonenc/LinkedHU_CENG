package com.example.linkedhuceng.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
    NOTSPECIFIED(Sets.newHashSet()),
    STUDENT(Sets.newHashSet(UserAuthority.POST_READ, UserAuthority.STUDENT_READ)),
    ACADEMICIAN(Sets.newHashSet(UserAuthority.POST_READ, UserAuthority.STUDENT_READ, UserAuthority.POST_WRITE)),
    GRADUATE(Sets.newHashSet(UserAuthority.POST_READ, UserAuthority.STUDENT_READ, UserAuthority.POST_WRITE)),
    ADMIN(Sets.newHashSet(  UserAuthority.POST_READ,
                            UserAuthority.POST_WRITE,
                            UserAuthority.STUDENT_READ,
                            UserAuthority.STUDENT_WRITE));

    private final Set<UserAuthority> authorities;

    UserRole(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<UserAuthority> getAuthorities() {
        return authorities;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> authoritySet = getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());
        authoritySet.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authoritySet;
    }
}
