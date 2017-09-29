package app.repos;

import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query("select u from User u where u.username = :username and u.status=1")
    Optional<User> findByUsername(@Param(value = "username") String username);

    @Query("select u from User u where u.id = :id and u.status=1")
    Optional<User> findById(@Param(value = "id") Integer id);

    @Modifying
    @Query("update User u set u.status = :status where u.id = :userId")
    @Transactional
    void setStatus(@Param(value = "userId") Integer userId, @Param(value = "status") Integer status);

}
