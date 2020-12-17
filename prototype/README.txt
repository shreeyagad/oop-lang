Our project uses java 1.8.0 so that that version of Java will be required to run our program

To compile the java files, use the following command: 

$ javac -d out -cp out:junit-platform-console-standalone-1.7.0.jar *.java


To run the tests that we created, use this command:

$ java -jar junit-platform-console-standalone-1.7.0.jar --classpath out -c Tests


To run the interactive interpreter, use this:

$ cd out
$ java Main

This interpreter has all the functionality specified in our documentation included in the writeup. To run a program, type it into the interpreter and at the end of the program add an extra semicolon (It will look like two semicolons at the end due to the semicolon from the very last statement) and hit Enter. The interpreter will print out the value returned by the very last statement. To exit the interpreter, type 'end;;' and hit enter. Any series of programs you run before the 'end;;' command will be interpreted under the same environment.