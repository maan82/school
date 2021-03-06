package com.mycompany.school.web;
import com.mycompany.school.domain.Designation;
import com.mycompany.school.domain.PersonRole;
import com.mycompany.school.domain.Staff;
import com.mycompany.school.reference.Gender;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

@RequestMapping("/staffs")
@Controller
@RooWebScaffold(path = "staffs", formBackingObject = Staff.class)
public class StaffController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Staff staff, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, staff);
            return "staffs/create";
        }
        uiModel.asMap().clear();
        staff.persist();
        return "redirect:/staffs/" + encodeUrlPathSegment(staff.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new Staff());
        return "staffs/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("staff", Staff.findStaff(id));
        uiModel.addAttribute("itemId", id);
        return "staffs/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("staffs", Staff.findStaffEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) Staff.countStaffs() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("staffs", Staff.findAllStaffs(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "staffs/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid Staff staff, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, staff);
            return "staffs/update";
        }
        uiModel.asMap().clear();
        staff.merge();
        return "redirect:/staffs/" + encodeUrlPathSegment(staff.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Staff.findStaff(id));
        return "staffs/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Staff staff = Staff.findStaff(id);
        staff.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/staffs";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("staff_dateofbirth_date_format", "yyyy-MM-dd HH:mm:ss");
        uiModel.addAttribute("staff_joiningdate_date_format", "yyyy-MM-dd HH:mm:ss");
    }

    void populateEditForm(Model uiModel, Staff staff) {
        uiModel.addAttribute("staff", staff);
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("designations", Designation.findAllDesignations());
        uiModel.addAttribute("personroles", PersonRole.findAllPersonRoles());
        uiModel.addAttribute("genders", Arrays.asList(Gender.values()));
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
