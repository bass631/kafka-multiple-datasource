package ru.multiple.datasource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ru.multiple.datasource.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    User findUserById(int id);
}