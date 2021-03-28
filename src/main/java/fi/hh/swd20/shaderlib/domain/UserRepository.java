package fi.hh.swd20.shaderlib.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long>  {
    User findByUsername(String username);
}
