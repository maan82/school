// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.StudentClass;
import com.mycompany.school.domain.StudentClassDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect StudentClassDataOnDemand_Roo_DataOnDemand {
    
    declare @type: StudentClassDataOnDemand: @Component;
    
    private Random StudentClassDataOnDemand.rnd = new SecureRandom();
    
    private List<StudentClass> StudentClassDataOnDemand.data;
    
    public StudentClass StudentClassDataOnDemand.getNewTransientStudentClass(int index) {
        StudentClass obj = new StudentClass();
        setName(obj, index);
        return obj;
    }
    
    public void StudentClassDataOnDemand.setName(StudentClass obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public StudentClass StudentClassDataOnDemand.getSpecificStudentClass(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        StudentClass obj = data.get(index);
        Long id = obj.getId();
        return StudentClass.findStudentClass(id);
    }
    
    public StudentClass StudentClassDataOnDemand.getRandomStudentClass() {
        init();
        StudentClass obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return StudentClass.findStudentClass(id);
    }
    
    public boolean StudentClassDataOnDemand.modifyStudentClass(StudentClass obj) {
        return false;
    }
    
    public void StudentClassDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = StudentClass.findStudentClassEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'StudentClass' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<StudentClass>();
        for (int i = 0; i < 10; i++) {
            StudentClass obj = getNewTransientStudentClass(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
