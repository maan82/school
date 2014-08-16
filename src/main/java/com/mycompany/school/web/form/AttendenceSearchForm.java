package com.mycompany.school.web.form;

import com.mycompany.school.domain.ClassSection;
import com.mycompany.school.domain.StudentClass;

import java.util.ArrayList;
import java.util.List;

public class AttendenceSearchForm {
    private Integer id;
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    private StudentClass studentClass;
    private ClassSection classSection;
    private List<StudentClass> studentClasses = new ArrayList<StudentClass>();
    private List<ClassSection> classSections = new ArrayList<ClassSection>();


    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public ClassSection getClassSection() {
        return classSection;
    }

    public void setClassSection(ClassSection classSection) {

        this.classSection = classSection;
    }

    public List<StudentClass> getStudentClasses() {
		return studentClasses;
	}
	public void setStudentClasses(List<StudentClass> studentClasses) {
		this.studentClasses = studentClasses;
	}
	public List<ClassSection> getClassSections() {
		return classSections;
	}
    public void setClassSections(List<ClassSection> classSections) {
		this.classSections = classSections;
	}
    
    
}
