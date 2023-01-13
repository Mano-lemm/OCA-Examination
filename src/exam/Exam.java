package exam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Exam implements Iterable<Question>{
    private List<Question> QAList;
    private String Name;

    public Exam(){
        this(new ArrayList<Question>());
    }
    
    public Exam(List<Question> QAList){
        this(QAList, "UNNAMED");
    }

    public Exam(List<Question> QAList, String Name) {
        this.QAList = QAList;
        this.Name = Name;
    }

    @Override
    public Iterator<Question> iterator() {
        Collections.shuffle(QAList);
        return new Iterator<Question>() {
            Iterator<Question> it = QAList.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Question next() {
                return it.next();
            }
        };
    }

    public Boolean addQuestion(Question question){
        if(QAList.contains(question)){
            return false;
        }
        QAList.add(question);
        return true;
    }

    public int getQuestionCount(){
        return QAList.size();
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
