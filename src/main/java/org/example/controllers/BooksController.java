package org.example.controllers;

import jakarta.validation.Valid;
import org.example.dao.BookDAO;
import org.example.dao.PersonDAO;
import org.example.models.Book;
import org.example.models.Person;
import org.example.util.BookValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookValidator bookValidator;
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    public BooksController(BookValidator bookValidator, BookDAO bookDAO, PersonDAO personDAO) {
        this.bookValidator = bookValidator;
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String infoBook(@ModelAttribute("person") Person person,
                             @PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.findById(id));
        if (bookDAO.getOwner(id) != null) {
            model.addAttribute("owner", bookDAO.getOwner(id));
        } else {
            model.addAttribute("people", personDAO.index());
        }
        return "books/info";
    }

    @PatchMapping("/{id}/assign")
    public String setOwner(@ModelAttribute("person") Person person,
                           @PathVariable int id) {
        bookDAO.setOwner(person.getPerson_id(), id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable int id) {
        bookDAO.deleteOwner(id);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
}
