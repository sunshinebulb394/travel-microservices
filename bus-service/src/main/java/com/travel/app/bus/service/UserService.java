package com.travel.app.bus.service;

import com.travel.app.bus.dto.ResponseMessage;
import com.travel.app.bus.pojo.users.User;
import com.travel.app.bus.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers(){
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.setTokens(null)); // set the tokens to null
        return users;
    }

    public ResponseMessage revokeUser(Integer id) {
        Optional<User> opt = userRepository.findById(id);

        if (opt.isPresent()) {
            User user = opt.get();
            if (user.isAccountNonLocked()) {
                user.setAccountNonLocked(false);
                user.setAccountEnabled(false);
                userRepository.save(user);
                return new ResponseMessage("User has been blocked");
            } else {
                user.setAccountNonLocked(true);
                user.setAccountEnabled(true);
                userRepository.save(user);
                return new ResponseMessage("User has been unblocked");
            }
        }
        return new ResponseMessage("User not found");
    }


    public ResponseMessage deleteUser(Integer id){
        userRepository.deleteById(id);

        return ResponseMessage.builder().message("User deleted").build();
    }
}
