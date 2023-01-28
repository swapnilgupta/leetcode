import java.util.*;

enum Exam {CODE, LEARN, CONTRIBUTE, QUIZ, MCQ}
public class EnumSetExample {
    public static void main(String[] args){
        Set<Exam> setExam = EnumSet.of(Exam.LEARN, Exam.MCQ, Exam.QUIZ);
        System.out.println("Exam Set: " + setExam);
    }
}