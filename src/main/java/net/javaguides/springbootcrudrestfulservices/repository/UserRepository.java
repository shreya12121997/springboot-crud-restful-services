package net.javaguides.springbootcrudrestfulservices.repository;

import net.javaguides.springbootcrudrestfulservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}