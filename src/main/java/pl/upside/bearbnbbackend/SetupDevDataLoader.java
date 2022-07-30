package pl.upside.bearbnbbackend;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.upside.bearbnbbackend.model.ERoles;
import pl.upside.bearbnbbackend.model.Role;
import pl.upside.bearbnbbackend.model.User;
import pl.upside.bearbnbbackend.repository.RoleRepository;
import pl.upside.bearbnbbackend.repository.UserRepository;

@Component
public class SetupDevDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean setupDone = false;

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public SetupDevDataLoader(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupDone) { return; }
        for (ERoles role : ERoles.values()) {
            createRole(role);
        }
        Role adminRole = new Role(ERoles.ROLE_ADMIN.name());
        User testUser = new User();
        testUser.setEmail("test@test.com");
        testUser.setPassword("test");
        createUser(testUser);
    }

    @Transactional
    public void createUser(User user){
        if(userRepository.findByEmail(user.getEmail()).isEmpty()) { userRepository.save(user); }
    }

    @Transactional
    void createRole(ERoles erole) {
        String name = erole.name();
        Role role = new Role(erole.name());
        if(roleRepository.findByName(name).isEmpty()) { roleRepository.save(role); }
    }
}
