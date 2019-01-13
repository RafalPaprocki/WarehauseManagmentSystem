package com.warehausesystem.app.warehauseSystemWeb.repository;

import java.util.List;
import java.util.Optional;

import com.warehausesystem.app.warehauseSystemWeb.model.Article;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("Select u from User u " +
            "where u.name like CONCAT('%',:name,'%') " +
            "and u.surname like CONCAT('%',:surname, '%')" +
            "and u.id like CONCAT('%', :id, '%')")
    List<User> findUserFiltered(@Param("name") String name,
                                      @Param("surname") String surname,
                                      @Param("id") String id
    );
}