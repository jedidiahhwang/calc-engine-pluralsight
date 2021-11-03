package com.pluralsight.calcengine;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
// 		performCalculations();

		// Uses the divider calculate() method instead
 		Divider divider = new Divider();
 		doCalculation(divider, 100.0d, 50.0d);

 		// Uses the adder calculate() method instead
 		Adder adder = new Adder();
 		doCalculation(adder, 25.0d, 92.0d);
    }

    static void doCalculation(CalculateBase calculation, double leftVal, double rightVal) {
    	calculation.setLeftVal(leftVal);
    	calculation.setRightVal(rightVal);
    	calculation.calculate(); // Even though CalculateBase calculate() is empty, @Override will pick the extended class
    	System.out.println("Calculation result = " + calculation.getResult());
	}

    static void performCalculations() {
    	// Currently takes in hard-coded values and does the calculation
		MathEquation[] equations = new MathEquation[4]; // Creates an array of MathEquation references
		equations[0] = new MathEquation('d', 100.0d, 50.0d);
		equations[1] = new MathEquation('a', 25.0d, 92.0d);
		equations[2] = new MathEquation('s', 225.0d, 17.0d);
		equations[3] = new MathEquation('m', 11.0d, 3.0d);

		for(MathEquation equation: equations) {
			equation.execute(); // This is calling the execute() method made in MathEquation class
			System.out.println("result = " + equation.result);
		}

		System.out.println("Average result = " + MathEquation.getAverageResult());

		System.out.println();
		System.out.println("Using execute overloads");
		System.out.println();

		MathEquation equationOverload = new MathEquation('d');
		double leftDouble = 9.0d;
		double rightDouble = 4.0d;
		equationOverload.execute(leftDouble, rightDouble);
		System.out.println("Overloaded result with doubles: " + equationOverload.getResult());

		// Converting from int to double is considered a widening conversion, so the following is legal
		int leftInt = 9;
		int rightInt = 4;
		equationOverload.execute(leftInt, rightInt);
		System.out.println("Overloaded result with ints: " + equationOverload.getResult());
	}

	static void executeInteractively() {
    	// Takes input from command line
    	System.out.println("Enter an operation and two numbers:");
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		String[] parts = userInput.split(" ");
		performOperation(parts);
	}

	private static void performOperation(String[] parts) {
    	// Takes in string arguments and handled date/time values as well as regular values
		char opCode = opCodeFromString(parts[0]);

		if(opCode == 'w') {
			handleWhen(parts);
		} else {
			double leftVal = valueFromWord(parts[1]);
			double rightVal = valueFromWord(parts[2]);
			double result = execute(opCode, leftVal, rightVal);
			displayResult(opCode, leftVal, rightVal, result);
		}
	}

	private static void handleWhen(String[] parts) {
    	// For handling addition of time
		LocalDate startDate = LocalDate.parse(parts[1]); // parse will change that string into a date format
		long daysToAdd = (long) valueFromWord(parts[2]);
		LocalDate newDate = startDate.plusDays(daysToAdd);
		String output = String.format("%s plus %d days is %s", startDate, daysToAdd, newDate);
		System.out.println(output);
	}

	private static void displayResult(char opCode, double leftVal, double rightVal, double result) {
    	// Shows the text using StringBuilder and String.format
		char symbol = symbolFromOpCode(opCode);
//		StringBuilder builder = new StringBuilder(20);
//		builder.append(leftVal);
//		builder.append(" ");
//		builder.append(symbol);
//		builder.append(" ");
//		builder.append(rightVal);
//		builder.append(" = ");
//		builder.append(result);
//		String output = builder.toString();

		String output = String.format("%.3f %c %.3f = %.3f", leftVal, symbol, rightVal, result);
		System.out.println(output);
	}

	private static char symbolFromOpCode(char opCode) {
    	// Uses an array to store opCode values, correlates them with a parallel array
    	char[] opCodes = {'a', 's', 'm', 'd'};
    	char[] symbols = {'+', '-', '*', '/'};
    	char symbol = ' ';
    	for(int index = 0; index < opCodes.length; index++) {
    		if(opCode == opCodes[index]) {
    			symbol = symbols[index];
    			break;
			}
		}
    	return symbol;
	}

	private static void handleCommandLine(String[] args) {
    	// Takes in command line input and parses/executes them accordingly
		char opCode = args[0].charAt(0); // Converts string to char
		double leftVal = Double.parseDouble(args[1]); // Converts a sequence of characters into a double
		double rightVal = Double.parseDouble(args[2]);
		double result = execute(opCode, leftVal, rightVal);
		System.out.println(result);
	}

	static double execute(char opCode, double leftVal, double rightVal) {
    	// Does the calculating, exported into a class
    	double result;
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
		return result;
	}

	static char opCodeFromString(String operationName) {
    	// Grabs the single character opCode (first char)
    	char opCode = operationName.charAt(0);
    	return opCode;
	}

	static double valueFromWord(String word) {
    	// Converts text to numbers using array indexes, or uses Double wrapper to process numbers
    	String[] numberWords = {
    			"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
		};
    	double value = -1d;
    	for(int index = 0; index < numberWords.length; index++) {
    		if(word.equals(numberWords[index])) {
    			value = index;
    			break;
			}
		}
		if(value == -1d) {
			value = Double.parseDouble(word);
		}


    	return value;
	}
}

