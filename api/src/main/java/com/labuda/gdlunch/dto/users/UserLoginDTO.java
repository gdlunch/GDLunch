package com.labuda.gdlunch.dto.users;

import javax.validation.constraints.NotEmpty;

/**
 * User login DTO
 */
public class UserLoginDTO {

    /**
     * Username
     */
    @NotEmpty
    private String username;

    /**
     * Password
     */
    @NotEmpty
    private String password;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserLoginDTO that = (UserLoginDTO) o;

        if (getUsername() != null ? !getUsername().equals(that.getUsername()) : that.getUsername() != null) {
            return false;
        }
        return getPassword() != null ? getPassword().equals(that.getPassword()) : that.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = getUsername() != null ? getUsername().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
