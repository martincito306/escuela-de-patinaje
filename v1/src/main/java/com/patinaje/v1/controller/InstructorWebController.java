package com.patinaje.v1.controller;

import com.patinaje.v1.model.Instructor;
import com.patinaje.v1.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/admin/instructores")
public class InstructorWebController {
    
    @Autowired
    private InstructorService instructorService;
    
    @GetMapping
    public String listarInstructores(Model model) {
        model.addAttribute("instructores", instructorService.findAll());
        model.addAttribute("titulo", "Gestión de Instructores");
        return "admin/instructores";
    }
    
    @GetMapping("/nuevo")
    public String nuevoInstructor(Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("titulo", "Nuevo Instructor");
        return "admin/instructor-form";
    }
    
    @GetMapping("/editar/{id}")
    public String editarInstructor(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Instructor> instructor = instructorService.findById(id);
        if (instructor.isPresent()) {
            model.addAttribute("instructor", instructor.get());
            model.addAttribute("titulo", "Editar Instructor");
            return "admin/instructor-form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Instructor no encontrado");
            return "redirect:/admin/instructores";
        }
    }
    
    @PostMapping("/guardar")
    public String guardarInstructor(@ModelAttribute Instructor instructor, RedirectAttributes redirectAttributes) {
        try {
            if (instructor.getFechaRegistro() == null) {
                instructor.setFechaRegistro(LocalDateTime.now());
            }
            instructorService.save(instructor);
            redirectAttributes.addFlashAttribute("success", "Instructor guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el instructor: " + e.getMessage());
        }
        return "redirect:/admin/instructores";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarInstructor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            instructorService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Instructor eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el instructor: " + e.getMessage());
        }
        return "redirect:/admin/instructores";
    }
}
