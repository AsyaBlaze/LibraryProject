package org.example.controllers;

import jakarta.validation.Valid;
import org.example.models.Book;
import org.example.models.Person;
import org.example.services.BooksService;
import org.example.services.PeopleService;
import org.example.util.PeopleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleValidator peopleValidator;
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public PeopleController(PeopleValidator peopleValidator, PeopleService peopleService, BooksService booksService) {
        this.peopleValidator = peopleValidator;
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String infoPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        Set<Book> stBooks = booksService.findByOwner((Person) model.getAttribute("person"));
        model.addAttribute("books", stBooks);
        for (Book book:stBooks) {
            System.out.println(book.isOverdue());
        }
        return "people/info";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findById(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        peopleValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors())
            return "people/edit";
        peopleService.update(id, person);
        return "redirect:/people";
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
        peopleService.save(person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        peopleService.deleteById(id);
        return "redirect:/people";
    }
}
