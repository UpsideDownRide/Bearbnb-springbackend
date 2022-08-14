package pl.upside.bearbnbbackend.filters;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static pl.upside.bearbnbbackend.utils.UtilityFunctions.removeStart;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final Optional<String> param = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));
        final String token = param.map(v -> removeStart(v, "Bearer")).map(String::trim).orElse("");

        if (token.isBlank() || token.isEmpty()) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(token, token);
        authenticationManager.authenticate(authReq);

        chain.doFilter(request, response);
    }
}
