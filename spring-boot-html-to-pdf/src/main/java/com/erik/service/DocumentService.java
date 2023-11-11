package com.erik.service;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class DocumentService {

    // Метод для преобразования HTML в PDF
    public void htmlToPdf(String processedHtml) throws IOException {

        // Создание потока байт для хранения PDF
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Инициализация объекта для записи PDF
        PdfWriter pdfwriter = new PdfWriter(byteArrayOutputStream);

        // Инициализация поставщика шрифтов для PDF
        DefaultFontProvider defaultFont = new DefaultFontProvider(false, true, false);

        // Настройка свойств конвертера
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setFontProvider(defaultFont);

        // Преобразование HTML в PDF
        HtmlConverter.convertToPdf(processedHtml, pdfwriter, converterProperties);

        // Создание файла PDF и запись в него данных
        FileOutputStream fout = new FileOutputStream("./person.pdf");
        byteArrayOutputStream.writeTo(fout);
        byteArrayOutputStream.close();
        byteArrayOutputStream.flush();
        fout.close();

    }
}