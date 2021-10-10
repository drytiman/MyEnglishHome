package english.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import english.api.dto.StudentDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;


@SqlResultSetMapping(
        name = "studentMapping",
        classes = @ConstructorResult(
                targetClass = StudentDto.class,
                columns = {
                        @ColumnResult(name = "id", type=Long.class),
                        @ColumnResult(name = "username", type=String.class),
                        @ColumnResult(name ="surname", type =String.class),
                        @ColumnResult(name ="login", type =String.class),
                        @ColumnResult(name ="password", type =String.class),
                        @ColumnResult(name ="link", type =String.class),
                }
        )
)

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @ApiModelProperty("user id")
    public Long id;

    @NotNull
    @Column(name = "name", length = 30)
    @ApiModelProperty("student name")
    public String name;

    @NotNull
    @Column(name = "surname", length = 30)
    @ApiModelProperty("student surname")
    public String surname;

    @NotNull
    @Column(name = "login", length = 30)
    @ApiModelProperty("student login")
    public String login;


    @NotNull
    @Column(name = "password", length = 30)
    @ApiModelProperty("hash of password")
    @JsonIgnore
    public String password;

    @NotNull
    @Column(name = "type", length = 30)
    @ApiModelProperty("type")
    @JsonIgnore
    public String type;

    @NotNull
    @Column(name = "link", length = 30)
    @ApiModelProperty("student link")
    public String link;

    @NotNull
    @Column(name = "tariff", length = 30)
    @ApiModelProperty("tariff")
    public String  tariff;

    @NotNull
    @Column(name = "yourTeacher", length = 30)
    @ApiModelProperty("yourTeacher")
    public String yourTeacher;

}