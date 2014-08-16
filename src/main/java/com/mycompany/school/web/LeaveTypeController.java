package com.mycompany.school.web;
import com.mycompany.school.domain.LeaveType;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/leavetypes")
@Controller
@RooWebScaffold(path = "leavetypes", formBackingObject = LeaveType.class)
public class LeaveTypeController {
}
