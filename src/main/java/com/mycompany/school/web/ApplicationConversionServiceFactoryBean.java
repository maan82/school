package com.mycompany.school.web;

import com.mycompany.school.domain.AbstractPerson;
import com.mycompany.school.domain.Attendence;
import com.mycompany.school.domain.Staff;
import com.mycompany.school.domain.Student;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.roo.addon.web.mvc.controller.converter.RooConversionService;

/**
 * A central place to register application converters and formatters. 
 */
@RooConversionService
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

	@Override
	protected void installFormatters(FormatterRegistry registry) {
		super.installFormatters(registry);
        reg();
		// Register application converters and formatters
	}

    public Converter<Attendence, String> getAttendenceToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<Attendence, String>() {
            public String convert(Attendence attendence) {
                return new StringBuilder().append(attendence.getAttendence()).toString();
            }
        };
    }

    public void reg() {
        getObject().addConverter(getAttendenceToStringConverter());
    }



	public Converter<Staff, String> getStaffToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.mycompany.school.domain.Staff, java.lang.String>() {
            public String convert(Staff staff) {
                return new StringBuilder().append(staff.getFirstName()).append(' ').append(staff.getLastName()).append(' ').toString();
            }
        };
    }

	public Converter<Student, String> getStudentToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.mycompany.school.domain.Student, java.lang.String>() {
            public String convert(Student student) {
                return new StringBuilder().append(student.getFirstName()).append(' ').append(student.getLastName()).append(' ').toString();
            }
        };
    }

	public Converter<AbstractPerson, String> getAbstractPersonToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<com.mycompany.school.domain.AbstractPerson, java.lang.String>() {
            public String convert(AbstractPerson abstractPerson) {
                return new StringBuilder().append(abstractPerson.getFirstName()).append(' ').append(abstractPerson.getLastName()).append(' ').toString();
            }
        };
    }
}
