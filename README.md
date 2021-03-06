# OOPLang

## Summary

OOPLang is an object-oriented language that is easy to learn and similar to Java. However, it is less verbose and more lenient with the types of expressions that can be formed. The language has standard features like literals; binary operations b/w arithmetic, boolean, and string expressions; variables; if/while commands; functions; and return, break, and print statements. It also supports defining classes, instantiating/modifying objects, and class inheritance.

Documentation can be found in the Wiki.

## Usage

Our project uses java 1.8.0, so that version of Java will be required to run our program.

To compile the java files, use the following command:

$ javac -d out -cp out:junit-platform-console-standalone-1.7.0.jar *.java

To run the tests that we created, use this command:

$ java -jar junit-platform-console-standalone-1.7.0.jar --classpath out -c Tests

To run the program, use this:

$ cd out  
$ java Main

The program will prompt the user to enter 1 if they wish to execute a .oop file and 2 if they wish to proceed to the interpreter.

**If the user enters 1, they will be prompted to enter the path to a .oop file.**
We have several .oop files in the src/examples folder. Copy the absolute path for any of the files, and enter the path into the console to run the interpreter on the .oop file. The interpreter will return the evaluation result of the program (if non-null).

**If the user enters 2, they will be directed to the interactive interpreter.**
To execute your OOPLang program, type it into the console. At the end of the program, add an extra semicolon (it will appear as two semicolons at the end due to the semicolon from the very last statement) and hit Enter. The interpreter will print the value returned by the very last statement. To exit the interpreter, type 'end;;' and hit enter. Any series of programs you run before the 'end;;' command will be interpreted under the same environment.

*For our CS 4110 Final Project.*
