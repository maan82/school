package com.mycompany.school.domain;
import com.mycompany.school.reference.AttendenceEnum;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Configurable
@Entity
public class Attendence {
    /**
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date attendenceDate;

    /**
     */
    @Size(max = 254)
    private String remarks;

    /**
     */
    @NotNull
    @Enumerated
    private AttendenceEnum attendence;

    /**
     */
    @ManyToOne
    private AbstractPerson person;

    /**
     */
    @ManyToOne
    private AttendenceSheet attendenceSheet;


	@PersistenceContext
    transient EntityManager entityManager;

	public static final List<String> fieldNames4OrderClauseFilter = java.util.Arrays.asList("attendenceDate", "remarks", "attendence", "person", "attendenceSheet");

	public static final EntityManager entityManager() {
        EntityManager em = new Attendence().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countAttendences() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Attendence o", Long.class).getSingleResult();
    }

	public static List<Attendence> findAllAttendences() {
        return entityManager().createQuery("SELECT o FROM Attendence o", Attendence.class).getResultList();
    }

	public static List<Attendence> findAllAttendences(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Attendence o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Attendence.class).getResultList();
    }

	public static Attendence findAttendence(Long id) {
        if (id == null) return null;
        return entityManager().find(Attendence.class, id);
    }

	public static List<Attendence> findAttendenceEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Attendence o", Attendence.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	public static List<Attendence> findAttendenceEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Attendence o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Attendence.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Attendence> findStudentAttendenceEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Attendence o where o.person.rollNumber is not null ";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Attendence> query = entityManager().createQuery(jpaQuery, Attendence.class);
        return entityManager().createQuery(jpaQuery, Attendence.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Attendence> findAllStudentAttendences(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Attendence o where o.person.rollNumber is not null ";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }

        TypedQuery<Attendence> query = entityManager().createQuery(jpaQuery, Attendence.class);
        return query.getResultList();
    }


    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Attendence attached = Attendence.findAttendence(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public Attendence merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Attendence merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Date getAttendenceDate() {
        return this.attendenceDate;
    }

	public void setAttendenceDate(Date attendenceDate) {
        this.attendenceDate = attendenceDate;
    }

	public String getRemarks() {
        return this.remarks;
    }

	public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public AttendenceEnum getAttendence() {
        return this.attendence;
    }

	public void setAttendence(AttendenceEnum attendence) {
        this.attendence = attendence;
    }

	public AbstractPerson getPerson() {
        return this.person;
    }

	public void setPerson(AbstractPerson person) {
        this.person = person;
    }

	public AttendenceSheet getAttendenceSheet() {
        return this.attendenceSheet;
    }

	public void setAttendenceSheet(AttendenceSheet attendenceSheet) {
        this.attendenceSheet = attendenceSheet;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
    private Integer version;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }

    public String getStudentClassName() {
        return ((Student)this.person).getStudentClass().getName();
    }

    public String getClassSectionName() {
        return ((Student)this.person).getClassSection().getName();
    }

    public Long getRollNumber() {
        return ((Student)this.person).getRollNumber();
    }

}
