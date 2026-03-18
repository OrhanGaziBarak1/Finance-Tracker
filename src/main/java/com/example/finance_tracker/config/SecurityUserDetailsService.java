package com.example.finance_tracker.config;

import com.example.finance_tracker.model.User;
import com.example.finance_tracker.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUserDetailsService implements UserDetailsService {
  private final UserRepository repository;

  @Override
  public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = repository.findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    return new UserPrincipal(user);
  }

  public static class UserPrincipal implements UserDetails {
    private final User user;

    public UserPrincipal(User user) {
      this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of();
    }

    @Override
    public @Nullable String getPassword() {
      return user.getPassword();
    }

    @Override
    public String getUsername() {
      return user.getEmail();
    }
  }
}
