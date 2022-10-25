package com.ntg.spring.controllers;

import com.ntg.spring.dao.PersonDao;
import com.ntg.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Nurbolot Gulamidinov
 */
@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDao personDaoImpl;

    @Autowired
    public PeopleController(PersonDao personDaoImpl) {
        this.personDaoImpl = personDaoImpl;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDaoImpl.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("person", personDaoImpl.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        personDaoImpl.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("person", personDaoImpl.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") long id) {
        if (bindingResult.hasErrors())
            return "people/edit";

        personDaoImpl.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        personDaoImpl.delete(id);
        return "redirect:/people";
    }
}
