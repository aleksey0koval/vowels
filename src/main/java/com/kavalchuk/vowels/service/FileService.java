package com.kavalchuk.vowels.service;

import com.kavalchuk.vowels.model.Word;

import java.util.List;

/**
 * Service for working with files.
 */
public interface FileService {
    /**
     * Reading info from file.
     *
     * @param fileName - name of file for reading.
     * @return list of read lines.
     */
    List<String> read(String fileName);

    /**
     * Writing info to file
     *
     * @param fileName - name of file for writing
     * @param output   - info for writing.
     * @return true, if writing is successful, else - false.
     */
    boolean write(String fileName, List<Word> output);
}
