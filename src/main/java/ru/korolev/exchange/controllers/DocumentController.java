package ru.korolev.exchange.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.korolev.exchange.dao.DocumentDao;
import ru.korolev.exchange.kafka.KafkaSendler;
import ru.korolev.exchange.model.Document;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/docs")
/**
 * A controller for processing user
 * requests and calling server services.
 * It structures the request,
 * creates an appropriate model for further
 * display in the browser.
 *
 * @author Nikita Korolev
 * @version 1.0
 */
public class DocumentController {
    @Autowired
    private DocumentDao documentDao;
    /**
     * The method on request /docs will
     * receive the page model,
     * call the method from DAO (Get all rows of the database)
     * and send the list to the page.
     * @param model Model - Web page model
     * @return String - redirecting the user to docs/main
     */
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("allDocs", documentDao.index());
        return "docs/main";
    }

    /**
     * The method redirects the user
     * to the page for adding a new
     * record to the database.
     * @param model Model - Web page model
     * @return String - redirecting the user to docs/new
     */
    @GetMapping("/new")
    public String newDocs(Model model) {
        model.addAttribute("fill", new Document());
        return "docs/new";
    }

    /**
     * The method accepts a POST request from the user (Filled out new Document model).
     * Calls the DAO save method, sends the model to the database and for verification in kafka.
     * @param document Document - A document is a form filled out by the user.
     * @param bindingResult Checking the correctness of information entered into fields.
     * @return Redirect the user back to the home page /docs.
     * @throws InterruptedException Error handler.
     */
    @PostMapping()
    public String createDocs(@ModelAttribute("fill") Document document,
                             BindingResult bindingResult) throws InterruptedException {
        if (bindingResult.hasErrors()) {
            return "docs/new";
        }
        documentDao.save(document);
        return "redirect:/docs";
    }
}
