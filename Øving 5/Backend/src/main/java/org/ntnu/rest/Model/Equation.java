package org.ntnu.rest.Model;


public class Equation {
    private String num1, num2;
    private char operator;
    private double answer;
    private String username;

    public char getOperator() {
        return operator;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public Equation(){}

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getEquation(){
        String eq = getNum1() + getOperator() + getNum2();
        if(getAnswer()!=0.0){
            eq+="=" + getAnswer();
        }
        return eq;
    }

    public String getFullEqution(){
        String eq = getNum1() + getOperator() + getNum2() + "=" + getAnswer();
        return eq;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
