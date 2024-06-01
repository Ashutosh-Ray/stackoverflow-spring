package com.stackoverflow.services.user;

import com.stackoverflow.dtos.SignupDTO;
import com.stackoverflow.dtos.UserDTO;

public interface UserService {
    UserDTO createUser(SignupDTO signupDTO);

    boolean hasUserWithEmail(String email);
}
