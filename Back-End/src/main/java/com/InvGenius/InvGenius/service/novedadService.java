package com.InvGenius.InvGenius.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InvGenius.InvGenius.interfaceService.InovedadService;
import com.InvGenius.InvGenius.interfaces.Inovedad;
import com.InvGenius.InvGenius.models.novedad;

@Service
public class novedadService implements InovedadService {
    
    @Autowired
    private Inovedad data;

    @Override
    public String save(novedad novedad) {
        data.save(novedad);
        return novedad.getIdNovedad();
    }

    @Override
    public List<novedad> findAll() {
        List<novedad> listaNovedad = (List<novedad>) data.findAll();
        return listaNovedad;
    }

    @Override
    public Optional<novedad> findOne(String id) {
        Optional<novedad> novedad = data.findById(id);
        return novedad;
    }

    @Override
    public List<novedad> novedadExist(String asunto) {
        List<novedad> listaNovedad = data.novedadExist(asunto);
        return listaNovedad;
    }

    @Override
    public List<novedad> fechaDeNovedad(Date fechaNovedad) {
        List<novedad> listaNovedad = data.fechaDeNovedad(fechaNovedad);
        return listaNovedad;
    }

    @Override
    public int delete (String id) {
        data.deleteById(id);
        return 1;
    }

        @Override
	public int guardarimagenJson(novedad novedad) {
		int res=0;
		novedad =data.save(novedad);
		if(novedad.equals(null)) {
			res=1;
		}
		return res;
	}
}
