// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.StudentClass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect StudentClass_Roo_Jpa_Entity {
    
    declare @type: StudentClass: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long StudentClass.id;
    
    @Version
    @Column(name = "version")
    private Integer StudentClass.version;
    
    public Long StudentClass.getId() {
        return this.id;
    }
    
    public void StudentClass.setId(Long id) {
        this.id = id;
    }
    
    public Integer StudentClass.getVersion() {
        return this.version;
    }
    
    public void StudentClass.setVersion(Integer version) {
        this.version = version;
    }
    
}