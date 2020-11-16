package com.library.repository;

import com.library.model.enums.EnumRole;
import com.library.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByEnumRole(EnumRole enumRole);
}
