// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.web;

import com.mycompany.school.domain.LeaveType;
import com.mycompany.school.domain.StaffLeave;
import com.mycompany.school.web.LeaveTypeController;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect LeaveTypeController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String LeaveTypeController.create(@Valid LeaveType leaveType, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, leaveType);
            return "leavetypes/create";
        }
        uiModel.asMap().clear();
        leaveType.persist();
        return "redirect:/leavetypes/" + encodeUrlPathSegment(leaveType.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String LeaveTypeController.createForm(Model uiModel) {
        populateEditForm(uiModel, new LeaveType());
        return "leavetypes/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String LeaveTypeController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("leavetype", LeaveType.findLeaveType(id));
        uiModel.addAttribute("itemId", id);
        return "leavetypes/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String LeaveTypeController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("leavetypes", LeaveType.findLeaveTypeEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) LeaveType.countLeaveTypes() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("leavetypes", LeaveType.findAllLeaveTypes(sortFieldName, sortOrder));
        }
        return "leavetypes/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String LeaveTypeController.update(@Valid LeaveType leaveType, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, leaveType);
            return "leavetypes/update";
        }
        uiModel.asMap().clear();
        leaveType.merge();
        return "redirect:/leavetypes/" + encodeUrlPathSegment(leaveType.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String LeaveTypeController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, LeaveType.findLeaveType(id));
        return "leavetypes/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String LeaveTypeController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        LeaveType leaveType = LeaveType.findLeaveType(id);
        leaveType.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/leavetypes";
    }
    
    void LeaveTypeController.populateEditForm(Model uiModel, LeaveType leaveType) {
        uiModel.addAttribute("leaveType", leaveType);
        uiModel.addAttribute("staffleaves", StaffLeave.findAllStaffLeaves());
    }
    
    String LeaveTypeController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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