// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.LeaveType;
import com.mycompany.school.domain.StaffLeave;
import java.util.Set;

privileged aspect LeaveType_Roo_JavaBean {
    
    public String LeaveType.getName() {
        return this.name;
    }
    
    public void LeaveType.setName(String name) {
        this.name = name;
    }
    
    public Set<StaffLeave> LeaveType.getStaffLeaves() {
        return this.staffLeaves;
    }
    
    public void LeaveType.setStaffLeaves(Set<StaffLeave> staffLeaves) {
        this.staffLeaves = staffLeaves;
    }
    
}