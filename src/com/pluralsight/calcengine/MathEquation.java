package com.pluralsight.calcengine;

public class MathEquation {
    double leftVal;
    double rightVal;
    double result;
    char opCode;

    public MathEquation() {

    }

    public MathEquation(char opCode) {
        this.opCode = opCode;
    }

    public MathEquation(char opCode, double leftVal, double rightVal) {
        this(opCode);
        this.leftVal = leftVal;

    }

    void execute() {
        switch(opCode) {
            case 'a':
                result = leftVal + rightVal;
                break;
            case 's':
                result = leftVal - rightVal;
                break;
            case 'm':
                result = leftVal * rightVal;
                break;
            case 'd':
                result = rightVal != 0 ? leftVal / rightVal : 0;
				/*
					You could've also done...

					if(value2 != 0) {
						result = value1 / value2;
					}
				*/
                break;
            default:
                System.out.println("Invalid opCode: " + opCode);
                result = 0.0d;
                break;
        }
    }

    public double setLeftVal(double leftVal) {
        return leftVal;
    }

    public double setRightVal(double rightVal) {
        return rightVal;
    }

    public char setOpCode(char opCode) {
        return opCode;
    }



}
