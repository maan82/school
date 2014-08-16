package com.mycompany.school.domain;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class AttendenceSheet {
    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date attendenceDate;

    /**
     */
    @NotNull
    @ManyToOne
    private Staff staff;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attendenceSheet")
    private List<Attendence> attendenceList = new ArrayList<Attendence>();


    public static List<AttendenceSheet> findAllAttendenceSheets(StudentClass studentClass, ClassSection classSection) {
        String jpaQuery = "SELECT o FROM AttendenceSheet o inner join o.attendenceList at  " +
            "where at.person.studentClass = :studentClass " +
            "and at.person.classSection = :classSection " +
            " GROUP BY at.person.studentClass, at.person.classSection";

        TypedQuery<AttendenceSheet> query = entityManager().createQuery(jpaQuery, AttendenceSheet.class);
        query.setParameter("studentClass", studentClass);
        query.setParameter("classSection", classSection);
        return query.getResultList();
    }

    public static List<AttendenceSheet> findStaffAttendenceSheetEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM AttendenceSheet o inner join  o.attendenceList at " +
            " where at.person.designation is not null " +
            " GROUP BY o.id";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, AttendenceSheet.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AttendenceSheet> findStaffAttendenceSheetEntries(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM AttendenceSheet o inner join  o.attendenceList at " +
            " where at.person.designation is not null " +
            " GROUP BY o.id";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, AttendenceSheet.class).getResultList();
    }

    public static List<AttendenceSheet> findStudentAttendenceSheetEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM AttendenceSheet o inner join  o.attendenceList at " +
            " where at.person.rollNumber is not null " +
            " GROUP BY o.id";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, AttendenceSheet.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<AttendenceSheet> findStudentAttendenceSheetEntries(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM AttendenceSheet o inner join  o.attendenceList at " +
            " where at.person.rollNumber is not null " +
            " GROUP BY o.id";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, AttendenceSheet.class).getResultList();
    }

}
