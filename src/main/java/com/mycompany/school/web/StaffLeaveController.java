package com.mycompany.school.web;
import com.mycompany.school.domain.LeaveType;
import com.mycompany.school.domain.Staff;
import com.mycompany.school.domain.StaffLeave;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.format.DateTimeFormat;
import org.springframework.context.i18n.LocaleContextHolder;
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

@RequestMapping("/staffleaves")
@Controller
@RooWebScaffold(path = "staffleaves", formBackingObject = StaffLeave.class)
public class StaffLeaveController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid StaffLeave staffLeave, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, staffLeave);
            return "staffleaves/create";
        }
        uiModel.asMap().clear();
        staffLeave.persist();
        return "redirect:/staffleaves/" + encodeUrlPathSegment(staffLeave.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel, HttpServletRequest httpServletRequest) {
        StaffLeave staffLeave = new StaffLeave();
        staffLeave.setStaff(Staff.findStaff(new Long(httpServletRequest.getUserPrincipal().getName())));
        populateEditForm(uiModel, staffLeave);
        return "staffleaves/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("staffleave", StaffLeave.findStaffLeave(id));
        uiModel.addAttribute("itemId", id);
        return "staffleaves/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("staffleaves", StaffLeave.findStaffLeaveEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) StaffLeave.countStaffLeaves() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("staffleaves", StaffLeave.findAllStaffLeaves(sortFieldName, sortOrder));
        }
        addDateTimeFormatPatterns(uiModel);
        return "staffleaves/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid StaffLeave staffLeave, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, staffLeave);
            return "staffleaves/update";
        }
        uiModel.asMap().clear();
        staffLeave.merge();
        return "redirect:/staffleaves/" + encodeUrlPathSegment(staffLeave.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, StaffLeave.findStaffLeave(id));
        return "staffleaves/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        StaffLeave staffLeave = StaffLeave.findStaffLeave(id);
        staffLeave.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/staffleaves";
    }

    void addDateTimeFormatPatterns(Model uiModel) {
        uiModel.addAttribute("staffLeave_leavefrom_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
        uiModel.addAttribute("staffLeave_leaveto_date_format", DateTimeFormat.patternForStyle("M-", LocaleContextHolder.getLocale()));
    }

    void populateEditForm(Model uiModel, StaffLeave staffLeave) {
        uiModel.addAttribute("staffLeave", staffLeave);
        uiModel.addAttribute("stafflist", Staff.findAllStaffs());
        addDateTimeFormatPatterns(uiModel);
        uiModel.addAttribute("leavetypes", LeaveType.findAllLeaveTypes());
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
