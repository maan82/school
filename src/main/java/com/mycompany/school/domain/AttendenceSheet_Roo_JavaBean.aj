// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.Attendence;
import com.mycompany.school.domain.AttendenceSheet;
import com.mycompany.school.domain.Staff;
import java.util.Date;
import java.util.List;

privileged aspect AttendenceSheet_Roo_JavaBean {
    
    public Date AttendenceSheet.getAttendenceDate() {
        return this.attendenceDate;
    }
    
    public void AttendenceSheet.setAttendenceDate(Date attendenceDate) {
        this.attendenceDate = attendenceDate;
    }
    
    public Staff AttendenceSheet.getStaff() {
        return this.staff;
    }
    
    public void AttendenceSheet.setStaff(Staff staff) {
        this.staff = staff;
    }
    
    public List<Attendence> AttendenceSheet.getAttendenceList() {
        return this.attendenceList;
    }
    
    public void AttendenceSheet.setAttendenceList(List<Attendence> attendenceList) {
        this.attendenceList = attendenceList;
    }
    
}
