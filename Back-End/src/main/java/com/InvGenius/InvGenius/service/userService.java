package com.InvGenius.InvGenius.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.IuserService;
import com.InvGenius.InvGenius.interfaces.Iuser;
import com.InvGenius.InvGenius.models.user;

@Service
public class userService implements IuserService{
    
    @Autowired
    private Iuser data;

    @SuppressWarnings("null")
    @Override
    public String save(user user) {
        data.save(user);
        return user.getIdUser();
    }

    @Override
    public List<user> findAll(){
        List<user> listaUser = (List<user>) data.findAll();
        return listaUser;
    }

    @Override
    public Optional<user> findOne(String id){
        @SuppressWarnings("null")
        Optional<user> user = data.findById(id);
        return user;
    }

    @Override
    public List<user> userExist(String Celular, String Correo) {
            List<user> listaUser =data.userExist( Celular,  Correo);
            return listaUser;
    }

    //corregiiiiiirrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    //tipo de dato de retorno IIIIIIIIIIIIIIIIIIIIIIIINNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
    @Override
    public int delete(String id) {
        data.deleteById(id);
        return 1;
    }
}
