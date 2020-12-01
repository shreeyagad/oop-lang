Our project uses java 1.8.0 so that that version of Java will be required to run our program

To compile the java files, use the following command: 

$ javac -d out -cp out:junit-platform-console-standalone-1.7.0.jar *.java


To run the tests that we created, use this command:

$ java -jar junit-platform-console-standalone-1.7.0.jar --classpath out -c Tests


To run the interactive interpreter, use this:

$ cd out
$ java Main

This interpreter interprets String, Integer, and Boolean expression similar in syntax to Java. Unary Operators and Parentheses have not yet been implemented. To exit the interpreter, type 'end' and hit enter