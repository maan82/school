// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.StudentClass;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StudentClass_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager StudentClass.entityManager;
    
    public static final List<String> StudentClass.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name", "students");
    
    public static final EntityManager StudentClass.entityManager() {
        EntityManager em = new StudentClass().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long StudentClass.countStudentClasses() {
        return entityManager().createQuery("SELECT COUNT(o) FROM StudentClass o", Long.class).getSingleResult();
    }
    
    public static List<StudentClass> StudentClass.findAllStudentClasses() {
        return entityManager().createQuery("SELECT o FROM StudentClass o", StudentClass.class).getResultList();
    }
    
    public static List<StudentClass> StudentClass.findAllStudentClasses(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM StudentClass o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, StudentClass.class).getResultList();
    }
    
    public static StudentClass StudentClass.findStudentClass(Long id) {
        if (id == null) return null;
        return entityManager().find(StudentClass.class, id);
    }
    
    public static List<StudentClass> StudentClass.findStudentClassEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM StudentClass o", StudentClass.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<StudentClass> StudentClass.findStudentClassEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM StudentClass o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, StudentClass.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void StudentClass.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void StudentClass.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            StudentClass attached = StudentClass.findStudentClass(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void StudentClass.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void StudentClass.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public StudentClass StudentClass.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        StudentClass merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}