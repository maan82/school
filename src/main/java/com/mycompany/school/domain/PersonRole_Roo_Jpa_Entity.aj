// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.PersonRole;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect PersonRole_Roo_Jpa_Entity {
    
    declare @type: PersonRole: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long PersonRole.id;
    
    @Version
    @Column(name = "version")
    private Integer PersonRole.version;
    
    public Long PersonRole.getId() {
        return this.id;
    }
    
    public void PersonRole.setId(Long id) {
        this.id = id;
    }
    
    public Integer PersonRole.getVersion() {
        return this.version;
    }
    
    public void PersonRole.setVersion(Integer version) {
        this.version = version;
    }
    
}
