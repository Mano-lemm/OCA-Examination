import java.util.List;
import java.util.Scanner;

import exam.Answer;
import exam.Exam;
import exam.Question;
import fileParsing.IntecOCAExamParser;
import fileParsing.Result;
import fileParsing.Tuple;

public class App {
    public static void main(String[] args) throws Exception {
        String fileName = null;
        Scanner scanner = new Scanner(System.in);
        if (!(args.length == 0)) {
            fileName = args[0];
        } else {
            try{
                System.out.println("Please give the name of the file that should be parsed.");
                fileName = scanner.nextLine();
            } catch (Exception e) {
                System.err.println("Scanner error.");
                System.exit(1);
            }
        }

        Tuple<Exam, Result> Parsed = IntecOCAExamParser.parseFile(fileName);

        switch (Parsed.getR()) {
            case FAILURE:
                System.err.println("The file failed to parse and did not produce an exam to try at all.");
                System.exit(1);
                break;

            case PARTIAL:
                System.out.println("The file was only partially parsed, there may be issues with this exam.");
                System.out.println("Would you like to do the exam anyway(y/n)?");
                try{
                    if(!scanner.nextLine().equalsIgnoreCase("y")){
                        System.exit(0);
                    }
                } catch (Exception e) {
                    System.err.println("Scanner error.");
                    System.exit(1);
                }
                break;

            case SUCCESS:
                System.out.printf("The file was successfully parsed, the exam \"%s\" will start. \nYou will be scored at the end.\n", Parsed.getT().getName());
                break;
        }
        
        /*  TODO:implement -h
        System.out.println("You will be asked a question and given multiple choices for awnsers.");
        System.out.println("Each of the choices is preceded by an index in the format \"[index])\".");
        System.out.println("Type the indices of one or multiple correct answers. \n(The question might state whether or not this is the case)\n\n");
        */

        System.out.print(Parsed.getT().getName());
        int totalCorrect = 0;
        try{
            for (Question question : Parsed.getT()) {
                System.out.print(question.getName());

                System.out.println("options:");
                List<Answer> answers = question.getAwnsers();
                for (int i = 0; i < answers.size(); i++) {
                    System.out.printf("%d) %s",i , answers.get(i).getName());
                }
                
                if(question.isRightAnswer(scanner.nextLine())){
                    totalCorrect++;
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        System.out.printf("You got %d/%d this is roughly %.2f%% \n",
            totalCorrect, Parsed.getT().getQuestionCount(), (double) totalCorrect / Parsed.getT().getQuestionCount());
        
        scanner.close();
    }
}
