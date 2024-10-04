package com.InvGenius.InvGenius.interfaceService;

import java.util.List;
import java.util.Optional;

import com.InvGenius.InvGenius.models.authResponse;
import com.InvGenius.InvGenius.models.registerRequest;
import com.InvGenius.InvGenius.models.user;

public interface IuserService {
    
    public String save(user user);

    public List<user> findAll();

    public List<user> userExist(String correo, String documentoIdentidad);

    public Optional<user> findOne(String id);

    //cambiar el int
    public int delete(String id);

    public authResponse register(registerRequest request);

    public Optional<user> findByUsername(String userName);

    public List<user> consultaruser();

    public int guardarimagenJson(user user);

}
