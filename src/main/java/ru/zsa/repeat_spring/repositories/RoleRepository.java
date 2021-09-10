package ru.zsa.repeat_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.repeat_spring.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
