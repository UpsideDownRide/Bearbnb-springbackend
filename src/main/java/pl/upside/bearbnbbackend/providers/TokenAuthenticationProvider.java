package pl.upside.bearbnbbackend.providers;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.upside.bearbnbbackend.services.UserAuthenticationService;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private final UserAuthenticationService auth;

    @Override
    protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
    }

    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken authentication) {
        final Object token = authentication.getCredentials();
        return Optional.ofNullable(token).map(String::valueOf).flatMap(auth::findByToken).map(u -> (UserDetails) u)
                .orElseThrow(() -> new UsernameNotFoundException("Couldn't find user: " + token));
    }
} 
