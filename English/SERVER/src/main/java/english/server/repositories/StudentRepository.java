package english.server.repositories;

import english.server.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT con FROM Student con  WHERE con.login=(:login)")
    Student findByStudentlogin(@Param("login") String login);
    @Query("SELECT con FROM Student con  WHERE con.yourTeacher=(:yourTeacher)")
    List<Student> findByStudentTeacher(@Param("yourTeacher") String yourTeacher);
    @Query("SELECT con FROM Student con  WHERE con.yourTeacher='default'and con.tariff not in ('default')")
    List<Student> findFreeStudent();

}
