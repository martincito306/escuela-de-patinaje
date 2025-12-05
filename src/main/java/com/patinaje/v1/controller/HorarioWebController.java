package com.patinaje.v1.controller;

import com.patinaje.v1.model.Horario;
import com.patinaje.v1.model.Programa;
import com.patinaje.v1.service.HorarioService;
import com.patinaje.v1.service.ProgramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/horarios")
public class HorarioWebController {
    
    @Autowired
    private HorarioService horarioService;
    
    @Autowired
    private ProgramaService programaService;
    
    @GetMapping
    public String listarHorarios(Model model) {
        model.addAttribute("horarios", horarioService.findAll());
        model.addAttribute("titulo", "Gestión de Horarios");
        return "admin/horarios";
    }
    
    @GetMapping("/nuevo")
    public String nuevoHorario(Model model) {
        model.addAttribute("horario", new Horario());
        model.addAttribute("programas", programaService.obtenerActivos());
        model.addAttribute("titulo", "Nuevo Horario");
        return "admin/horario-form";
    }
    
    @GetMapping("/editar/{id}")
    public String editarHorario(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Horario> horario = horarioService.findById(id);
        if (horario.isPresent()) {
            model.addAttribute("horario", horario.get());
            model.addAttribute("programas", programaService.obtenerActivos());
            model.addAttribute("titulo", "Editar Horario");
            return "admin/horario-form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Horario no encontrado");
            return "redirect:/admin/horarios";
        }
    }
    
    @PostMapping("/guardar")
    public String guardarHorario(@ModelAttribute Horario horario, 
                                  @RequestParam(required = false) Long programaId,
                                  RedirectAttributes redirectAttributes) {
        try {
            if (programaId != null) {
                Optional<Programa> programa = programaService.obtenerPorId(programaId);
                programa.ifPresent(horario::setPrograma);
            }
            horarioService.save(horario);
            redirectAttributes.addFlashAttribute("success", "Horario guardado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar el horario: " + e.getMessage());
        }
        return "redirect:/admin/horarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarHorario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            horarioService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Horario eliminado exitosamente");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al eliminar el horario: " + e.getMessage());
        }
        return "redirect:/admin/horarios";
    }
}
