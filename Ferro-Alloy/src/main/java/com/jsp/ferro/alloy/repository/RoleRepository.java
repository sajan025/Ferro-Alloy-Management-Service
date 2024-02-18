package com.jsp.ferro.alloy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jsp.ferro.alloy.model.Role;
/**
 * @author Sajan Yadav
 * @apiNote DO crud opration related to role
 * @Date 12 Sept 2023
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleId(Integer roleId);

}
