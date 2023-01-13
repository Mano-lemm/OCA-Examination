# This is a program to use for self testing in a more streamlined manner

You are required to compile it yourself, since I do not know which version of the JVM you have installed. <br>
Both Intellij as well as VSCode should be able to compile it although I cannot test this for Intellij.


# Folder Structure

- `src`: the folder that contains all source files
- `exam`: the folder that contains all pre-written exams (may be empty)
- `lib`: the folder that contains all dependencies outside of java core (should be empty)
- `.vscode`: the folder that contains VSCode specific files (Intelij should ignore this folder, you can safely delete it in case it is causing issues)


# File formatting for `.iOCA` files

.iOCA files Have a simple format, deviation from it will (likely) result in a failure to parse the file. <br>
The parser will inform you what is wrong about the file generally. <br>
I have not yet had time to include information such as line number that might help you debug your .iOCA file. <br>
This will likely be introduced in the coming week. <br>

.iOCA files use the following formatting

    :NAME
    [NAME]
    This defines the name of the exam

    :QUESTION
    [QUESTION]
    This defines the start of a question.
    
    :ANSWER [T|F]
    [ANSWER]
    This defines the start of an awnser
    and whether or not it is true (T is a true awnser, F a false one)

    :END 
    This defines the end of a question-awnser tuple.


# rules:
- A File may only contain 1 `:NAME` statement 
- The question-answer-end statements must come in that order 

# notes:
- There is no limit on the amount of questions you can put in a file, but putting more than 30 in is strongly discouraged
- When a question has multiple awnsers, each option should have its own `:ANSWER` statement
- `:QUESTION`, `:ANSWER` and `:NAME` statements expect the argument to start on the next line
- `:NAME` statement expect its argument to be exactly 1 line long