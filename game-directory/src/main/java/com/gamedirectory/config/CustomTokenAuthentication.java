package com.gamedirectory.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class CustomTokenAuthentication implements Authentication {
    private boolean isAuthenticated;
    public CustomTokenAuthentication(boolean isAuthenticated) {
        this.setAuthenticated(isAuthenticated);
    }
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }
    public void setAuthenticated(boolean authenticated) {
        this.isAuthenticated = authenticated;
    }
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }
    public Object getCredentials() {
        return null;
    }
    public Object getDetails() {
        return null;
    }
    public Object getPrincipal() {
        return null;
    }
    public String getName() {
        return null;
    }
}
