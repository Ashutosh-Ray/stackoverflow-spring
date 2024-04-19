package com.stackoverflow.controllers;

import com.stackoverflow.dtos.AuthenticationRequestDTO;
import com.stackoverflow.dtos.AuthenticationResponseDTO;
import com.stackoverflow.entities.User;
import com.stackoverflow.repositories.UserRepository;
import com.stackoverflow.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING= "Authorization";

    @PostMapping("/auth")
    public void createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO, HttpServletResponse response) throws IOException, JSONException {
        try{
            //authenticate a user by verifying the provided email and password against the configured authentication manager.
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getEmail(), authenticationRequestDTO.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect Email or password");
        }catch (DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not created");
            return;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDTO.getEmail());
        //Get user by email
        Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());

        //Generate the token for the user
        final String jwt = jwtUtil.generateToken(userDetails);

        if (optionalUser.isPresent()){
            //return json object contain the userId
            response.getWriter().write(new JSONObject().put("userId", optionalUser.get().getId()).toString());
        }
        //Send in the header
        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, X-Requested-With, Content-Type, Accept, X-Custom-header");
        // exp Authorization : Bearer JWTkjfhgkfjhgf45h3g
        response.setHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
    }
}
