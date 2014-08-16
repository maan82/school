package com.mycompany.school.web;
import com.mycompany.school.domain.AbstractPerson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/abstractpeople")
@Controller
@RooWebScaffold(path = "abstractpeople", formBackingObject = AbstractPerson.class)
public class AbstractPersonController {
}
