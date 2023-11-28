package ru.korolev.exchange.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.korolev.exchange.dao.DocumentDao;
import ru.korolev.exchange.model.DocumentForm;
import ru.korolev.exchange.model.DocumentModel;

import java.util.Collections;
import java.util.List;

@Controller
public class AnnotationController {
    @Autowired
    private DocumentDao documentDao;

    @RequestMapping(path = "/documents", method = RequestMethod.GET)
    private ModelAndView getDocuments() {
        String test = "Hello";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents");
        modelAndView.addObject("documentFromServer", test);
        return modelAndView;
    }

//    @RequestMapping(path = "/documents", method = RequestMethod.POST)
//    private  String saveDocument(DocumentForm documentForm) {
//        DocumentModel build = DocumentModel.builder()
//                .view(documentForm.getView())
//                .organization(documentForm.getOrganization())
//                .date(documentForm.getDate())
//                .description(documentForm.getDescription())
//                .patient(documentForm.getPatient())
//                .status("Новая").build();
//
//        documentDao.save(build);
//        return "redirect:/documents";
//    }
}
