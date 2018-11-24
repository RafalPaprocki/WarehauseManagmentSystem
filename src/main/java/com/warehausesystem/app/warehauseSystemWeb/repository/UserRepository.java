package com.warehausesystem.app.warehauseSystemWeb.repository;
import com.warehausesystem.app.warehauseSystemWeb.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Long> {
    
}
