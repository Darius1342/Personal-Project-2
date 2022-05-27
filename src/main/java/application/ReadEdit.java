package application;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class ReadEdit {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader("C:\\Users\\Darius\\Desktop\\application\\edit.json"));
    JSONObject jsonObject = (JSONObject)obj;
    String firstName = (String)jsonObject.get("First Name");
    String lastName = (String)jsonObject.get("Last Name");
    String dob = (String)jsonObject.get("Date of Birth");
    String sal = (String)jsonObject.get("Salary");
    String cnp = (String)jsonObject.get("CNP");
    String gender = (String) jsonObject.get("Gender");
    String email = (String) jsonObject.get("Email");
    String doh = (String) jsonObject.get("Date of Hiring");
    String id = (String) jsonObject.get("ID");



    public void print()
    {
        System.out.println(firstName +" "+
                lastName +" "+
                dob +" "+
                sal +" "+
                cnp +" "+
                gender +" "+
                email +" "+
                doh+" "+
                id);
    }
    public ReadEdit() throws IOException, ParseException {
    }
}
