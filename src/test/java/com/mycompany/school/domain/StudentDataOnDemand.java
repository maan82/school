package com.mycompany.school.domain;
import com.mycompany.school.reference.Gender;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;

@Component
@RooDataOnDemand(entity = Student.class)
public class StudentDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<Student> data;

	@Autowired
    private ClassSectionDataOnDemand classSectionDataOnDemand;

	@Autowired
    private StudentClassDataOnDemand studentClassDataOnDemand;

	public Student getNewTransientStudent(int index) {
        Student obj = new Student();
        setAddress(obj, index);
        setCity(obj, index);
        setCountry(obj, index);
        setCountryState(obj, index);
        setDateOfBirth(obj, index);
        setEmailId(obj, index);
        setEnabled(obj, index);
        setFatherName(obj, index);
        setFirstName(obj, index);
        setGender(obj, index);
        setLastName(obj, index);
        setMobile(obj, index);
        setMotherName(obj, index);
        setPasswordSHA(obj, index);
        setPincode(obj, index);
        setRollNumber(obj, index);
        return obj;
    }

	public void setAddress(Student obj, int index) {
        String address = "address_" + index;
        if (address.length() > 500) {
            address = address.substring(0, 500);
        }
        obj.setAddress(address);
    }

	public void setCity(Student obj, int index) {
        String city = "city_" + index;
        obj.setCity(city);
    }

	public void setCountry(Student obj, int index) {
        String country = "country_" + index;
        obj.setCountry(country);
    }

	public void setCountryState(Student obj, int index) {
        String countryState = "countryState_" + index;
        obj.setCountryState(countryState);
    }

	public void setDateOfBirth(Student obj, int index) {
        Date dateOfBirth = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDateOfBirth(dateOfBirth);
    }

	public void setEmailId(Student obj, int index) {
        String emailId = "foo" + index + "@bar.com";
        obj.setEmailId(emailId);
    }

	public void setEnabled(Student obj, int index) {
        Boolean enabled = true;
        obj.setEnabled(enabled);
    }

	public void setFatherName(Student obj, int index) {
        String fatherName = "fatherName_" + index;
        obj.setFatherName(fatherName);
    }

	public void setFirstName(Student obj, int index) {
        String firstName = "firstName_" + index;
        obj.setFirstName(firstName);
    }

	public void setGender(Student obj, int index) {
        Gender gender = Gender.Male;
        obj.setGender(gender);
    }

	public void setLastName(Student obj, int index) {
        String lastName = "lastName_" + index;
        obj.setLastName(lastName);
    }

	public void setMobile(Student obj, int index) {
        String mobile = "mobile_" + index;
        if (mobile.length() > 20) {
            mobile = mobile.substring(0, 20);
        }
        obj.setMobile(mobile);
    }

	public void setMotherName(Student obj, int index) {
        String motherName = "motherName_" + index;
        obj.setMotherName(motherName);
    }

	public void setPasswordSHA(Student obj, int index) {
        String passwordSHA = "passwordSHA_" + index;
        obj.setPasswordSHA(passwordSHA);
    }

	public void setPincode(Student obj, int index) {
        String pincode = "pincode_" + index;
        if (pincode.length() > 20) {
            pincode = pincode.substring(0, 20);
        }
        obj.setPincode(pincode);
    }

	public void setRollNumber(Student obj, int index) {
        Long rollNumber = new Integer(index).longValue();
        obj.setRollNumber(rollNumber);
    }

	public Student getSpecificStudent(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Student obj = data.get(index);
        Long id = obj.getId();
        return Student.findStudent(id);
    }

	public Student getRandomStudent() {
        init();
        Student obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Student.findStudent(id);
    }

	public boolean modifyStudent(Student obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = Student.findStudentEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Student' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student obj = getNewTransientStudent(i);
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
