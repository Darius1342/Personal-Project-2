package application;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class ReadAdd {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader("C:\\Users\\Darius\\Desktop\\application\\add.json"));
    JSONObject jsonObject = (JSONObject)obj;
    String firstName = (String)jsonObject.get("First Name");
    String lastName = (String)jsonObject.get("Last Name");
    String dob = (String)jsonObject.get("Date of Birth");
    String sal = (String)jsonObject.get("Salary");
    String cnp = (String)jsonObject.get("CNP");
    String gender = (String) jsonObject.get("Gender");
    String email = (String) jsonObject.get("Email");
    String doh = (String) jsonObject.get("Date of Hiring");

    public void print()
    {
        System.out.println(firstName +" "+
                lastName +" "+
                dob +" "+
                sal +" "+
                cnp +" "+
                gender +" "+
                email +" "+
                doh);
    }

    public ReadAdd() throws IOException, ParseException {
    }
}
