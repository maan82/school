package com.mycompany.school.web;
import com.mycompany.school.domain.*;
import com.mycompany.school.reference.AttendenceEnum;
import com.mycompany.school.web.form.AttendenceSearchForm;

import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

@RequestMapping("/studentattendences")
@Controller
@RooWebScaffold(path = "studentattendences", formBackingObject = Attendence.class)
public class StudentAttendenceController {

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Attendence attendence, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, attendence);
            return "studentattendences/create";
        }
        uiModel.asMap().clear();
        attendence.persist();
        return "redirect:/studentattendences/" + encodeUrlPathSegment(attendence.getId().toString(), httpServletRequest);
    }

	@RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Attendence());
        return "studentattendences/create";
    }

    @RequestMapping(params = {"form", "find=ByClassAndSection"}, method = RequestMethod.GET)
    public String createStudentAttendenceSearchFormForCreate(Model uiModel) {
        uiModel.addAttribute("finder", "ByClassAndSection");
        return getString(uiModel);
    }

    @RequestMapping(params = {"form", "find=ByClassAndSectionForList"}, method = RequestMethod.GET)
    public String createStudentAttendenceSearchFormForList(Model uiModel) {
        uiModel.addAttribute("finder", "ByClassAndSectionForList");
        return getString(uiModel);
    }

    private String getString(Model uiModel) {
        createAttendenceSearchByForm(uiModel);
        return "studentattendences/findAttendenceSheetByClassAndSection";
    }

    private void createAttendenceSearchByForm(Model uiModel) {
        List<StudentClass> studentClasses = StudentClass.findAllStudentClasses();
        List<ClassSection> classSections = ClassSection.findAllClassSections();
        AttendenceSearchForm attendenceSearchForm = new AttendenceSearchForm();
        attendenceSearchForm.setClassSections(classSections);
        attendenceSearchForm.setStudentClasses(studentClasses);
        uiModel.addAttribute("attendencesearchform", attendenceSearchForm);
    }

    @RequestMapping(params = "find=ByClassAndSection", method = RequestMethod.GET)
    public String createFormForClass(@RequestParam("studentClass") StudentClass studentClass, @RequestParam("classSection") ClassSection classSection, Model uiModel) {
        List<Student> persons = Student.findStudentsByStudentClassAndClassSection(studentClass, classSection);
        List<Attendence> studentAttendences = new ArrayList<Attendence>();
        for(Student person: persons) {
            Attendence attendence = new Attendence();
            attendence.setAttendence(AttendenceEnum.Present);
            attendence.setPerson(person);
            studentAttendences.add(attendence);
        }
        AttendenceSheet attendenceSheet = new AttendenceSheet();
        attendenceSheet.setAttendenceList(studentAttendences);
        uiModel.addAttribute("attendencesheet", attendenceSheet);
        uiModel.addAttribute("attendences", Arrays.asList(AttendenceEnum.values()));
        addDateTimeFormatPatterns(uiModel);
        return "studentattendences/create-sheet";
    }

    @RequestMapping(params = "find=ByClassAndSectionForList", method = RequestMethod.GET)
    public String listAttendenceByClassAndSection(AttendenceSearchForm attendenceSearchForm, BindingResult bindingResult, Model uiModel) {
        List<AttendenceSheet> allAttendenceSheets = AttendenceSheet.findAllAttendenceSheets(attendenceSearchForm.getStudentClass(), attendenceSearchForm.getClassSection());
        uiModel.addAttribute("attendencesheets", allAttendenceSheets);
        createAttendenceSearchByForm(uiModel);
        addDateTimeFormatPatterns(uiModel);

        List<StudentClass> studentClasses = StudentClass.findAllStudentClasses();
        List<ClassSection> classSections = ClassSection.findAllClassSections();
        attendenceSearchForm.setClassSections(classSections);
        attendenceSearchForm.setStudentClasses(studentClasses);
        uiModel.addAttribute("attendencesearchform", attendenceSearchForm);
        uiModel.addAttribute("finder", "ByClassAndSectionForList");
        return "studentattendences/list-sheets";
    }

    @RequestMapping(params = "attendence=student", method = RequestMethod.POST, produces = "text/html")
    public String createFormForClass(@Valid AttendenceSheet attendenceSheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        Date date = new Date();
        attendenceSheet.setAttendenceDate(date);
        Staff staff = attendenceSheet.getStaff();
        attendenceSheet.setStaff(Staff.findStaff(new Long(httpServletRequest.getUserPrincipal().getName())));
        for(Attendence studentAttendence: attendenceSheet.getAttendenceList()) {
            studentAttendence.setAttendenceDate(date);
            AbstractPerson abstractPerson = studentAttendence.getPerson(); 
            studentAttendence.setPerson(abstractPerson.findAbstractPerson(abstractPerson.getId()));
            studentAttendence.setAttendenceSheet(attendenceSheet);
        }
        attendenceSheet.persist();
        return "redirect:/studentattendences/";
    }


	@RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("attendencesheet", AttendenceSheet.findAttendenceSheet(id));
        uiModel.addAttribute("itemId", id);
        return "studentattendences/show";
    }

	@RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("attendencesheets", AttendenceSheet.findStudentAttendenceSheetEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Attendence.countAttendences() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("attendencesheets", AttendenceSheet.findStudentAttendenceSheetEntries(sortFieldName, sortOrder));
        }

        List<StudentClass> studentClasses = StudentClass.findAllStudentClasses();
        List<ClassSection> classSections = ClassSection.findAllClassSections();
        AttendenceSearchForm attendenceSearchForm = new AttendenceSearchForm();
        attendenceSearchForm.setClassSections(classSections);
        attendenceSearchForm.setStudentClasses(studentClasses);
        uiModel.addAttribute("attendencesearchform", attendenceSearchForm);
        uiModel.addAttribute("finder", "ByClassAndSectionForList");

        addDateTimeFormatPatterns(uiModel);
        return "studentattendences/list-sheets";
    }

	@RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Attendence attendence, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, attendence);
            return "studentattendences/update";
        }
        uiModel.asMap().clear();
        attendence.merge();
        return "redirect:/studentattendences/" + encodeUrlPathSegment(attendence.getId().toString(), httpServletRequest);
    }

	@RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Attendence.findAttendence(id));
        return "studentattendences/update";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Attendence attendence = Attendence.findAttendence(id);
        attendence.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/studentattendences";
    }


    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("studentAttendence_attendencedate_date_format", "yyyy-MM-dd hh:mm:ss");
    }

	void populateEditForm(Model uiModel, Attendence attendence) {
        uiModel.addAttribute("attendence", attendence);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("students", Student.findAllStudents());
        uiModel.addAttribute("attendenceenums", Arrays.asList(AttendenceEnum.values()));
    }

	String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
}
