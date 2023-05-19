package peaksoft.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.springbootapi.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select  u from  User  u where  u.userName=:username")
    User getUserByName(@Param("username") String username) ;


    Optional<User> findByUserName(String username);

}
