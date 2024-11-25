//package com.example.Web0.utils;
//
//import com.example.Web0.entities.UserEntity;
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jwt.JWTClaimsSet;
//import org.springframework.stereotype.Component;
//
//import java.time.Instant;
//import java.util.Date;
//import java.util.stream.Collectors;
//
//@Component
//public class JwtUtil {
//
//    private final String SEY_KEY = "LmCvzYozUk1bEXD5xIXJxqz00lRjdFNJ";
//
//    public String generateToken(UserEntity userEntity) {
//        try {
//            String scope= userEntity.getRoles().stream()
//                    .map(role -> "ROLE_" + role.getRoleName())  // Thêm tiền tố "ROLE_"
//                    .collect(Collectors.joining(" "));
//            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
//
//            JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                    .subject(userEntity.getUsername())
//                    .issuer(userEntity.getUsername())
//                    .issueTime(new Date())
//                    .expirationTime(new Date(
//                            Instant.now().plusSeconds(60 * 60 * 24 * 7).toEpochMilli()
//                    ))
//                    .claim("scope", scope)
//                    .build();
//
//            Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//            JWSObject jwsObject = new JWSObject(header, payload);
//            jwsObject.sign(new MACSigner(SEY_KEY));
//
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
