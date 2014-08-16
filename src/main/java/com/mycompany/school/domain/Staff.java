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

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Staff extends AbstractPerson {
    /**
     */
    @NotNull
    @Size(min = 2)
    private String qualification;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joiningDate;

    /**
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Designation designation;

}
