package za.ac.nwu.domain.persistence;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(schema = "project_schema",name = "users")
public class userPersistence
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer userID;

    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(targetEntity = imagePersistence.class, fetch = FetchType.LAZY, mappedBy = "userID")
    @JsonManagedReference
    private Set<imagePersistence> image;


    @OneToMany(targetEntity = shareimagePersistence.class, fetch = FetchType.LAZY, mappedBy = "userIDShared")
    @JsonManagedReference
    private Set<shareimagePersistence> sharedImageShared;

    @OneToMany(targetEntity = shareimagePersistence.class, fetch = FetchType.LAZY, mappedBy = "userIDSharer")
    @JsonManagedReference
    private Set<shareimagePersistence> sharedImageSharer;

    public userPersistence() {
    }

    public userPersistence(Integer userID) {
        this.userID = userID;
    }

    public userPersistence(Integer userID, String firstName, String lastName, String email, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        userPersistence user = (userPersistence) o;
        return Objects.equals(userID, user.userID) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstName, lastName, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
