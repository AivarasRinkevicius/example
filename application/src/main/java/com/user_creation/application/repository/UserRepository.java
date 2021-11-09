package com.user_creation.application.repository;
import com.user_creation.application.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
