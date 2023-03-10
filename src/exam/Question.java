package exam;

import java.util.Arrays;
import java.util.List;

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
        String[] awnserStrings = awnserString.split(" ");
        Integer[] awnserIntegers = new Integer[awnserStrings.length];
        for (int i = 0; i < awnserStrings.length; i++) {
            awnserIntegers[i] = Integer.valueOf(awnserStrings[i]);
        }
        try {
            List<Integer> indices = Arrays.asList(awnserIntegers);
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
