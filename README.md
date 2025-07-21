# LabelMaker
LabelMaker is a java program that creates labels from .csv files. It does this by creating individual html files for each product in the csv 
file and generating a label on that html page.

GUI:
Input Filepath - This should be the path of the .csv file (./products.txt)
Output Filepath - This should be the folder where you want the html files to be created

Important Information:
The program contains a list of allergens in the LabelCreator class. This can be added to to create a more comprehensive list of allergens.

Documentation:
An example .csv file has been provided in src/main/resources/com/docs. To run the program with this file enter the Input Filepath as 
~/PATH/src/main/resources/com/docs/products.txt and the Output Filepath as ~/PATH/src/main/resources/com/docs, where PATH is where you 
downloaded this repository.
