// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.ClassSection;
import com.mycompany.school.domain.Student;
import java.util.Set;

privileged aspect ClassSection_Roo_JavaBean {
    
    public String ClassSection.getName() {
        return this.name;
    }
    
    public void ClassSection.setName(String name) {
        this.name = name;
    }
    
    public Set<Student> ClassSection.getStudents() {
        return this.students;
    }
    
    public void ClassSection.setStudents(Set<Student> students) {
        this.students = students;
    }
    
}
