package com.mycompany.services;

import com.mycompany.models.Bus;

import com.mycompany.repo.BusRepository;
import com.mycompany.user.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BusService {
    @Autowired private BusRepository bus;
    public void saveBus(Bus b){
        bus.save(b);
    }
    public List<Bus> listAll() {
        return (List<Bus>) bus.findAll();
    }
    public Bus get(Integer id) throws UserNotFoundException {
        Optional<Bus> result = bus.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any Bus with ID " + id);
    }
//    public String editBus(Integer id, Model model) {
//        Bus b = bus.findById(id).orElse(null);
//        if (b != null) {
//            List<String> stopList = Arrays.asList(b.getStops().split(","));
//            model.addAttribute("bus", b);
//            model.addAttribute("stopList", stopList);
//        }
//        return "bus/edit";
//    }
//        public String updateBus(Integer id,Bus updatedBus) {
//        Bus b = bus.findById(id).orElse(null);
//        if (b != null) {
//            b.setName(updatedBus.getName());
//            b.setType(updatedBus.getType());
//            // Update other fields as needed
//            b.setStops(updatedBus.getStops());
//            bus.save(b);
//        }
//        return "redirect:/buses";
//    }
}
