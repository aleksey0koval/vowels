package com.kavalchuk.vowels;

import com.kavalchuk.vowels.service.FileService;
import com.kavalchuk.vowels.service.WordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class VowelsApplication {
    public static void main(String[] args) throws IOException {
        var applicationContext = SpringApplication.run(VowelsApplication.class, args);
        var fileService = applicationContext.getBean(FileService.class);
        var wordService = applicationContext.getBean(WordService.class);

        var scanner = new Scanner(System.in);
        System.out.println("***********************************************************************");
        System.out.println("Please enter the file name to read data...");
        System.out.println("***********************************************************************");
        var fileName = scanner.next();
        List<String> list = null;
        try {
            list = fileService.read(fileName);
        } catch (RuntimeException e) {
            var defaultFile = "input.txt";
            System.out.println("***********************************************************************");
            System.out.printf("File '%s' doesn't exist!. Instead '%s' use default file '%s'.%n", fileName, fileName, defaultFile);
            System.out.println("***********************************************************************");
            list = fileService.read(defaultFile);
        }
        var words = wordService.calculateAverageVowels(list);
        System.out.println("***********************************************************************");
        System.out.println("Please enter the file name where you want to save result...");
        System.out.println("***********************************************************************");
        fileName = scanner.next();
        try {
            fileService.write(fileName, words);
        } catch (RuntimeException e) {
            var defaultFile = "output.txt";
            System.out.println("***********************************************************************");
            System.out.printf("Directory '%s' doesn't exist!. Instead '%s' use default file '%s'.%n", fileName, fileName, defaultFile);
            System.out.println("***********************************************************************");
            fileService.write(defaultFile, words);
        }
        words.forEach(System.out::println);
        System.out.println("***********************************************************************");
        System.out.println("App has been completed successfully.");
        System.out.println("***********************************************************************");
    }
}
