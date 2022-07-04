/**
 * 
 */
package com.sogeti.filmland.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sogeti.filmland.request.model.AuthRequestModel;
import com.sogeti.filmland.utils.JwtUtil;


/**
 * @author monal500
 *
 */
@RestController
public class AuthenticateController {
	
	@Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

	
	@PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequestModel authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inValid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
