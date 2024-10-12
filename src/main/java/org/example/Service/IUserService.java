package org.example.Service;

import org.example.Dto.ExpenseDto;
import org.example.Dto.UserDto;

import java.util.List;

public interface IUserService {


    UserDto register(UserDto userDto);

    void  login(UserDto userDto);

    List<UserDto> getAll();
}
