package com.controle.financeiro.service;

import com.controle.financeiro.model.dto.LoginDTO;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.interfaces.RSAPrivateKey;
import java.util.ArrayList;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${jwt.private.key}")
    private RSAPrivateKey privateKey;

    private final AuthenticationManager authenticationManager;

    @PostMapping
    public String login(@RequestBody LoginDTO loginRequest) throws JOSEException {
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

        Authentication authenticate = authenticationManager.authenticate(user);
        ArrayList<String> authorities = new ArrayList<>();
        authenticate.getAuthorities().forEach(role -> authorities.add(String.valueOf(role)));

        JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(com.nimbusds.jose.JOSEObjectType.JWT)
                .keyID("key-1")
                .build();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(authenticate.getName())
                .issuer("MinhaAPI")
                .claim("role", authorities)
                .expirationTime(new Date(new Date().getTime() + 60 * 1000))
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);

        jwt.sign(new RSASSASigner(privateKey));

        return jwt.serialize();
    }
}
