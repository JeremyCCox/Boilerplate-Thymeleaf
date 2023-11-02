package com.example.resumegenerator.services.interfaces;

import com.example.resumegenerator.entities.AppUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AppUserService {

    Optional<AppUser> findByUserEmail(String email);

    Iterable<AppUser> findAll();

    AppUser getReferenceById(Integer id);

    AppUser save(AppUser appUser);
//    @Transactional
    void saveWithId(AppUser appUser);
    boolean existsByUserEmail(String userEmail);

    boolean existsByUserId(Integer preferredId);

}
