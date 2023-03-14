package org.ntnu.rest.Repository;

import org.ntnu.rest.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
