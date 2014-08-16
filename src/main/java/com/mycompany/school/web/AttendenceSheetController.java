package com.mycompany.school.web;
import com.mycompany.school.domain.AttendenceSheet;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/attendencesheets")
@Controller
@RooWebScaffold(path = "attendencesheets", formBackingObject = AttendenceSheet.class)
public class AttendenceSheetController {
}
