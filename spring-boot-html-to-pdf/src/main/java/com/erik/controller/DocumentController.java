package com.erik.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import com.erik.service.DocumentService;
import com.erik.mapper.DataMapper;
import com.erik.model.Person;

@RestController
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    @Autowired
    private DataMapper dataMapper;

    // Обработка POST-запроса для генерации документа
    @PostMapping(value = "/generate/document")
    public String generateDocument(@RequestBody List<Person> personList) throws IOException {

        // Создание объекта Context с данными о сотрудниках
        Context dataContext = dataMapper.setData(personList);

        // Генерация HTML на основе шаблона и данных
        var finalHtml = springTemplateEngine.process("template", dataContext);

        // Преобразование сгенерированного HTML в PDF
        documentService.htmlToPdf(finalHtml);

        return "Success";
    }
}
