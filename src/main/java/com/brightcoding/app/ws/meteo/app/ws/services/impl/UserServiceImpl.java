package com.brightcoding.app.ws.meteo.app.ws.services.impl;

import com.brightcoding.app.ws.meteo.app.ws.entities.UserEntity;
import com.brightcoding.app.ws.meteo.app.ws.repositories.userRepositories;
import com.brightcoding.app.ws.meteo.app.ws.responses.UserResponse;
import com.brightcoding.app.ws.meteo.app.ws.services.UserService;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.AdressDto;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.UserDto;
import com.brightcoding.app.ws.meteo.app.ws.shared.dto.Utils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    userRepositories userRepositories;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    Utils utils;



    @Override
    public UserDto createUser(UserDto user) {

        UserEntity checkUser = userRepositories.findByEmail(user.getEmail());

        if(checkUser != null) throw new RuntimeException("User Already Exists");



        for(int i=0; i < user.getAdresses().size(); i++){
            AdressDto adress = user.getAdresses().get(i);
            adress.setUser(user);
            adress.setAdressId(utils.generateStringId(32));
            user.getAdresses().set(i, adress);
        }

        ModelMapper modelMapper = new ModelMapper();

        UserEntity userEntity = modelMapper.map(user,UserEntity.class);

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userEntity.setUserId(utils.generateStringId(32));

        UserEntity newUser = userRepositories.save(userEntity);

        UserDto userDto = modelMapper.map(newUser, UserDto.class);

        return userDto;
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity userEntity = userRepositories.findByEmail(email);

        if(userEntity == null) throw  new UsernameNotFoundException(email);

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;

       // return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

    }

    @Override
    public UserDto getUserByUserId(String userId) {

        UserEntity userEntity = userRepositories.findByUserId(userId);

        if(userEntity == null) throw  new UsernameNotFoundException(userId);

        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userEntity, userDto);

        return userDto;

    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {

        UserEntity userEntity = userRepositories.findByUserId(id);

        if(userEntity == null) throw  new UsernameNotFoundException(id);

        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());

        UserEntity userUpdated = userRepositories.save(userEntity);

        UserDto user = new UserDto();

        BeanUtils.copyProperties(userUpdated, user);

        return user;
    }

    @Override
    public void deleteUser(String UserId) {
        UserEntity userEntity = userRepositories.findByUserId(UserId);

        if(userEntity == null) throw  new UsernameNotFoundException(UserId);

        userRepositories.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit, String search, int status) {

        if(page > 0) page = page - 1;

        List<UserDto> userDto = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<UserEntity> userPage;
        if(search.isEmpty()){
            userPage = userRepositories.findAllUser((java.awt.print.Pageable) pageableRequest);
        }
        else{
            userPage = userRepositories.findAllUserByCriteria((java.awt.print.Pageable) pageableRequest, search, status);
        }

        List<UserEntity> users = userPage.getContent();

        for(UserEntity userEntity: users){

            ModelMapper modelMapper = new ModelMapper();
            UserDto user = modelMapper.map(userEntity, UserDto.class);

            userDto.add(user);
        }

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepositories.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }
}
