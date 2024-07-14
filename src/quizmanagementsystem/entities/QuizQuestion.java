/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quizmanagementsystem.entities;

/**
 *
 * @author Hassaan.Siddique
 */
public class QuizQuestion {
    private String quizId;
    private Question Question;
    private String QuestionId;
    private int srNo;
    private String selectedOption;

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public Question getQuestion() {
        return Question;
    }

    public void setQuestion(Question Question) {
        this.Question = Question;
    }

    public String getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(String QuestionId) {
        this.QuestionId = QuestionId;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" + "quizId=" + quizId + ", Question=" + Question.toString() + ", QuestionId=" + QuestionId + ", srNo=" + srNo + ", selectedOption=" + selectedOption + '}';
    }
    
    
}
