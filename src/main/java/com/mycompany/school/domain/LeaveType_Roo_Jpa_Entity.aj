// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.LeaveType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect LeaveType_Roo_Jpa_Entity {
    
    declare @type: LeaveType: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long LeaveType.id;
    
    @Version
    @Column(name = "version")
    private Integer LeaveType.version;
    
    public Long LeaveType.getId() {
        return this.id;
    }
    
    public void LeaveType.setId(Long id) {
        this.id = id;
    }
    
    public Integer LeaveType.getVersion() {
        return this.version;
    }
    
    public void LeaveType.setVersion(Integer version) {
        this.version = version;
    }
    
}
