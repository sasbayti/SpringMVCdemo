package com.example.controllers;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.entities.Estudiante;
import com.example.services.EstudianteService;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private EstudianteService estudianteService;

    /**
     * El metodo siguiente devuelve un listado de estudiantes
     */
    @GetMapping("/listar")
    public ModelAndView listar() {

        List<Estudiante> estudiantes = estudianteService.findAll();

        ModelAndView mav = new ModelAndView("views/listarEstudiantes");
        mav.addObject("estudiantes", estudiantes);
        
        return mav;
    }

    /**
     * Muestra el formulario de alta de estudiante
     */
    @GetMapping("/frmAltaEstudiante")
    public String formularioAltaEstudiante(Model model) {

        model.addAttribute("estudiante", new Estudiante());

        return "views/formularioAltaEstudiante";
    }

   // @PostMapping("/altaEstudiante")
    // /** Metodo que recibe los dayos procedente de los controles del formulario */
    // public RedirectView altaEstudiante(){ 
    //     RedirectView redirectView = new RedirectView();
    //     redirectView.setUrl("/listar");
    // return redirectView;
    // }
    @PostMapping("/altaEstudiante")
    /** Metodo que recibe los dayos procedente de los controles del formulario*/
    public String altaEstudiante(@ModelAttribute Estudiante estudiante){  /* va a devolver una vista si todo ha ido correctaemnte */
     estudianteService.save(estudiante);
    return "redirect:/listar"; 
    }
}
