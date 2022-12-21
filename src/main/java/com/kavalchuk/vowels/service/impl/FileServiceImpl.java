package com.kavalchuk.vowels.service.impl;

import com.kavalchuk.vowels.model.Word;
import com.kavalchuk.vowels.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FileServiceImpl implements FileService {
    public static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Override
    public List<String> read(final String inputFileName) {
        try (var bufferedReader = new BufferedReader(new FileReader(inputFileName))) {
            return bufferedReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("File {} doesn't exist", inputFileName);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean write(final String outputFileName, final List<Word> outputResult) {
        try (var bufferedWriter = new BufferedWriter(new FileWriter(outputFileName))) {
            for (var str : outputResult) {
                bufferedWriter.write(str.toString() + "\n");
            }
            return true;
        } catch (IOException e) {
            logger.error("Something goes wrong...");
            throw new RuntimeException(e);
        }
    }
}
