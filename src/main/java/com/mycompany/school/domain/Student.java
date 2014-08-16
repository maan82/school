package com.mycompany.school.domain;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.Pattern;
import com.mycompany.school.reference.Gender;
import javax.persistence.ManyToOne;

@Entity
@Configurable
@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Student extends AbstractPerson {

    /**
     */
    @NotNull
    private Long rollNumber;

    /**
     */
    @ManyToOne
    private StudentClass studentClass;

    /**
     */
    @ManyToOne
    private ClassSection classSection;

    public static Long countFindStudentsByFirstNameLike(String firstName) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        EntityManager em = Student.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Student AS o WHERE LOWER(o.firstName) LIKE LOWER(:firstName)", Long.class);
        q.setParameter("firstName", firstName);
        return ((Long) q.getSingleResult());
    }

    public static Long countFindStudentsByFirstNameOrLastNameLike(String firstName, String lastName) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        EntityManager em = Student.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Student AS o WHERE o.firstName = :firstName OR LOWER(o.lastName) LIKE LOWER(:lastName)", Long.class);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        return ((Long) q.getSingleResult());
    }

    public static Long countFindStudentsByGender(Gender gender) {
        if (gender == null) throw new IllegalArgumentException("The gender argument is required");
        EntityManager em = Student.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Student AS o WHERE o.gender = :gender", Long.class);
        q.setParameter("gender", gender);
        return ((Long) q.getSingleResult());
    }

    public static Long countFindStudentsByGenderOrFirstName(Gender gender, String firstName) {
        if (gender == null) throw new IllegalArgumentException("The gender argument is required");
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        EntityManager em = Student.entityManager();
        TypedQuery q = em.createQuery("SELECT COUNT(o) FROM Student AS o WHERE o.gender = :gender OR o.firstName = :firstName", Long.class);
        q.setParameter("gender", gender);
        q.setParameter("firstName", firstName);
        return ((Long) q.getSingleResult());
    }

    public static TypedQuery<Student> findStudentsByFirstNameLike(String firstName) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        EntityManager em = Student.entityManager();
        TypedQuery<Student> q = em.createQuery("SELECT o FROM Student AS o WHERE LOWER(o.firstName) LIKE LOWER(:firstName)", Student.class);
        q.setParameter("firstName", firstName);
        return q;
    }

    public static TypedQuery<Student> findStudentsByFirstNameLike(String firstName, String sortFieldName, String sortOrder) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        firstName = firstName.replace('*', '%');
        if (firstName.charAt(0) != '%') {
            firstName = "%" + firstName;
        }
        if (firstName.charAt(firstName.length() - 1) != '%') {
            firstName = firstName + "%";
        }
        EntityManager em = Student.entityManager();
        String jpaQuery = "SELECT o FROM Student AS o WHERE LOWER(o.firstName) LIKE LOWER(:firstName)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Student> q = em.createQuery(jpaQuery, Student.class);
        q.setParameter("firstName", firstName);
        return q;
    }

    public static TypedQuery<Student> findStudentsByFirstNameOrLastNameLike(String firstName, String lastName) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        EntityManager em = Student.entityManager();
        TypedQuery<Student> q = em.createQuery("SELECT o FROM Student AS o WHERE o.firstName = :firstName OR LOWER(o.lastName) LIKE LOWER(:lastName)", Student.class);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        return q;
    }

    public static TypedQuery<Student> findStudentsByFirstNameOrLastNameLike(String firstName, String lastName, String sortFieldName, String sortOrder) {
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        if (lastName == null || lastName.length() == 0) throw new IllegalArgumentException("The lastName argument is required");
        lastName = lastName.replace('*', '%');
        if (lastName.charAt(0) != '%') {
            lastName = "%" + lastName;
        }
        if (lastName.charAt(lastName.length() - 1) != '%') {
            lastName = lastName + "%";
        }
        EntityManager em = Student.entityManager();
        String jpaQuery = "SELECT o FROM Student AS o WHERE o.firstName = :firstName OR LOWER(o.lastName) LIKE LOWER(:lastName)";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Student> q = em.createQuery(jpaQuery, Student.class);
        q.setParameter("firstName", firstName);
        q.setParameter("lastName", lastName);
        return q;
    }

    public static TypedQuery<Student> findStudentsByGender(Gender gender) {
        if (gender == null) throw new IllegalArgumentException("The gender argument is required");
        EntityManager em = Student.entityManager();
        TypedQuery<Student> q = em.createQuery("SELECT o FROM Student AS o WHERE o.gender = :gender", Student.class);
        q.setParameter("gender", gender);
        return q;
    }

    public static TypedQuery<Student> findStudentsByGender(Gender gender, String sortFieldName, String sortOrder) {
        if (gender == null) throw new IllegalArgumentException("The gender argument is required");
        EntityManager em = Student.entityManager();
        String jpaQuery = "SELECT o FROM Student AS o WHERE o.gender = :gender";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Student> q = em.createQuery(jpaQuery, Student.class);
        q.setParameter("gender", gender);
        return q;
    }

    public static TypedQuery<Student> findStudentsByGenderOrFirstName(Gender gender, String firstName) {
        if (gender == null) throw new IllegalArgumentException("The gender argument is required");
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        EntityManager em = Student.entityManager();
        TypedQuery<Student> q = em.createQuery("SELECT o FROM Student AS o WHERE o.gender = :gender OR o.firstName = :firstName", Student.class);
        q.setParameter("gender", gender);
        q.setParameter("firstName", firstName);
        return q;
    }

    public static TypedQuery<Student> findStudentsByGenderOrFirstName(Gender gender, String firstName, String sortFieldName, String sortOrder) {
        if (gender == null) throw new IllegalArgumentException("The gender argument is required");
        if (firstName == null || firstName.length() == 0) throw new IllegalArgumentException("The firstName argument is required");
        EntityManager em = Student.entityManager();
        String jpaQuery = "SELECT o FROM Student AS o WHERE o.gender = :gender OR o.firstName = :firstName";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        TypedQuery<Student> q = em.createQuery(jpaQuery, Student.class);
        q.setParameter("gender", gender);
        q.setParameter("firstName", firstName);
        return q;
    }

    public Long getRollNumber() {
        return this.rollNumber;
    }

    public void setRollNumber(Long rollNumber) {
        this.rollNumber = rollNumber;
    }

    public StudentClass getStudentClass() {
        return this.studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public ClassSection getClassSection() {
        return this.classSection;
    }

    public void setClassSection(ClassSection classSection) {
        this.classSection = classSection;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public static final List<String> fieldNames4OrderClauseFilter = Lists.newArrayList(Iterables.concat(AbstractPerson.fieldNames4OrderClauseFilter, java.util.Arrays.asList("rollNumber", "studentClass", "classSection")));// java.util.Arrays.asList("rollNumber", "studentClass", "classSection");

    public static long countStudents() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Student o", Long.class).getSingleResult();
    }

    public static List<Student> findAllStudents() {
        return entityManager().createQuery("SELECT o FROM Student o", Student.class).getResultList();
    }

    public static List<Student> findAllStudents(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Student o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }

        return entityManager().createQuery(jpaQuery, Student.class).getResultList();
    }

    public static Student findStudent(Long id) {
        if (id == null) return null;
        return entityManager().find(Student.class, id);
    }

    public static List<Student> findStudentEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Student o", Student.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    public static List<Student> findStudentEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM Student o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, Student.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public Student merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Student merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

    public static List<Student> findStudentsByStudentClassAndClassSection(StudentClass studentClass, ClassSection classSection) {
        EntityManager em = Student.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Student AS o WHERE 1=1");
        addAndCriteriaIfNotNullOrEmpty(queryBuilder, "studentClass", studentClass);
        addAndCriteriaIfNotNullOrEmpty(queryBuilder, "classSection", classSection);

        TypedQuery q = em.createQuery(queryBuilder.toString(), Student.class);
        setParameterIfNotNullOrEmpty("studentClass", studentClass, q);
        setParameterIfNotNullOrEmpty("classSection", classSection, q);
        return q.getResultList();
    }

    public static Long countFindStudentsByAllFind(String firstName, String lastName, String fatherName, String motherName, Date dateOfBirth, Long rollNumber, String emailId, String address, String city, String countryState, String country, String mobile, String pincode, Gender gender, boolean enabled, StudentClass studentClass, ClassSection classSection, Set<PersonRole> personRoles) {
        EntityManager em = Student.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT COUNT(o) FROM Student AS o WHERE 1!=1");
        addCriteriaIfNotNullOrEmpty(queryBuilder, "firstName", firstName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "lastName", lastName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "fatherName", fatherName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "motherName", motherName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "dateOfBirth", dateOfBirth);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "rollNumber", rollNumber);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "emailId", emailId);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "address", address);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "city", city);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "countryState", countryState);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "country", country);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "mobile", mobile);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "pincode", pincode);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "gender", gender);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "enabled", enabled);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "studentClass", studentClass);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "classSection", classSection);

        for (int i = 0; i < personRoles.size(); i++) {
            if (i > 0) queryBuilder.append(" AND");
            queryBuilder.append(" :personRoles_item").append(i).append(" MEMBER OF o.personRoles");
        }
        TypedQuery q = em.createQuery(queryBuilder.toString(), Long.class);
        setParameterIfNotNullOrEmpty("firstName", firstName, q);
        setParameterIfNotNullOrEmpty("lastName", lastName, q);
        setParameterIfNotNullOrEmpty("fatherName", fatherName, q);
        setParameterIfNotNullOrEmpty("motherName", motherName, q);
        setParameterIfNotNullOrEmpty("dateOfBirth", dateOfBirth, q);
        setParameterIfNotNullOrEmpty("rollNumber", rollNumber, q);
        setParameterIfNotNullOrEmpty("emailId", emailId, q);
        setParameterIfNotNullOrEmpty("address", address, q);
        setParameterIfNotNullOrEmpty("city", city, q);
        setParameterIfNotNullOrEmpty("countryState", countryState, q);
        setParameterIfNotNullOrEmpty("country", country, q);
        setParameterIfNotNullOrEmpty("mobile", mobile, q);
        setParameterIfNotNullOrEmpty("pincode", pincode, q);
        setParameterIfNotNullOrEmpty("gender", gender, q);
        setParameterIfNotNullOrEmpty("enabled", enabled, q);
        setParameterIfNotNullOrEmpty("studentClass", studentClass, q);
        setParameterIfNotNullOrEmpty("classSection", classSection, q);
        int personRolesIndex = 0;
        for (PersonRole _personrole: personRoles) {
            q.setParameter("personRoles_item" + personRolesIndex++, _personrole);
        }
        return ((Long) q.getSingleResult());
    }

	public static TypedQuery<Student> findStudentsByAllFind(String firstName, String lastName, String fatherName, String motherName, Date dateOfBirth, Long rollNumber, String emailId, String address, String city, String countryState, String country, String mobile, String pincode, Gender gender, boolean enabled, StudentClass studentClass, ClassSection classSection, Set<PersonRole> personRoles) {
        EntityManager em = Student.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Student AS o WHERE  1!=1 ");
        addCriteriaIfNotNullOrEmpty(queryBuilder, "firstName", firstName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "lastName", lastName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "fatherName", fatherName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "motherName", motherName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "dateOfBirth", dateOfBirth);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "rollNumber", rollNumber);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "emailId", emailId);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "address", address);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "city", city);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "countryState", countryState);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "country", country);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "mobile", mobile);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "pincode", pincode);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "gender", gender);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "enabled", enabled);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "studentClass", studentClass);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "classSection", classSection);

        for (int i = 0; i < personRoles.size(); i++) {
            if (i > 0) queryBuilder.append(" OR");
            queryBuilder.append(" :personRoles_item").append(i).append(" MEMBER OF o.personRoles");
        }
        TypedQuery<Student> q = em.createQuery(queryBuilder.toString(), Student.class);
        setParameterIfNotNullOrEmpty("firstName", firstName, q);
        setParameterIfNotNullOrEmpty("lastName", lastName, q);
        setParameterIfNotNullOrEmpty("fatherName", fatherName, q);
        setParameterIfNotNullOrEmpty("motherName", motherName, q);
        setParameterIfNotNullOrEmpty("dateOfBirth", dateOfBirth, q);
        setParameterIfNotNullOrEmpty("rollNumber", rollNumber, q);
        setParameterIfNotNullOrEmpty("emailId", emailId, q);
        setParameterIfNotNullOrEmpty("address", address, q);
        setParameterIfNotNullOrEmpty("city", city, q);
        setParameterIfNotNullOrEmpty("countryState", countryState, q);
        setParameterIfNotNullOrEmpty("country", country, q);
        setParameterIfNotNullOrEmpty("mobile", mobile, q);
        setParameterIfNotNullOrEmpty("pincode", pincode, q);
        setParameterIfNotNullOrEmpty("gender", gender, q);
        setParameterIfNotNullOrEmpty("enabled", enabled, q);
        setParameterIfNotNullOrEmpty("studentClass", studentClass, q);
        setParameterIfNotNullOrEmpty("classSection", classSection, q);
        int personRolesIndex = 0;
        for (PersonRole _personrole: personRoles) {
            q.setParameter("personRoles_item" + personRolesIndex++, _personrole);
        }
        return q;
    }

	public static TypedQuery<Student> findStudentsByAllFind(String firstName, String lastName, String fatherName, String motherName, Date dateOfBirth, Long rollNumber, String emailId, String address, String city, String countryState, String country, String mobile, String pincode, Gender gender, Boolean enabled, StudentClass studentClass, ClassSection classSection, Set<PersonRole> personRoles, String sortFieldName, String sortOrder) {
        EntityManager em = Student.entityManager();
        StringBuilder queryBuilder = new StringBuilder("SELECT o FROM Student AS o WHERE 1!=1 ");
        addCriteriaIfNotNullOrEmpty(queryBuilder, "firstName", firstName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "lastName", lastName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "fatherName", fatherName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "motherName", motherName);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "dateOfBirth", dateOfBirth);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "rollNumber", rollNumber);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "emailId", emailId);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "address", address);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "city", city);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "countryState", countryState);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "country", country);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "mobile", mobile);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "pincode", pincode);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "gender", gender);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "enabled", enabled);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "studentClass", studentClass);
        addCriteriaIfNotNullOrEmpty(queryBuilder, "classSection", classSection);

        for (int i = 0; i < personRoles.size(); i++) {
            if (i > 0) queryBuilder.append(" OR");
            queryBuilder.append(" :personRoles_item").append(i).append(" MEMBER OF o.personRoles");
        }
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            queryBuilder.append(" ORDER BY " + sortFieldName);
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                queryBuilder.append(" " + sortOrder);
            }
        }
        TypedQuery<Student> q = em.createQuery(queryBuilder.toString(), Student.class);
        setParameterIfNotNullOrEmpty("firstName", firstName, q);
        setParameterIfNotNullOrEmpty("lastName", lastName, q);
        setParameterIfNotNullOrEmpty("fatherName", fatherName, q);
        setParameterIfNotNullOrEmpty("motherName", motherName, q);
        setParameterIfNotNullOrEmpty("dateOfBirth", dateOfBirth, q);
        setParameterIfNotNullOrEmpty("rollNumber", rollNumber, q);
        setParameterIfNotNullOrEmpty("emailId", emailId, q);
        setParameterIfNotNullOrEmpty("address", address, q);
        setParameterIfNotNullOrEmpty("city", city, q);
        setParameterIfNotNullOrEmpty("countryState", countryState, q);
        setParameterIfNotNullOrEmpty("country", country, q);
        setParameterIfNotNullOrEmpty("mobile", mobile, q);
        setParameterIfNotNullOrEmpty("pincode", pincode, q);
        setParameterIfNotNullOrEmpty("gender", gender, q);
        setParameterIfNotNullOrEmpty("enabled", enabled, q);
        setParameterIfNotNullOrEmpty("studentClass", studentClass, q);
        setParameterIfNotNullOrEmpty("classSection", classSection, q);
        int personRolesIndex = 0;
        for (PersonRole _personrole: personRoles) {
            setParameterIfNotNullOrEmpty("personRoles_item" + personRolesIndex++, _personrole, q);
        }
        return q;
    }

    private static StringBuilder addCriteriaIfNotNullOrEmpty(StringBuilder queryBuilder, String criteria, Object criteriaObject) {
        if (canAddObject(criteriaObject)) {
            queryBuilder.append(" OR o." + criteria + " = :" + criteria + " ");
        }
        return queryBuilder;
    }

    private static StringBuilder addAndCriteriaIfNotNullOrEmpty(StringBuilder queryBuilder, String criteria, Object criteriaObject) {
        if (canAddObject(criteriaObject)) {
            queryBuilder.append(" AND o." + criteria + " = :" + criteria + " ");
        }
        return queryBuilder;
    }

    private static void setParameterIfNotNullOrEmpty(String name, Object param, TypedQuery<Student> q) {
        if (canAddObject(param)) {
            q.setParameter(name, param);
        }
    }

    private static boolean canAddObject(Object criteriaObject) {
        return
            criteriaObject != null
            &&
            (criteriaObject instanceof String && !((String) criteriaObject).isEmpty()
             || !(criteriaObject instanceof String));

    }

}
