package english.server.repositories;

import english.server.model.Student;
import english.server.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT con FROM Teacher con  WHERE con.login=(:login)")
    Teacher findByTeacherlogin(@Param("login") String login);
}