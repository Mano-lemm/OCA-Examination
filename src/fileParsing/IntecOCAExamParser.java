package fileParsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exam.Answer;
import exam.Exam;
import exam.Question;

public class IntecOCAExamParser {
    /**
     * @param FileName The name of the file 
     * 
     * @return The exam described in the file and whether or not it was succesfully parsed
     */
    public static Tuple<Exam, Result> parseFile(String FileName){
        Exam rExam = new Exam();
        Result result = Result.SUCCESS;

        if (!FileName.endsWith(".iOCA")) {
            System.err.println("File is not of .iOCA format.");
            return new Tuple<Exam,Result>(null, Result.FAILURE);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("./exams/" + FileName))) {
            String line;
            String command = "";
            String question = "";
            List<Answer> awnsers = new ArrayList<>(4);
            Boolean awnserIsTrue = false;
            StringBuilder cur = new StringBuilder();
            Statements statement = Statements.NONE;
            while ((line = reader.readLine()) != null) {
                if(!line.isBlank()){
                    if(line.charAt(0) == ':') {
                        command = line.split(" ")[0].substring(1);
                    } else if(statement == Statements.NONE){
                        continue;
                    }
                }

                switch (command) {
                    case "NAME":
                        statement = Statements.NAME;
                        break;
                    case "QUESTION":
                        statement = Statements.QUES;
                        break;
                    case "ANSWER":
                        if(statement == Statements.ANSW){
                            awnsers.add(new Answer(cur.toString(), awnserIsTrue));
                        } else if(statement == Statements.QUES){
                            question = cur.toString();
                        } else {
                            result = Result.PARTIAL;
                            return new Tuple<Exam,Result>(rExam, result);
                        }
                        cur.setLength(0);
                        statement = Statements.ANSW;
                        awnserIsTrue = line.charAt(8) == 'T' ? true : false;
                        break;
                    case "END":
                        awnsers.add(new Answer(cur.toString(), awnserIsTrue));
                        cur.setLength(0);
                        rExam.addQuestion(new Question(question, awnsers));
                        awnsers = new ArrayList<>();
                        statement = Statements.END;
                        break;
                    default:
                        if(statement != Statements.NONE && !command.isBlank() && statement != Statements.NAME){
                            result = Result.FAILURE;
                            System.err.println("Unknown command: " + command);
                            return new Tuple<Exam,Result>(rExam, result);
                        }
                }

                if(!command.isBlank()){
                    command = "";
                    continue;
                }

                switch (statement) {
                    case ANSW:
                    case QUES:
                        cur.append(line);
                        cur.append("\n");
                        break;
                    case NAME:
                        rExam.setName(line);
                        statement = Statements.NONE;
                    default:
                        break;
                }
            }

            if(!(statement == Statements.NONE || statement == Statements.END)){
                result = Result.PARTIAL;
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = Result.FAILURE;
        }
        
        return new Tuple<Exam,Result>(rExam, result);
    }
}
