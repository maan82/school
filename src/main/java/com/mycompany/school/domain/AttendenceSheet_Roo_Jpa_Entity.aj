// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.AttendenceSheet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect AttendenceSheet_Roo_Jpa_Entity {
    
    declare @type: AttendenceSheet: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long AttendenceSheet.id;
    
    @Version
    @Column(name = "version")
    private Integer AttendenceSheet.version;
    
    public Long AttendenceSheet.getId() {
        return this.id;
    }
    
    public void AttendenceSheet.setId(Long id) {
        this.id = id;
    }
    
    public Integer AttendenceSheet.getVersion() {
        return this.version;
    }
    
    public void AttendenceSheet.setVersion(Integer version) {
        this.version = version;
    }
    
}