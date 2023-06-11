package peaksoft.springbootapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.springbootapi.entity.Company;
import peaksoft.springbootapi.entity.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email =:email")
    User getUserByUsername(@Param("username") String email);

    Optional<User>findByEmail(String email);
    @Query("select c from User c where  upper(c.firstName) like  concat('%',:text,'%') " +
            "or upper(c.lastName) like  concat('%',:text,'%') " +
            "or  upper(c.email) like concat('%',:text,'%') " +
            "or upper(c.password) like concat('%',:text,'%') ")
    List<User> searchAndPagination(@Param("text")String text, Pageable pageable);

}
