package ru.practicum.features.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.features.user.model.User;
import ru.practicum.features.user.service.UserService;
import ru.practicum.exceptions.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    UserService userService;


    @PostMapping
    public User postUser(@RequestBody User user) throws ValidationException, DuplicationException {
        return userService.postUser(user);
    }

    @PatchMapping("/{userId}")
    public User patchUser(@PathVariable("userId") Long userId, @RequestBody User user)
            throws ValidationException, DuplicationException, NotFoundException {

        return userService.patchUser(userId, user);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long userId) throws NotFoundException {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}
