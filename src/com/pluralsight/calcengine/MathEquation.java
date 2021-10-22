package com.pluralsight.calcengine;

public class MathEquation {
    double leftVal;
    double rightVal;
    double result;
    char opCode;

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
}
