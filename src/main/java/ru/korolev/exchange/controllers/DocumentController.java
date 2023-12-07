package ru.korolev.exchange.controllers;

import jakarta.validation.Valid;
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
public class DocumentController {
    private final DocumentDao documentDao;

    @Autowired
    public DocumentController(DocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("allDocs", documentDao.index());
        return "docs/main";
    }

    @GetMapping("/new")
    public String newDocs(Model model) {
        model.addAttribute("fill", new Document());
        return "docs/new";
    }

    @PostMapping()
    public String createDocs(@ModelAttribute("fill") @Valid Document document,
                             BindingResult bindingResult) throws InterruptedException {
        if (bindingResult.hasErrors()) {
            return "docs/new";
        }
        documentDao.save(document);
        return "redirect:/docs";
    }
}
