package com.mycompany.school.web;
import com.mycompany.school.domain.EventCategory;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/eventcategorys")
@Controller
@RooWebScaffold(path = "eventcategorys", formBackingObject = EventCategory.class)
public class EventCategoryController {
}
