package english.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import english.api.dto.TeacherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@SqlResultSetMapping(
        name = "teacherMapping",
        classes = @ConstructorResult(
                targetClass = TeacherDto.class,
                columns = {
                        @ColumnResult(name = "id", type=Long.class),
                        @ColumnResult(name = "name", type=String.class),
                        @ColumnResult(name ="surname", type =String.class),
                        @ColumnResult(name ="login", type =String.class),
                        @ColumnResult(name ="password", type =String.class),
                        @ColumnResult(name ="link", type =String.class),
                        @ColumnResult(name ="education", type =String.class),

                }
        )
)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("teacher id")
    public Long id;

    @NotNull
    @Column(name = "name", length = 30)
    @ApiModelProperty("teacher name")
    public String name;

    @NotNull
    @Column(name = "surname", length = 30)
    @ApiModelProperty("teacher surname")
    public String surname;

    @NotNull
    @Column(name = "type", length = 30)
    @ApiModelProperty("teacher")
    public String type;


    @NotNull
    @Column(name = "login", length = 30)
    @ApiModelProperty("teacher login")
    public String login;


    @NotNull
    @Column(name = "password", length = 30)
    @ApiModelProperty("hash of password")
    @JsonIgnore
    public String password_hash;

    @NotNull
    @Column(name = "link", length = 30)
    @ApiModelProperty("teacher link")
    public String link;

    @NotNull
    @Column(name = "education", length = 30)
    @ApiModelProperty("education")
    public String education;
}