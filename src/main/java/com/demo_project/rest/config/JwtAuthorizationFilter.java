package com.demo_project.rest.config;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.demo_project.rest.util.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JwtAuthorizationFilter extends OncePerRequestFilter {


    private final String secretKey = "qazwsxedcrfvtgb@d^&&^*(*geghytukyytjhr43";
    private final JWTUtil jwtUtil;

    public JwtAuthorizationFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains("/login")) {
            filterChain.doFilter(request, response);
        } else {

            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {


                try {

                    String accessToken = authHeader.substring(7);

                    DecodedJWT decodedJWT = jwtUtil.validateToken(accessToken);

                    String username = decodedJWT.getSubject();
                    String role = decodedJWT.getClaim("role").asString();


                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken(username, null, Collections.singleton(new SimpleGrantedAuthority(role)));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);
                }  catch (Exception exception){
                    response.sendError(401,exception.getMessage());
                }


            } else {

                    filterChain.doFilter(request,response);

            }


        }


    }
}
