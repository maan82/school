package com.mycompany.school.web;
import com.mycompany.school.domain.Student;
import com.mycompany.school.domain.StudentClass;
import java.io.UnsupportedEncodingException;
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

@RequestMapping("/studentclasses")
@Controller
@RooWebScaffold(path = "studentclasses", formBackingObject = StudentClass.class)
public class StudentClassController {

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid StudentClass studentClass, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, studentClass);
            return "studentclasses/create";
        }
        uiModel.asMap().clear();
        studentClass.persist();
        return "redirect:/studentclasses/" + encodeUrlPathSegment(studentClass.getId().toString(), httpServletRequest);
    }

    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new StudentClass());
        return "studentclasses/create";
    }

    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("studentclass", StudentClass.findStudentClass(id));
        uiModel.addAttribute("itemId", id);
        return "studentclasses/show";
    }

    @RequestMapping(produces = "text/html")
    public String list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, @RequestParam(value = "sortFieldName", required = false) String sortFieldName, @RequestParam(value = "sortOrder", required = false) String sortOrder, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("studentclasses", StudentClass.findStudentClassEntries(firstResult, sizeNo, sortFieldName, sortOrder));
            float nrOfPages = (float) StudentClass.countStudentClasses() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("studentclasses", StudentClass.findAllStudentClasses(sortFieldName, sortOrder));
        }
        return "studentclasses/list";
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String update(@Valid StudentClass studentClass, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, studentClass);
            return "studentclasses/update";
        }
        uiModel.asMap().clear();
        studentClass.merge();
        return "redirect:/studentclasses/" + encodeUrlPathSegment(studentClass.getId().toString(), httpServletRequest);
    }

    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, StudentClass.findStudentClass(id));
        return "studentclasses/update";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        StudentClass studentClass = StudentClass.findStudentClass(id);
        studentClass.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/studentclasses";
    }

    void populateEditForm(Model uiModel, StudentClass studentClass) {
        uiModel.addAttribute("studentClass", studentClass);
        uiModel.addAttribute("students", Student.findAllStudents());
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
