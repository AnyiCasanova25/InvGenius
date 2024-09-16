package com.InvGenius.InvGenius.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.InvGenius.InvGenius.models.user;

@Repository
public interface Iuser extends CrudRepository<user, String> {

    @Query("SELECT u FROM user u WHERE u.celular LIKE %?1% OR u.correo LIKE %?2%")
    List<user> userExist(String celular, String correo);

    @Query("SELECT u FROM user u WHERE u.correo = ?1")
    Optional<user> findByUsername(String username);
    
}
