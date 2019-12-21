package lv.ozo.bikeApp.controller;

import lv.ozo.bikeApp.entity.Bike;
import lv.ozo.bikeApp.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/bikes/")
public class BikeController {
    private  final BikeRepository bikeRepository;

    @Autowired
    public BikeController(BikeRepository bikeRepository){
        this.bikeRepository = bikeRepository;
    }

//    @GetMapping("/test")
//    public String test (Model model){
//        return "test";
//    }

    @GetMapping("bikeinfo")
    public String showBikeInfoRegistratorForm(Bike bike){
        return "addBike";
    }

    @GetMapping("list")
    public String showAllBikes(Model model){
        model.addAttribute("bikes", bikeRepository.findAll());
        return "bikeList";
    }

    @PostMapping("add")
    public String addBike(@Valid Bike bike, BindingResult result, Model model){
        if (result.hasErrors()){
            return "addBike";
        }
        bikeRepository.save(bike);
        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public  String showUpdateBike(@PathVariable("id") long id,  Model model){
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bike Id:" + id));
        model.addAttribute("bike", bike);
        return "updateBikeInfo";
}

@PostMapping("update/{id}")
public String updateBike(@PathVariable("id") long id, @Valid Bike bike, BindingResult result, Model model){
        if (result.hasErrors()){
            bike.setId(id);
            return "updateBikeInfo";
        }
        bikeRepository.save(bike);
        model.addAttribute("bikes", bikeRepository.findAll());
        return "bikeList";
}

    @GetMapping("delete/{id}")
    public String deleteBike(@PathVariable("id") long id, Model model){
        Bike bike = bikeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid bike Id:"+id));
        bikeRepository.delete(bike);
        model.addAttribute("bikes", bikeRepository.findAll());
        return "bikeList";
    }
}
