// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.StudentLeave;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect StudentLeave_Roo_Jpa_Entity {
    
    declare @type: StudentLeave: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long StudentLeave.id;
    
    @Version
    @Column(name = "version")
    private Integer StudentLeave.version;
    
    public Long StudentLeave.getId() {
        return this.id;
    }
    
    public void StudentLeave.setId(Long id) {
        this.id = id;
    }
    
    public Integer StudentLeave.getVersion() {
        return this.version;
    }
    
    public void StudentLeave.setVersion(Integer version) {
        this.version = version;
    }
    
}
