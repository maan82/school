// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.LeaveType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect LeaveType_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager LeaveType.entityManager;
    
    public static final List<String> LeaveType.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name", "staffLeaves");
    
    public static final EntityManager LeaveType.entityManager() {
        EntityManager em = new LeaveType().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long LeaveType.countLeaveTypes() {
        return entityManager().createQuery("SELECT COUNT(o) FROM LeaveType o", Long.class).getSingleResult();
    }
    
    public static List<LeaveType> LeaveType.findAllLeaveTypes() {
        return entityManager().createQuery("SELECT o FROM LeaveType o", LeaveType.class).getResultList();
    }
    
    public static List<LeaveType> LeaveType.findAllLeaveTypes(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM LeaveType o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, LeaveType.class).getResultList();
    }
    
    public static LeaveType LeaveType.findLeaveType(Long id) {
        if (id == null) return null;
        return entityManager().find(LeaveType.class, id);
    }
    
    public static List<LeaveType> LeaveType.findLeaveTypeEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM LeaveType o", LeaveType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<LeaveType> LeaveType.findLeaveTypeEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM LeaveType o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, LeaveType.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void LeaveType.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void LeaveType.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            LeaveType attached = LeaveType.findLeaveType(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void LeaveType.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void LeaveType.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public LeaveType LeaveType.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        LeaveType merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}