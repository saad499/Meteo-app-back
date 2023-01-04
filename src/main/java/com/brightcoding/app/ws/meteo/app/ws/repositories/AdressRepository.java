package com.brightcoding.app.ws.meteo.app.ws.repositories;

import com.brightcoding.app.ws.meteo.app.ws.entities.AdressEntity;
import com.brightcoding.app.ws.meteo.app.ws.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressRepository extends CrudRepository<AdressEntity, Long> {
    List<AdressEntity> findByUser(UserEntity userEntity);
}
