package org.example.Service.Impl;

import org.example.Dto.ExpenseDto;
import org.example.Dto.UserDto;
import org.example.Exception.InvalidInputException;
import org.example.Exception.ResourceAlreadyExistsException;
import org.example.Model.Expense;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(UserDto userDto) {
        Optional<User> user = userRepository.findByUserName(userDto.getUserName());
        if (user.isPresent()) {
            throw new ResourceAlreadyExistsException("this user already exists");
        }
        User user1 = convertToEntity(userDto);
        user1 = userRepository.saveAndFlush(user1);
        UserDto userDto1 = convertToDto(user1);
        return userDto1;


    }

    @Override
    public void login(UserDto userDto) {
        Optional<User> user = userRepository.findByUserNameAndPassword(userDto.getUserName(), userDto.getPassword());
        if (user.isPresent()) {
            System.out.println("login was successfull");
        } else {
            throw new InvalidInputException("invalid username or password");
        }

    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertToDto).collect(Collectors.toList());

    }


    public UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setExpense(user.getExpense());
        return userDto;
    }

    public User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setExpense(userDto.getExpense());
        return user;
    }
}
