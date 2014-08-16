package com.mycompany.school.domain;
import org.hibernate.annotations.Cascade;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Event {
    /**
     */
    @ManyToOne(cascade = CascadeType.ALL)
    private EventCategory eventCategory;

    /**
     */
    @ManyToOne
    private EventType eventType;

    /**
     */
    @NotNull
    @Size(min = 2)
    private String description;

    /**
     */
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date eventDate;

}
