package com.labuda.gdlunch.dto.users;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * User DTO class
 */
public class UserDTO {

    /**
     * Database id
     */
    private Long id;

    /**
     * Username
     */
    @NotEmpty
    private String username;

    /**
     * Password stored in bcrypt
     */
    @NotEmpty
    private String password;

    /**
     * User email
     */
    @NotEmpty
    @Email
    private String email;

    /**
     * Users name
     */
    @NotEmpty
    private String name;

    /**
     * Users surname
     */
    @NotEmpty
    private String surname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserDTO userDTO = (UserDTO) o;

        if (getUsername() != null ? !getUsername().equals(userDTO.getUsername()) : userDTO.getUsername() != null) {
            return false;
        }
        return getEmail() != null ? getEmail().equals(userDTO.getEmail()) : userDTO.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
