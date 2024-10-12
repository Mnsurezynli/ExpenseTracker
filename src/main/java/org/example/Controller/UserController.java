package org.example.Controller;

import org.example.Dto.UserDto;
import org.example.Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private IUserService iUserService;

    @Autowired
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated UserDto userDto) {
        iUserService.register(userDto);
        return new ResponseEntity<>("Registration was successful", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> Login(@RequestBody @Validated UserDto userDto) {
        iUserService.login(userDto);
        return new ResponseEntity<>("Login was successful", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> users = iUserService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
