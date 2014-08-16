package com.mycompany.school.web;
import com.mycompany.school.domain.*;
import com.mycompany.school.reference.AttendenceEnum;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.mycompany.school.web.form.AttendenceSearchForm;
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

@RequestMapping("/staffattendences")
@Controller
@RooWebScaffold(path = "staffattendences", formBackingObject = Attendence.class)
public class StaffAttendenceController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Attendence staffAttendence, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, staffAttendence);
            return "staffattendences/create";
        }
        uiModel.asMap().clear();
        staffAttendence.persist();
        return "redirect:/staffattendences/" + encodeUrlPathSegment(staffAttendence.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Attendence());
        return "staffattendences/create";
    }

    @RequestMapping(params = "find=ByStaff", method = RequestMethod.GET)
    public String createFormForStaff(Model uiModel) {
        List<Staff> persons = Staff.findAllStaffs();
        List<Attendence> attendences = new ArrayList<Attendence>();
        for(Staff person: persons) {
            Attendence attendence = new Attendence();
            attendence.setAttendence(AttendenceEnum.Present);
            attendence.setPerson(person);
            attendences.add(attendence);
        }
        AttendenceSheet attendenceSheet = new AttendenceSheet();
        attendenceSheet.setAttendenceList(attendences);
        uiModel.addAttribute("attendencesheet", attendenceSheet);
        uiModel.addAttribute("attendences", Arrays.asList(AttendenceEnum.values()));
        addDateTimeFormatPatterns(uiModel);
        return "staffattendences/create-sheet";
    }

    @RequestMapping(params = "attendence=staff", method = RequestMethod.POST, produces = "text/html")
    public String createFormForClass(@Valid AttendenceSheet attendenceSheet, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        Date date = new Date();
        attendenceSheet.setAttendenceDate(date);
        Staff staff = attendenceSheet.getStaff();
        attendenceSheet.setStaff(Staff.findStaff(new Long(httpServletRequest.getUserPrincipal().getName())));
        for(Attendence attendence: attendenceSheet.getAttendenceList()) {
        	attendence.setAttendenceDate(date);
            AbstractPerson abstractPerson = attendence.getPerson();
            attendence.setPerson(abstractPerson.findAbstractPerson(abstractPerson.getId()));
            attendence.setAttendenceSheet(attendenceSheet);
        }
        attendenceSheet.persist();
        return "redirect:/staffattendences/";
    }


    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("attendencesheet", AttendenceSheet.findAttendenceSheet(id));
        uiModel.addAttribute("itemId", id);
        return "staffattendences/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("attendencesheets", AttendenceSheet.findStaffAttendenceSheetEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Attendence.countAttendences() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("attendencesheets", AttendenceSheet.findStaffAttendenceSheetEntries(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "staffattendences/list-sheets";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Attendence staffAttendence, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, staffAttendence);
            return "staffattendences/update";
        }
        uiModel.asMap().clear();
        staffAttendence.merge();
        return "redirect:/staffattendences/" + encodeUrlPathSegment(staffAttendence.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Attendence.findAttendence(id));
        return "staffattendences/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Attendence staffAttendence = Attendence.findAttendence(id);
        staffAttendence.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/staffattendences";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("staffAttendence_attendencedate_date_format", "yyyy-MM-dd hh:mm:ss");
    }

    void populateEditForm(Model uiModel, Attendence staffAttendence) {
        uiModel.addAttribute("staffAttendence", staffAttendence);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("staffs", Staff.findAllStaffs());
        uiModel.addAttribute("attendences", Arrays.asList(AttendenceEnum.values()));
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
}
