package com.example.labelmaker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileController {
    public Scanner openFile(String path) throws FileNotFoundException {
        try {
            File products = new File(path);
            Scanner reader = new Scanner(products);
            return reader;
        }
        catch (FileNotFoundException e) {
            return null;
        }
    }

    public void createFile(String path) throws IOException {
        try {
            File products = new File(path);
            products.createNewFile();
        }
        catch (IOException e) {
            throw new IOException();
        }
    }
}
