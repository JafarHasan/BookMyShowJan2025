package com.BookMyShowJan2025.BookMyShow.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Setter
@Getter
@Table(name="user")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "User Name must not be null")
    @Size(min = 1, max = 10, message = "User Name must be between 1 and 10 characters")
    private String userName;

    @NotNull(message = "Age must not be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 100, message = "Age can't exceed 100")
    private Integer age;

    @NotBlank(message = "Email must not be null")
    @Email(message = "Email ID Should be a valid email address")
    private String email;

    @NotBlank(message = "Mobile no must not be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile no must be exactly 10 digits")
    private String mobileNo;

    public Integer getUserId(){
        return userId;
    }
    public void setUserId(Integer userId){
        this.userId=userId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }


}

/*
* Annotate each field with appropriate validation constraints as follows:
userId: Must not be null and must be a positive integer.
name: Must not be blank and should have a length between 2 and 50 characters.
age: Must not be null, must be between 18 and 100.
emailId: Must not be blank and should be a valid email address.
mobileNo: Must not be blank and must be exactly 10 digits.*/