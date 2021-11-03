package com.pluralsight.calcengine;

public class CalculateBase {
    private double leftVal;
    private double rightVal;
    private double result;

    // Because our fields are private, we need to make getters and setters again
    public double getLeftVal() {
        return leftVal;
    }
    public void setLeftVal(double leftVal) {
        this.leftVal = leftVal;
    }

    public double getRightVal() {
        return rightVal;
    }
    public void setRightVal(double rightVal) {
        this.rightVal = rightVal;
    }

    public double getResult() {
        return result;
    }
    public void setResult(double result) {
        this.result = result;
    }

    public void calculate() {

    }
}
