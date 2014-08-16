package com.mycompany.school.domain;
import com.mycompany.school.reference.Gender;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AbstractPerson {

     /**
     */
    @NotNull
    @Size(min = 2)
    private String firstName;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String lastName;

    /**
     */
    @NotNull
    @Enumerated
    private Gender gender;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String fatherName;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String motherName;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfBirth;

    /**
     */
    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    private String emailId;

    /**
     */
    @Size(min = 5, max = 500)
    private String address;

    /**
     */
    @Size(min = 1)
    private String city;

    /**
     */
    @Size(min = 1)
    private String countryState;

    /**
     */
    @Size(min = 1)
    private String country;

    /**
     */
    @Size(min = 1, max = 20)
    private String mobile;

    /**
     */
    @Size(min = 1, max = 20)
    private String pincode;

    /**
     */
    @NotNull
    private boolean enabled;

    /**
     */
    @NotNull
    private String passwordSHA;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<PersonRole> personRoles = new HashSet<PersonRole>();

    public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("firstName", "lastName");

}
