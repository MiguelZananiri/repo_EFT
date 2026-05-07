package com.duoc.backend;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;

public final class Constants {

    // Constructor privado para evitar instancias
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Spring Security
    public static final String LOGIN_URL = "/login";
    public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";

    // JWT
    public static final String ISSUER_INFO = "https://www.duocuc.cl/";

    // Obtener la clave desde variables de entorno
    public static final String SUPER_SECRET_KEY =
            System.getenv("JWT_SECRET_KEY");
}