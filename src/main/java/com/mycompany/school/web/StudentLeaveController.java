package com.mycompany.school.web;
import com.mycompany.school.domain.StudentLeave;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/studentleaves")
@Controller
@RooWebScaffold(path = "studentleaves", formBackingObject = StudentLeave.class)
public class StudentLeaveController {
}
