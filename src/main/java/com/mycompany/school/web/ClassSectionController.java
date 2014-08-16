package com.mycompany.school.web;
import com.mycompany.school.domain.ClassSection;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/classsections")
@Controller
@RooWebScaffold(path = "classsections", formBackingObject = ClassSection.class)
public class ClassSectionController {
}
