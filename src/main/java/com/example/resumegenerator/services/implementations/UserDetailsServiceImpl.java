package com.example.resumegenerator.services.implementations;

import com.example.resumegenerator.entities.AppUser;
import com.example.resumegenerator.repositories.AppUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserServiceImpl appUserService;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository){
        this.appUserService = new AppUserServiceImpl(appUserRepository);
    }
    @Transactional
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        AppUser user = appUserService.findByUserEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email :"+email));
        return UserDetailsImpl.build(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = appUserService.findByUserEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with email :"+email));
        return UserDetailsImpl.build(user);
    }
}
