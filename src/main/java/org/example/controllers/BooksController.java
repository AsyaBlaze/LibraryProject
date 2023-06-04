package org.example.controllers;

import jakarta.validation.Valid;
import org.example.models.Book;
import org.example.models.Person;
import org.example.services.BooksService;
import org.example.services.PeopleService;
import org.example.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookValidator bookValidator;
    private final PeopleService peopleService;
    private final BooksService booksService;

    @Autowired
    public BooksController(BookValidator bookValidator, PeopleService peopleService, BooksService booksService) {
        this.bookValidator = bookValidator;
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping()
    public String index(@RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name = "books_per_page", required = false) Integer books_per_page,
                        @RequestParam(name = "sort_by_year", required = false, defaultValue = "false") boolean sortByYear,
                        Model model) {
        if (page != null && books_per_page != null) {
            model.addAttribute("books", booksService.findAllWithPagination(page, books_per_page, sortByYear));
        } else {
            model.addAttribute("books", booksService.findAll(sortByYear));
        }
        return "books/index";
    }

    @GetMapping("/search")
    public String search(@ModelAttribute("startsWith") String startsWith, Model model) {
        System.out.println("Hi");
        return "books/search";
    }

    @PostMapping("/search")
    public String find(@RequestParam("startsWith") String startsWith,
                       Model model) {
        List<Book> books = booksService.findBooksByNameStartingWith(startsWith);
        model.addAttribute("books", books);
        return "books/search";
    }

    @GetMapping("/{id}")
    public String infoBook(@ModelAttribute("person") Person person,
                             @PathVariable("id") int id, Model model) {
        Book book = booksService.findById(id);
        model.addAttribute("book", book);
        if (book.getOwner() != null) {
            model.addAttribute("owner", book.getOwner());
        } else {
            model.addAttribute("people", peopleService.findAll());
        }
        return "books/info";
    }

    @PatchMapping("/{id}/assign")
    public String setOwner(@ModelAttribute("person") Person person,
                           @PathVariable int id) {
        Book book = booksService.findById(id);
        book.setOwner(person);
        booksService.update(id, book);
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
        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult) {
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors())
            return "books/edit";
        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteOwner(@PathVariable int id) {
        Book book = booksService.findById(id);
        book.setOwner(null);
        booksService.update(id, book);
        return "redirect:/books/{id}";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        booksService.deleteById(id);
        return "redirect:/books";
    }
}
