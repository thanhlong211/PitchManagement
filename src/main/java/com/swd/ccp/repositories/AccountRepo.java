package com.swd.ccp.repositories;

import com.swd.ccp.enums.Role;
import com.swd.ccp.models.entity_models.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {

    Optional<Account> findByEmail(String email);
    List<Account> findByIdAndRoleNot(Integer shopOwnerId, Role role,Sort sort);

    Optional<Account> findByEmailAndPassword(String email, String password);

    List<Account> findByRole(Role role);
    Optional<Account> findByEmailAndRole(String email, Role role);
    List<Account> findAll();
    @Query("SELECT a FROM Account a WHERE a.role <> 2")
    List<Account> findAllNonAdminAccounts();
}
