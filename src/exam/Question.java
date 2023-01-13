package exam;

import java.util.List;
import java.util.stream.Stream;

public class Question {
    private String name;
    private List<Answer> awnsers;

    public Question(String question, List<Answer> awnsers){
        this.name = question;
        this.awnsers = awnsers;   
    }

    public String getName() {
        return name;
    }

    public List<Answer> getAwnsers() {
        return awnsers;
    }

    public Boolean isRightAnswer(String awnserString){
        try {
            List<Integer> indices = 
                Stream.of(awnserString.split(" "))
                    .map(e -> Integer.valueOf(awnserString))
                    .toList();
            for (int i = 0; i < awnsers.size(); i++) {
                if(awnsers.get(i).getCorrect() != indices.contains(i)){
                    return false;
                }
            }
        } catch(NumberFormatException e){
            System.err.println("The last given awnser was incorrectly formatted.");
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Question other = (Question) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
