package org.example.controllers;

import jakarta.validation.Valid;
import org.example.models.Person;
import org.example.util.PeopleValidator;
import org.example.dao.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleValidator peopleValidator;
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public PeopleController(PeopleValidator peopleValidator, PersonDAO perssonDAO, BookDAO bookDAO) {
        this.peopleValidator = peopleValidator;
        this.personDAO = perssonDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String infoPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.findById(id));
        model.addAttribute("books", bookDAO.findByOwner(id));
        return "people/info";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        peopleValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/new";
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
