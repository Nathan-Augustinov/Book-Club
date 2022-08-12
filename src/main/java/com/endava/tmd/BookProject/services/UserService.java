package com.endava.tmd.BookProject.services;

import com.endava.tmd.BookProject.authentication.ApplicationUserService;
import com.endava.tmd.BookProject.jwt.JwtConfig;
import com.endava.tmd.BookProject.jwt.UsernameAndPasswordAuthenticationRequest;
import com.endava.tmd.BookProject.models.User;
import com.endava.tmd.BookProject.repositories.UserRepository;
import com.endava.tmd.BookProject.utils.Utils;
import io.jsonwebtoken.Jwts;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private JwtConfig jwtConfig;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Object getUserById(Long userId){
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .body("");
        }
        return user;
    }

    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }

    public ResponseEntity<?> addUser(User user){
        List<User> usersList = userRepository.findAll();
        for (User user1 : usersList) {
            if(user1.getUsername().equals(user.getUsername())){
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Username already exists!");
            }
        }
        user.setPassword(Utils.encodedPassword(user.getPassword()));
        userRepository.saveAndFlush(user);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User created successfully");
    }

    public ResponseEntity<?> loginUser(UsernameAndPasswordAuthenticationRequest authenticationRequest){

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );

        try{
            authenticationManager.authenticate(authentication);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("authorities", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(jwtConfig.getTokenValidityTimeInHours())))
                .signWith(jwtConfig.getSecretKeyForSigning())
                .compact();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(jwtConfig.getAuthorizationHeader(), jwtConfig.getTokenPrefix() + token);
        User user = userRepository.findUserByUsername(authentication.getName());
        user.setToken(token);
        userRepository.saveAndFlush(user);
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }
        else{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Username", user.getUsername());
            jsonObject.put("UserFirstname", user.getFirstname());
            jsonObject.put("UserLastname", user.getLastname());
            jsonObject.put("UserEmail", user.getEmail());
            jsonObject.put("JWTToken", user.getToken());
            return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(jsonObject.toString());
        }
    }

    public ResponseEntity<?> updateUser(Long userId, User user){
        User existingUser = userRepository.findById(userId).orElse(null);
        if(existingUser == null){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("User id given not a correct one!");
        }
        BeanUtils.copyProperties(user, existingUser, "userId","createdOn");
        userRepository.saveAndFlush(existingUser);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User updated successfully!");
    }


}
