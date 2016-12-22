package be.ucll.forecast.raspberry.domain;

/**
 * Created by tompl on 12/20/2016.
 */
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;


@Entity
@Table(name = "t_user")
@NamedQueries({
        @NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
        @NamedQuery(name = User.FIND_BY_LOGIN_PASSWORD, query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name = User.COUNT_ALL, query = "SELECT COUNT(u) FROM User u")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    // ======================================
    // =             Constants              =
    // ======================================

    public static final String FIND_ALL = "User.findAll";
    public static final String COUNT_ALL = "User.countAll";
    public static final String FIND_BY_LOGIN_PASSWORD = "User.findByLoginAndPassword";


    @Column(length = 10, nullable = false)
    private String username;
    @Column(length = 256, nullable = false)
    private String password;

    public User(String id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Id
    @Column(length = 10, nullable = false)
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}