package io.badri.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.badri.app.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
