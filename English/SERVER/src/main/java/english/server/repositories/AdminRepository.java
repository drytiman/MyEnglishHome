package english.server.repositories;

import english.server.model.Admin;
import english.server.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    @Query("SELECT con FROM Admin con  WHERE con.login=(:login)")
    Admin findByAdminlogin(@Param("login") String login);
}
