package com.example.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Estudiante;
import com.example.entities.Facultad;
import com.example.services.EstudianteService;
import com.example.services.FacultadService;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private FacultadService facultadService;
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

        List<Facultad> facultades = facultadService.findAll();
               model.addAttribute("estudiante", new Estudiante());
               model.addAttribute("facultades", facultades);

        return "views/formularioAltaEstudiante";
    }

    @PostMapping("/altaEstudiante")
    /** Metodo que recibe los dayos procedente de los controles del formulario */
    public void altaEstudiante(){

    }
}
