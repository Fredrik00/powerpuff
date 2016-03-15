package Treningsdagbok;

import java.util.Scanner;

public class Interpreter {
    private String[] query;


    Connector connector = new Connector();

    public void Check(String s) {
    	if (s == "exit"){
    		connector.exit();
    	}

    	else {
	        query = s.split(";");
	        if(query[0].equals("r")) {
	            connector.Read(query[1]);
	        }
	        else if(query[0].equals("w")) {
	            if (connector.Write(query[1])){
	            	System.out.println("Query executed successfully");
	            }
	        }

	        else{
	        	System.out.println("ERROR ERROR");
	        }
    	}
    }

    public static void main (String[] args) {
        Interpreter interpreter = new Interpreter();
        Scanner scanner = new Scanner(System.in);
        while (true){
	        System.out.print("Write your (w/r) followed by a ';' and you SQL-statement: ");
	        String reply = scanner.nextLine();
	        if (reply == "exit"){
	        	break;
	        }
	        interpreter.Check(reply);
        }
    }
}
