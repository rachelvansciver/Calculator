I found ANTLR to be a bit confusing for a newbie, and here is my explanation of how I got it work in IntelliJ 2020.3
1.	Download Antlr
  a.	Open IntelliJ, navigate to plugins, install antlr4. Restart IDE.
2.	Add Antlr Jar to modules
     a. While in the project, navigate to file -> project structure -> modules -> dependencies
        i.	Add Antlr.runtime jar path (I searched using This PC) to modules and make sure to set to compile time
3.	Generate Antlr Files	
  a.	Right click on your .g4 file and click configure Antlr
  b.	Add generated Antlr grammar files to your source code (put them in src file with a name of your choice to the package so they are visible to your implementations of the generated antlr files). After this, click on generate ANTLR Recognizer to generate those ANTLR files. Happy Coding! 









