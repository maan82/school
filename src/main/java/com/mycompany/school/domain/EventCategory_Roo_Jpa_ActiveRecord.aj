// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.EventCategory;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

privileged aspect EventCategory_Roo_Jpa_ActiveRecord {
    
    @PersistenceContext
    transient EntityManager EventCategory.entityManager;
    
    public static final List<String> EventCategory.fieldNames4OrderClauseFilter = java.util.Arrays.asList("name");
    
    public static final EntityManager EventCategory.entityManager() {
        EntityManager em = new EventCategory().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }
    
    public static long EventCategory.countEventCategorys() {
        return entityManager().createQuery("SELECT COUNT(o) FROM EventCategory o", Long.class).getSingleResult();
    }
    
    public static List<EventCategory> EventCategory.findAllEventCategorys() {
        return entityManager().createQuery("SELECT o FROM EventCategory o", EventCategory.class).getResultList();
    }
    
    public static List<EventCategory> EventCategory.findAllEventCategorys(String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EventCategory o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EventCategory.class).getResultList();
    }
    
    public static EventCategory EventCategory.findEventCategory(Long id) {
        if (id == null) return null;
        return entityManager().find(EventCategory.class, id);
    }
    
    public static List<EventCategory> EventCategory.findEventCategoryEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM EventCategory o", EventCategory.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    public static List<EventCategory> EventCategory.findEventCategoryEntries(int firstResult, int maxResults, String sortFieldName, String sortOrder) {
        String jpaQuery = "SELECT o FROM EventCategory o";
        if (fieldNames4OrderClauseFilter.contains(sortFieldName)) {
            jpaQuery = jpaQuery + " ORDER BY " + sortFieldName;
            if ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder)) {
                jpaQuery = jpaQuery + " " + sortOrder;
            }
        }
        return entityManager().createQuery(jpaQuery, EventCategory.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public void EventCategory.persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }
    
    @Transactional
    public void EventCategory.remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            EventCategory attached = EventCategory.findEventCategory(this.id);
            this.entityManager.remove(attached);
        }
    }
    
    @Transactional
    public void EventCategory.flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }
    
    @Transactional
    public void EventCategory.clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }
    
    @Transactional
    public EventCategory EventCategory.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        EventCategory merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
}
