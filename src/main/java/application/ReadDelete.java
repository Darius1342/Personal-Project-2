package application;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;
public class ReadDelete {
    JSONParser parser = new JSONParser();
    Object obj = parser.parse(new FileReader("C:\\Users\\Darius\\Desktop\\application\\delete.json"));
    JSONObject jsonObject = (JSONObject)obj;
    String id = (String) jsonObject.get("ID");

    public ReadDelete() throws IOException, ParseException {
    }

    public void print()
    {
        System.out.println(id);
    }
}
