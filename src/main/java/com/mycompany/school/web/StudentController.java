package com.mycompany.school.web;
import com.mycompany.school.domain.ClassSection;
import com.mycompany.school.domain.PersonRole;
import com.mycompany.school.domain.Student;
import com.mycompany.school.domain.StudentClass;
import com.mycompany.school.reference.Gender;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;

@RequestMapping("/students")
@Controller
@RooWebScaffold(path = "students", formBackingObject = Student.class)
@RooWebFinder
public class StudentController {

    @RequestMapping(params = { "find=ByFirstNameLike", "form" }, method = RequestMethod.GET)
    public String findStudentsByFirstNameLikeForm(Model uiModel) {
        return "students/findStudentsByFirstNameLike";
    }

    @RequestMapping(params = "find=ByFirstNameLike", method = RequestMethod.GET)
    public String findStudentsByFirstNameLike(@RequestParam("firstName") String firstName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("students", Student.findStudentsByFirstNameLike(firstName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Student.countFindStudentsByFirstNameLike(firstName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findStudentsByFirstNameLike(firstName, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "students/list";
    }

    @RequestMapping(params = { "find=ByFirstNameOrLastNameLike", "form" }, method = RequestMethod.GET)
    public String findStudentsByFirstNameOrLastNameLikeForm(Model uiModel) {
        return "students/findStudentsByFirstNameOrLastNameLike";
    }

    @RequestMapping(params = "find=ByFirstNameOrLastNameLike", method = RequestMethod.GET)
    public String findStudentsByFirstNameOrLastNameLike(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("students", Student.findStudentsByFirstNameOrLastNameLike(firstName, lastName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Student.countFindStudentsByFirstNameOrLastNameLike(firstName, lastName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findStudentsByFirstNameOrLastNameLike(firstName, lastName, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "students/list";
    }

    @RequestMapping(params = { "find=ByGender", "form" }, method = RequestMethod.GET)
    public String findStudentsByGenderForm(Model uiModel) {
        uiModel.addAttribute("genders", java.util.Arrays.asList(Gender.class.getEnumConstants()));
        return "students/findStudentsByGender";
    }

    @RequestMapping(params = "find=ByGender", method = RequestMethod.GET)
    public String findStudentsByGender(@RequestParam("gender") Gender gender, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("students", Student.findStudentsByGender(gender, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Student.countFindStudentsByGender(gender) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findStudentsByGender(gender, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "students/list";
    }

    @RequestMapping(params = { "find=ByGenderOrFirstName", "form" }, method = RequestMethod.GET)
    public String findStudentsByGenderOrFirstNameForm(Model uiModel) {
        uiModel.addAttribute("genders", java.util.Arrays.asList(Gender.class.getEnumConstants()));
        return "students/findStudentsByGenderOrFirstName";
    }

    @RequestMapping(params = "find=ByGenderOrFirstName", method = RequestMethod.GET)
    public String findStudentsByGenderOrFirstName(@RequestParam("gender") Gender gender, @RequestParam("firstName") String firstName, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("students", Student.findStudentsByGenderOrFirstName(gender, firstName, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Student.countFindStudentsByGenderOrFirstName(gender, firstName) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findStudentsByGenderOrFirstName(gender, firstName, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        return "students/list";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Student student, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, student);
            return "students/create";
        }
        uiModel.asMap().clear();
        student.persist();
        return "redirect:/students/" + encodeUrlPathSegment(student.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Student());
        return "students/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("student", Student.findStudent(id));
        uiModel.addAttribute("itemId", id);
        return "students/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("students", Student.findStudentEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Student.countStudents() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findAllStudents(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        setUiModelForAllFind(uiModel);
        return "students/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Student student, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, student);
            return "students/update";
        }
        uiModel.asMap().clear();
        student.merge();
        return "redirect:/students/" + encodeUrlPathSegment(student.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Student.findStudent(id));
        return "students/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Student student = Student.findStudent(id);
        student.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/students";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("student_dateofbirth_date_format", "yyyy-MM-dd HH:mm:ss");
    }

    void populateEditForm(Model uiModel, Student student) {
        uiModel.addAttribute("student", student);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("classsections", ClassSection.findAllClassSections());
        uiModel.addAttribute("personroles", PersonRole.findAllPersonRoles());
        uiModel.addAttribute("studentclasses", StudentClass.findAllStudentClasses());
        uiModel.addAttribute("genders", Arrays.asList(Gender.values()));
        uiModel.addAttribute("personroles", PersonRole.findAllPersonRoles());
    }

    String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {
        }
        return pathSegment;
    }

    @RequestMapping(params = { "find=ByAllFind", "form" }, method = RequestMethod.GET)
    public String findStudentsByAllFindForm(Model uiModel) {
        setUiModelForAllFind(uiModel);
        return "students/findStudentsByAllFind";
    }

    private void setUiModelForAllFind(Model uiModel) {
        uiModel.addAttribute("genders", Arrays.asList(Gender.class.getEnumConstants()));
        uiModel.addAttribute("studentclasses", StudentClass.findAllStudentClasses());
        uiModel.addAttribute("classsections", ClassSection.findAllClassSections());
        uiModel.addAttribute("personroles", PersonRole.findAllPersonRoles());
        addDateTimeFormatPatterns(uiModel);
    }

    @RequestMapping(params = "find=ByAllFind", method = RequestMethod.GET)
    public String findStudentsByAllFind(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("fatherName") String fatherName, @RequestParam("motherName") String motherName, @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dateOfBirth, @RequestParam("rollNumber") Long rollNumber, @RequestParam("emailId") String emailId, @RequestParam("address") String address, @RequestParam("city") String city, @RequestParam("countryState") String countryState, @RequestParam("country") String country, @RequestParam("mobile") String mobile, @RequestParam("pincode") String pincode, @RequestParam("gender") Gender gender, @RequestParam(value = "enabled", required = false) boolean enabled, @RequestParam("studentClass") StudentClass studentClass, @RequestParam("classSection") ClassSection classSection, @RequestParam(value = "personRoles", required = false) Set<PersonRole> personRoles, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            personRoles = personRoles == null ? new HashSet<PersonRole>() : personRoles;
            uiModel.addAttribute("students", Student.findStudentsByAllFind(firstName, lastName, fatherName, motherName, dateOfBirth, rollNumber, emailId, address, city, countryState, country, mobile, pincode, gender, enabled, studentClass, classSection, personRoles, sortFieldName, sortOrder).setFirstResult(firstResult).setMaxResults(sizeNo).getResultList());
            float nrOfPages = (float) Student.countFindStudentsByAllFind(firstName, lastName, fatherName, motherName, dateOfBirth, rollNumber, emailId, address, city, countryState, country, mobile, pincode, gender, enabled, studentClass, classSection, personRoles) / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("students", Student.findStudentsByAllFind(firstName, lastName, fatherName, motherName, dateOfBirth, rollNumber, emailId, address, city, countryState, country, mobile, pincode, gender, enabled, studentClass, classSection, personRoles, sortFieldName, sortOrder).getResultList());
        }
        addDateTimeFormatPatterns(uiModel);
        setUiModelForAllFind(uiModel);
        return "students/list";
    }
}
