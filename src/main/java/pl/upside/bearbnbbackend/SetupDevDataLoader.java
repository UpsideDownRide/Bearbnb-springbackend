package pl.upside.bearbnbbackend;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.upside.bearbnbbackend.model.ERoles;
import pl.upside.bearbnbbackend.model.Role;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.repositories.RoleRepository;
import pl.upside.bearbnbbackend.services.UserService;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class SetupDevDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean setupDone = false;

    private final RoleRepository roleRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupDone) { return; }
        for (ERoles role : ERoles.values()) {
            createRole(role);
        }
        Role adminRole = roleRepository.findByName(ERoles.ROLE_ADMIN.name())
                .orElse(new Role(ERoles.ROLE_ADMIN.name()));
        User testUser = new User();
        testUser.setEmail("t1@t.com");
        testUser.setPassword(passwordEncoder.encode("test"));
        testUser.setRoles(Set.of(adminRole));
        createUser(testUser);
    }

    @Transactional
    public void createUser(User user){
        if(userService.findByEmail(user.getEmail()).isEmpty()) { userService.save(user); }
    }

    @Transactional
    void createRole(ERoles erole) {
        String name = erole.name();
        Role role = new Role(erole.name());
        if(roleRepository.findByName(name).isEmpty()) { roleRepository.save(role); }
    }
}
