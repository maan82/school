package com.mycompany.school.domain;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class LeaveType {

    /**
     */
    @NotNull
    @Size(min = 2)
    private String name;

    /**
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveType")
    private Set<StaffLeave> staffLeaves = new HashSet<StaffLeave>();


}
