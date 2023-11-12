package com.mycompany.controllers;

import com.mycompany.models.Bus;
import com.mycompany.services.BusService;
import com.mycompany.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BusController {
    @Autowired private BusService bs;
    @GetMapping("/buses")
    public String showBusesList(Model model) {
        List<Bus> listBus = bs.listAll();
        model.addAttribute("listBus", listBus);
        return "busPage";
    }
    @GetMapping("/buses/new")
    public void newBus(Model model) {
        model.addAttribute("bus", new Bus());
        model.addAttribute("pageTitle", "Add New Bus");
    }

    @PostMapping("/buses/save")
    public void saveBus(Bus bus, RedirectAttributes ra) {
        bs.saveBus(bus);
        ra.addFlashAttribute("message", "The user has been saved successfully.");
    }
    @GetMapping("/buses/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Bus b = bs.get(id);
            model.addAttribute("bus", b);
            model.addAttribute("pageTitle", "Edit Bus (ID: " + id + ")");

            return "busEditPage";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/buses";
        }
    }
}
