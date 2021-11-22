package za.ac.nwu.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import za.ac.nwu.domain.persistence.userPersistence;
import java.util.Objects;

@ApiModel(value = "User", description = "User DTO")
public class userDTO
{
    private Integer userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public userDTO() {
    }

    public userDTO(Integer userID, String firstName, String lastName, String email, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public userDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public userDTO(userPersistence user) {
        this.setUserID(user.getUserID());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setEmail(user.getEmail());
        this.setPassword(user.getPassword());
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    @ApiModelProperty(position = 1,
            value = "User firstName",
            name = "firstName",
            notes = "Identifies the name of the user",
            dataType = "java.lang.String",
            example = "Shaylin",
            required = true)

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ApiModelProperty(position = 2,
            value = "User lastName",
            name = "lastName",
            notes = "Identifies the surname of the user",
            dataType = "java.lang.String",
            example = "Johnson",
            required = true)

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ApiModelProperty(position = 3,
            value = "User email",
            name = "email",
            notes = "Identifies the email of the user",
            dataType = "java.lang.String",
            example = "shaylinjohnson1008@gmail.com",
            required = true)

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ApiModelProperty(position = 4,
            value = "User password",
            name = "password",
            notes = "Identifies the password of the user",
            dataType = "java.lang.String",
            example = "shayj1008",
            required = true)

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public userPersistence getUser(){
        return new userPersistence(getUserID(), getFirstName(), getLastName(), getEmail(), getPassword());
    }

    @JsonIgnore
    public userDTO getUserDTO(){
        return new userDTO(
                getUserID(), getFirstName(), getLastName(), getEmail(), getPassword());
    }

    @JsonIgnore
    public userPersistence getUID(){
        return new userPersistence(getUserID());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        userDTO userDTO = (userDTO) o;
        return Objects.equals(userID, userDTO.userID) && Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) && Objects.equals(email, userDTO.email) && Objects.equals(password, userDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, firstName, lastName, email, password);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userID=" + userID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
