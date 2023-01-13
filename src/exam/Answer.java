package exam;

public class Answer {
    private Boolean correct;
    private String name;

    public Answer(String name, Boolean correct){
        this.name = name;
        this.correct = correct;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public String getName() {
        return name;
    }
}
