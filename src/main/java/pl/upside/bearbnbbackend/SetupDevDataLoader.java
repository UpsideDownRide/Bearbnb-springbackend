package pl.upside.bearbnbbackend;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.upside.bearbnbbackend.model.ERoles;
import pl.upside.bearbnbbackend.model.Role;
import pl.upside.bearbnbbackend.repository.RoleRepository;

@Component
public class SetupDevDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    boolean setupDone = false;

    private RoleRepository roleRepository;

    public SetupDevDataLoader(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupDone) { return; }
        for (ERoles role : ERoles.values()) {
            createRole(role);
        }

    }

    @Transactional
    void createRole(ERoles erole) {
        String name = erole.name();
        Role role = new Role(erole.name());
        if(roleRepository.findByName(name).isEmpty()) { roleRepository.save(role); }
    }
}
