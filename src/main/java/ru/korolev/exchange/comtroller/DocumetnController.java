package ru.korolev.exchange.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import ru.korolev.exchange.dao.DocumentDao;
import ru.korolev.exchange.model.DocumentModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Component
public class DocumetnController implements Controller {

    @Autowired
    private DocumentDao documentDao;

    @Override
    public ModelAndView handleRequest(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response) throws Exception {
        DocumentModel documentModel = documentDao.find(1L).get();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("documents");
        modelAndView.addObject("documentFromServer", Collections.singleton(documentModel));
        return modelAndView;
    }
}
