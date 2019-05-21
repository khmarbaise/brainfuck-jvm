package it.mulders.brainfuckjvm.demoapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

@Controller
@Slf4j
public class IndexController {
    @GetMapping("/")
    public ModelAndView index() {
        final Map<String, Object> model = Collections.singletonMap("input", new ExecutionInput());
        return new ModelAndView("index", model);
    }
}