package com.example.validatesonginformation.controller;

import com.example.validatesonginformation.model.Song;
import com.example.validatesonginformation.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(("/songs"))
public class SongController {
    @Autowired
    private ISongService iSongService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("songs", iSongService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("songs", new Song());
        return "create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("songs") Song song, Model model, BindingResult bindingResult) {
        new Song().validate(song, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "create";
        } else {
            model.addAttribute("songs", song);
            iSongService.save(song);
            return "list";
        }
    }
    @GetMapping("/edit/{id}")
    public ModelAndView formEdit(@PathVariable Long id) {
        Optional<Song> customerOptional = iSongService.findById(id);
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("songs",customerOptional.get());
            return modelAndView;
    }
    @PostMapping("/edit")
    public String edit (@Valid @ModelAttribute("songs")Song song,BindingResult bindingResult) {
        song.validate(song,bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "edit";
        }else {
            iSongService.save(song);
            return "redirect:/songs";
        }

    }
}
