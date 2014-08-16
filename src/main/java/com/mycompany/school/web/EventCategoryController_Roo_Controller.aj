// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.mycompany.school.web;

import com.mycompany.school.domain.EventCategory;
import com.mycompany.school.web.EventCategoryController;
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

privileged aspect EventCategoryController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String EventCategoryController.create(@Valid EventCategory eventCategory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, eventCategory);
            return "eventcategorys/create";
        }
        uiModel.asMap().clear();
        eventCategory.persist();
        return "redirect:/eventcategorys/" + encodeUrlPathSegment(eventCategory.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String EventCategoryController.createForm(Model uiModel) {
        populateEditForm(uiModel, new EventCategory());
        return "eventcategorys/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String EventCategoryController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("eventcategory", EventCategory.findEventCategory(id));
        uiModel.addAttribute("itemId", id);
        return "eventcategorys/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String EventCategoryController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("eventcategorys", EventCategory.findEventCategoryEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) EventCategory.countEventCategorys() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("eventcategorys", EventCategory.findAllEventCategorys(sortFieldName, sortOrder));
        }
        return "eventcategorys/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String EventCategoryController.update(@Valid EventCategory eventCategory, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, eventCategory);
            return "eventcategorys/update";
        }
        uiModel.asMap().clear();
        eventCategory.merge();
        return "redirect:/eventcategorys/" + encodeUrlPathSegment(eventCategory.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String EventCategoryController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, EventCategory.findEventCategory(id));
        return "eventcategorys/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String EventCategoryController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        EventCategory eventCategory = EventCategory.findEventCategory(id);
        eventCategory.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/eventcategorys";
    }
    
    void EventCategoryController.populateEditForm(Model uiModel, EventCategory eventCategory) {
        uiModel.addAttribute("eventCategory", eventCategory);
    }
    
    String EventCategoryController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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