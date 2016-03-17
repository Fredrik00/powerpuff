package Treningsdagbok;

import java.util.Scanner;

public class Interpreter {
    private String[] query;

    Connector connector = new Connector();

    public boolean initialized()
    {
        return connector.initialized_correctly;
    }

    public void Check(String s) {

            if(connector==null)
                return;

	        query = s.split(";");
	        if(query[0].equals("r")) {
	            connector.Read(query[1]);
	        }
	        else if(query[0].equals("w")) {
	            if (connector.Write(query[1])){
	            	System.out.println("Query executed successfully");
	            }
	        }
	        else if(query[0].equals("c")) {
	        	String[] values = query[1].split(" ");
	        	System.out.println(connector.compare(Integer.parseInt(values[1]), Integer.parseInt(values[2])));
	        }
	        else{
	        	System.out.println("ERROR ERROR");
	        }
    	}

    public static void main (String[] args) {
        Interpreter interpreter = new Interpreter();

        if(!interpreter.initialized())
            return;

        Scanner scanner = new Scanner(System.in);
        while (true){
	        System.out.print("Write your (w/r) followed by a ';' and you SQL-statement: ");
	        String reply = scanner.nextLine();
	        if (reply.equals("exit")){
	        	interpreter.connector.exit();
	        	break;
	        }
	        interpreter.Check(reply);
        }
        scanner.close();
    }
}
