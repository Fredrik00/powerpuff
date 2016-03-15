package Treningsdagbok;

public class Interpreter {
    private String[] query;
    Connector connector = new Connector();

    public void Check(String s) {
    	if (s == "exit"){

    	};

        query = s.split(";");
        if(query[0].equals("r")) {
            connector.Read(query[1]);
        }
        else if(query[0].equals("w")) {
            connector.Write(query[1]);
        }
    }
}
