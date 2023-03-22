package com.example.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.Estudiante;
import com.example.entities.Telefono;
import com.example.services.EstudianteService;
import com.example.services.TelefonoService;

@Controller
@RequestMapping("/")
public class MainController {

    private static final  Logger LOG = Logger.getLogger("MainController");

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private TelefonoService telefonoService;

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
    // RedirectView redirectView = new RedirectView();
    // redirectView.setUrl("/listar");
    // return redirectView;
    // }
    @PostMapping("/altaEstudiante")
    /** Metodo que recibe los dayos procedente de los controles del formulario */
    public String altaEstudiante(@ModelAttribute Estudiante estudiante,
    @RequestParam(name = "numerosTelefonos") String telefonosRecibidos) { /** va a devolver una vista si todo ha ido correctamente */
      
      LOG.info("Los telefonos recibidos son: " + telefonosRecibidos);
      
      List<String> listadoNumerosTelefonos = null; // Como ha sido declarada aqui, ya no necesito hacerlo despues

      if(telefonosRecibidos != null) {
        String[] arrayTelefonos = telefonosRecibidos.split(";"); /* Aqui separo por punto y coma */
       
        listadoNumerosTelefonos = Arrays.asList(arrayTelefonos); // Aqui lo convierto automaticamente en la lista de telefonos
  
      }
 
      estudianteService.save(estudiante);
      
      if(listadoNumerosTelefonos != null){
        listadoNumerosTelefonos.stream().forEach(n -> {  // n hace referencia al numero que va pasando, uno a uno
            Telefono telefonoObject = Telefono.builder().numero(n).estudiante(estudiante)
            .build();

            telefonoService.save(telefonoObject);
        });
      }
      return "redirect:/listar";
    }
}
