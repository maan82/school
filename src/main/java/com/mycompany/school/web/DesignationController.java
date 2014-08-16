package com.mycompany.school.web;
import com.mycompany.school.domain.Designation;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/designations")
@Controller
@RooWebScaffold(path = "designations", formBackingObject = Designation.class)
public class DesignationController {
}
