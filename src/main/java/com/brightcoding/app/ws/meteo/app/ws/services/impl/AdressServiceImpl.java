package com.brightcoding.app.ws.meteo.app.ws.services.impl;

import com.brightcoding.app.ws.meteo.app.ws.entities.AdressEntity;
import com.brightcoding.app.ws.meteo.app.ws.entities.UserEntity;
import com.brightcoding.app.ws.meteo.app.ws.repositories.AdressRepository;
import com.brightcoding.app.ws.meteo.app.ws.repositories.userRepositories;
import com.brightcoding.app.ws.meteo.app.ws.responses.AdressResponse;
import com.brightcoding.app.ws.meteo.app.ws.services.AdressService;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.AdressDto;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.UserDto;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
@Service
@AllArgsConstructor
public class AdressServiceImpl implements AdressService {


    AdressRepository adressRepository;

    userRepositories userRepositories;

    Utils util;

    @Override
    public List<AdressDto> getAllAdresses(String email) {

        UserEntity currentUser = userRepositories.findByEmail(email);

        List<AdressEntity> adresses = currentUser.getAdmin() == true ? (List<AdressEntity>) adressRepository.findAll(): adressRepository.findByUser(currentUser);

        Type listType = new TypeToken<List<AdressDto>>() {}.getType();
        List<AdressDto> adressesDto = new ModelMapper().map(adresses, listType);

        return adressesDto;
    }

    @Override
    public AdressDto craeteAddress(AdressDto adress, String email) {
        UserEntity currentUser = userRepositories.findByEmail(email);

        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(currentUser, UserDto.class);

        adress.setAdressId(util.generateStringId(30));
        adress.setUser(userDto);

        AdressEntity addressEntity = modelMapper.map(adress, AdressEntity.class);

        AdressEntity newAdress = adressRepository.save(addressEntity);

        AdressDto adressDto = modelMapper.map(newAdress, AdressDto.class);

        return adressDto;
    }
}
