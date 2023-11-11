package com.erik.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.erik.model.Person;

@Service
public class DataMapper {

    // Метод для создания объекта Context с данными о сотрудниках
    public Context setData(List<Person> personList) {

        Context context = new Context();

        Map<String, Object> data = new HashMap<>();

        // Добавление списка сотрудников в контекст
        data.put("person", personList);

        // Установка переменных контекста
        context.setVariables(data);

        return context;
    }
}
