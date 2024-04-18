package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.user;

public interface IuserService {
    
    public String save(user user);

    public List<user> findAll();

    public List<user> userExist(String Celular, String Correo);

    public Optional<user> findOne(String id);

    public int delete(String id);


}
