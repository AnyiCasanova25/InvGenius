package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.user;

public interface IuserService {
    
    public String save(user user);

    public List<user> findAll();

    public Optional<user> findOne(String id);
}
