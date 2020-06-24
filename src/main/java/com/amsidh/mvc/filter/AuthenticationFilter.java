package com.amsidh.mvc.filter;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static io.jsonwebtoken.Jwts.parser;

public class AuthenticationFilter extends BasicAuthenticationFilter {

    private final Environment environment;
    public AuthenticationFilter(Environment environment, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));
        //Remove Bearer part from authorization
        if (null == authorizationHeader || !authorizationHeader.startsWith(environment.getProperty("authorization.token.header.prefix"))) {
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getUsernamePasswordAuthenticationToken(request);
        //We need to add above retried user token to the existing request security.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        filterChain.doFilter(request,response);

    }

    private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(environment.getProperty("authorization.token.header.name"));
        if (null == authorizationHeader) {
            return null;
        }
        String jwtToken = authorizationHeader.replaceAll(environment.getProperty("authorization.token.header.prefix"), "");
        String userId = parser()
                .setSigningKey(environment.getProperty("jwt.token.secret"))
                .parseClaimsJws(jwtToken).getBody().getSubject();
        if (null == userId) {
            return null;
        }
        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
