package com.example.labelmaker;

import javafx.scene.control.TextArea;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class LabelCreator {
    private FileController controller;
    private final Scanner scanner;
    private final String output;
    private final ArrayList<String> allergens;

    LabelCreator(String path, String output) throws FileNotFoundException {
        controller = new FileController();
        scanner = controller.openFile(path);
        this.output = output;
        allergens = new ArrayList<String>(Arrays.asList("Milk, ", "Eggs, ", "Fish ", "Salmon, ", "Tuna, ", "Shellfish, ", "Shrimp, ", "Crab, ", "Lobster, ", "Nuts, ", "Peanuts, ", "Almonds, ", "Walnuts, ", "Cashews, ", "Wheat, ", "Flour, ", "Soy, ", "Sesame, "));
    }

    public void create(TextArea output) throws IOException {
        if (scanner == null) {
            output.setText("Error opening file");
        }
        else {
            FileWriter index = createIndex();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                ArrayList<String> list = splitString(line, " ");
                writeHTML(list, index);
            }
            index.close();
        }
    }

    /// Creates the file writer for index.html and writes the header
    private FileWriter createIndex() throws IOException {
        controller.createFile(output + "\\index.html");
        FileWriter indexWriter =  new FileWriter(output + "\\index.html");
        indexWriter.write("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\" /><style>div {text-align: center;} li {display: block;width:" +
                "20%%;float: left;} button {padding:5px 10px; color:#000; border-radius:12px; background-color:#91ab66;}</style><title>Labels" +
                "</title></head><body><a href=\"https://github.com/JSneeky/Label\"><button style=\"position:10px 10px;\">Github</button>" +
                "</a><div><h1>Labels</h1></div><hr width=\"100%%;\" color=\"black\" size=\"2\"><ul>");
        return indexWriter;
    }

    /// Adds the footer to the index.html file
    private void endIndex(FileWriter index, String productName) throws IOException {
        index.write("<li><a href=\"./" + productName + ".html\"><button>" + productName + "</button></a></li></ul></Body></html>");
    }

    /// Splits a string based on a given regex
    private ArrayList<String> splitString(String s, String regex) {
        String[] split = s.split(regex);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(split));
        return list;
    }

    /// Creates a FileWriter for the given product
    private FileWriter createWriter(String s) throws IOException {
        String productName = s + ".html";
        controller.createFile(output + "\\" + productName);
        return new FileWriter(output + "\\" + productName);
    }

    /// If the ingredient is an allergen, return true
    private boolean allergen(String ing) {
        return allergens.contains(ing);
    }

    /// If there is an empty set of commas e.g. ', ' return true
    private boolean empty(String ing) {
        return ing.equals(", ");
    }

    /// Creates a writer for product and writes the header to the product file
    private void writeHTML(ArrayList<String> list, FileWriter index) throws IOException {
        String productName = removeComma(list.get(0));
        FileWriter productHTML = createWriter(productName);
        productHTML.write("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\" /><title> " + productName + "</title><style>div {text-align: center;}</style></head><body><link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">" +
                "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Libre+Barcode+39+Extended+Text&display=swap\" rel=\"stylesheet\"><div><h1>");
        addElements(productHTML, productName, list);
        endIndex(index, productName);
    }

    /// Adds the html to the product.hmtl file that displays it's name, ingredients, price and barcode
    private void addElements(FileWriter productHTML, String productName, ArrayList<String> list) throws IOException {
        /// Adds the products name
        productHTML.write(productName + "</h1><h1>");
        /// Removes the comma from the end of the price and adds it to the file
        String price = list.get(1);
        price = removeComma(price);
        productHTML.write(price + "</h1><p style=\"font-size: 20px\">Ingredients:</p><p style=\"font-size: 20px\">");

        /// Adds each of the ingredients to the file and bolds them if they are an allergen
        int i;
        for (i = 2; i < list.size() - 1; i++) {
            String ingredient = list.get(i) + " ";
            if (empty(ingredient));
            else if (allergen(ingredient)) {
                productHTML.write("<b>" + ingredient + "</b>");
            }
            else productHTML.write(ingredient);
        }
        /// Adds the barcode
        productHTML.write("</p><span style=\"font-family: 'Libre Barcode 39 Extended Text'; font-size: 55px;\">");
        String barcode = removeComma(list.get(i));
        productHTML.write(barcode + "</span></div></body></html>");
        productHTML.close();
    }

    /// Removes a comma from the end of a string if it is present
    private String removeComma(String s) {
        return s.substring(0, s.lastIndexOf(","));
    }
}
