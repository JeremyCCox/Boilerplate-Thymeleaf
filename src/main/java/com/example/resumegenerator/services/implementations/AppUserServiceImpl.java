package com.example.resumegenerator.services.implementations;

import com.example.resumegenerator.entities.AppUser;
import com.example.resumegenerator.repositories.AppUserRepository;
import com.example.resumegenerator.services.interfaces.AppUserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository repository;


    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.repository = appUserRepository;
    }

    @Override
    public Optional<AppUser> findByUserEmail(String email) {
        return repository.findByUserEmail(email);
    }

    @Override
    public List<AppUser> findAll() {
        return repository.findAll();
    }

    @Override
    public AppUser getReferenceById(Integer id) {
        return repository.getReferenceById(id);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return repository.save(appUser);
    }

    @Override
    public boolean existsByUserEmail(String userEmail) {
        return repository.existsByUserEmail(userEmail);
    }

    @Override
    public boolean existsByUserId(Integer id) {
        return repository.existsById(id);
    }

    public void saveWithId(AppUser appUser){
        repository.saveWithId(appUser.getId(),appUser.getUserPassword(),appUser.getUserEmail(), appUser.getUserPasswordSalt());
    }

}
