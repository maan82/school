// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.domain;

import com.mycompany.school.domain.PersonRole;
import com.mycompany.school.domain.PersonRoleDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect PersonRoleDataOnDemand_Roo_DataOnDemand {
    
    declare @type: PersonRoleDataOnDemand: @Component;
    
    private Random PersonRoleDataOnDemand.rnd = new SecureRandom();
    
    private List<PersonRole> PersonRoleDataOnDemand.data;
    
    public PersonRole PersonRoleDataOnDemand.getNewTransientPersonRole(int index) {
        PersonRole obj = new PersonRole();
        setRoleName(obj, index);
        return obj;
    }
    
    public void PersonRoleDataOnDemand.setRoleName(PersonRole obj, int index) {
        String roleName = "roleName_" + index;
        obj.setRoleName(roleName);
    }
    
    public PersonRole PersonRoleDataOnDemand.getSpecificPersonRole(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        PersonRole obj = data.get(index);
        Long id = obj.getId();
        return PersonRole.findPersonRole(id);
    }
    
    public PersonRole PersonRoleDataOnDemand.getRandomPersonRole() {
        init();
        PersonRole obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return PersonRole.findPersonRole(id);
    }
    
    public boolean PersonRoleDataOnDemand.modifyPersonRole(PersonRole obj) {
        return false;
    }
    
    public void PersonRoleDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = PersonRole.findPersonRoleEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'PersonRole' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<PersonRole>();
        for (int i = 0; i < 10; i++) {
            PersonRole obj = getNewTransientPersonRole(i);
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