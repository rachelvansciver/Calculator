# I found ANTLR to be a bit confusing for a newbie, and here is my explanation of how I got it work in IntelliJ 2020.3.
## Installing ANTLR
Open IntelliJ, navigate to plugins, install antlr4. Restart IDE. 
![Installing ANTLR]("C:\Users\eh\Pictures\Screenshots\Screenshot (3).png")
## Add ANTLR JAR to modules
While in the ANTLR project, navigate to file -> project structure -> modules -> dependencies
Add Antlr.runtime jar path (I searched using This PC) to modules and make sure to set to compile time.
![Adding .jar file] ("C:\Users\eh\Pictures\Screenshots\Screenshot (5).png")
## Configuring ANTLR
Right click on your .g4 file and click configure ANTLR.
Add generated Antlr grammar files to your source code (put them in src file with a name of your choice to the package so they are visible to your implementations of the generated antlr files). After this, click on generate ANTLR Recognizer to generate those ANTLR files. Happy Coding! 
![Generating ANTLR files]("C:\Users\eh\Pictures\Screenshots\Screenshot (9).png")









