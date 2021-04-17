package fi.hh.swd20.shaderlib;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.shaderlib.domain.User;
import fi.hh.swd20.shaderlib.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void adminUserCreatedOnStartup() {
        User user = repository.findByUsername("admin").get();
        assertNotNull(user);
    }

    @Test
    public void createNewUser() {
        User user = repository.save(new User("Juuso", "test@moi", "12345", "TEST"));
        assertThat(user.getId()).isGreaterThan(0l);
    }

    @Test
    public void deleteUser() {
        User user = repository.findByUsername("admin").get();
        assertNotNull(user);

        repository.deleteById(user.getId());
        assertThat(repository.findById(user.getId()).isPresent()).isFalse();
    }
    
}
