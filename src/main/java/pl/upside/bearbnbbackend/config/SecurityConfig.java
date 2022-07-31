package pl.upside.bearbnbbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pl.upside.bearbnbbackend.repositories.UserRepository;
import pl.upside.bearbnbbackend.services.DBUserDetailsService;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().and()
                .csrf().ignoringAntMatchers(PublicRoutes.getAll()).and()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers(Routes.SIGNUP.getRoute()).permitAll()
                        .antMatchers(Routes.LOGIN.getRoute()).permitAll()
                        .anyRequest().authenticated())
                .httpBasic();
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new DBUserDetailsService(userRepository);
    }

}
