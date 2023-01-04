package com.brightcoding.app.ws.meteo.app.ws.services;

import com.brightcoding.app.ws.meteo.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);

    UserDto getUser(String email);

    UserDto getUserByUserId(String userId);

    UserDto updateUser(String id, UserDto userDto);

    void deleteUser(String UserId);

    List<UserDto> getUsers(int page, int limit, String search, int status);
}
