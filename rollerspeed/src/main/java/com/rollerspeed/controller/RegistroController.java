package com.rollerspeed.controller;

import com.rollerspeed.model.Alumno;
import com.rollerspeed.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// Controlador para el registro de nuevos alumnos
@Controller
public class RegistroController {

    @Autowired
    private AlumnoService alumnoService;

    // Mostrar el formulario de registro
    @GetMapping("/registro")
    public String mostrarFormulario(Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("activePage", "registro");
        return "registro";
    }

    // Procesar el formulario cuando lo envían
    @PostMapping("/registro")
    public String procesarRegistro(@ModelAttribute Alumno alumno, 
                                   RedirectAttributes redirectAttributes) {
        try {
            // Verificamos que el correo no esté repetido
            if (alumnoService.correoExiste(alumno.getCorreo())) {
                redirectAttributes.addFlashAttribute("error", 
                    "Ya hay alguien registrado con ese correo. Usá otro por favor.");
                return "redirect:/registro";
            }

            // Guardamos el alumno
            Alumno alumnoGuardado = alumnoService.guardarAlumno(alumno);

            // Redirigimos a la página de confirmación con los datos
            redirectAttributes.addFlashAttribute("alumno", alumnoGuardado);
            redirectAttributes.addFlashAttribute("mensaje", 
                "¡Listo! Tu registro fue exitoso.");

            return "redirect:/confirmacion";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", 
                "Hubo un problema al guardar. Intentá de nuevo.");
            return "redirect:/registro";
        }
    }

    // Página de confirmación después del registro
    @GetMapping("/confirmacion")
    public String confirmacion(Model model) {
        model.addAttribute("activePage", "registro");
        return "confirmacion";
    }
}
