package com.example.resumegenerator.auth;

import com.example.resumegenerator.entities.AppUser;
import com.example.resumegenerator.repositories.AppUserRepository;
import com.example.resumegenerator.services.implementations.AppUserServiceImpl;
import com.example.resumegenerator.services.implementations.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    private final AppUserServiceImpl appUserService;

    @Autowired
    private PasswordEncoder encoder;

    public AuthUtils(AppUserRepository appUserRepository){
        this.appUserService = new AppUserServiceImpl(appUserRepository);
    }

    public void updateUserPassword(UserDetailsImpl userDetails, String newPassword){
        AppUser appUser = appUserService.getReferenceById(userDetails.getId());
        appUser.setUserPassword(encoder.encode(newPassword));
        appUserService.save(appUser);
    }
    public void updateUserPasswordById(Integer id, String newPassword){
        AppUser appUser = appUserService.getReferenceById(id);
        appUser.setUserPassword(encoder.encode(newPassword));
        appUserService.save(appUser);
    }
    public void updateUserEmail(UserDetailsImpl userDetails, String newEmail){
        AppUser appUser = appUserService.getReferenceById(userDetails.getId());
        appUser.setUserEmail(newEmail);
        appUserService.save(appUser);
    }
    public void updateUserEmailById(Integer id, String newEmail){
        AppUser appUser = appUserService.getReferenceById(id);
        appUser.setUserEmail(newEmail);
        appUserService.save(appUser);
    }
}
