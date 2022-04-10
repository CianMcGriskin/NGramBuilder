# NGramBuilder
<p align="center">
A program that extracts a user specified text sequence from a directory of text files and sorts the number of occurrences of the user specified sequence from high to low.
 </p>
 <h2 align="center">
  <b>How to run</b>
</h2>
Command: Java -Xmx1G –cp ./ngrammer.jar ie.gmit.sw.Runner

<h2 align="center">
  <b>The Menu</b>
</h2>


You are introduced to a menu of options:

•	Specifying Text File Directory: This option prompts the user to enter what text files they would like to read (Must be .txt file)

•	Specify n-Gram Size: This lets the user set the n-gram size they would like, ideally 1-5 

•	Specify Output File Directory: This lets the user specify where they would like to output their CSV file.

•	Build n-Grams: This is the main process of the application which builds the n-gram csv file.
 
 
HashMap Sorting
The output file has been successful and has given me the number of occurrences of each 3 character n-gram, which is also sorted from high to low using a hashmap sorting method.
 
**How I did it?**

Directory Filtering:
 ![image](https://user-images.githubusercontent.com/81272459/162643365-d60cfc5e-9e2e-4d15-a582-2195fec512a4.png)
Process of n-Gram Extraction:
 ![image](https://user-images.githubusercontent.com/81272459/162643360-feef9a34-e1b1-4f40-8992-4d4bb1442728.png)
Outputting the HashMap:
![image](https://user-images.githubusercontent.com/81272459/162643332-9df2e653-80b4-422d-b007-d82750ecbeb9.png)
