package com.InvGenius.InvGenius.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.InvGenius.InvGenius.models.user;

public interface Iuser extends CrudRepository<user , String>{


    @Query("SELECT u FROM user u WHERE u.Celular = ?1 OR u.Correo = ?2")
    List<user> userExist(String Celular,String Correo);
}
