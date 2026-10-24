package com.controle.financeiro.controller;

import com.controle.financeiro.model.dto.LoginDTO;
import com.controle.financeiro.model.dto.LoginResponseDTO;
import com.controle.financeiro.service.TokenService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/login")
//@CrossOrigin("*")
@RequiredArgsConstructor
public class LoginController {

    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginRequest) throws JOSEException {

        return ResponseEntity.ok(new LoginResponseDTO(tokenService.login(loginRequest)));
    }
}
