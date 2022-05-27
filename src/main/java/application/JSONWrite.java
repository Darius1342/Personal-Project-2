package application;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.List;
import org.json.*;
public class JSONWrite {
    String path;
    JSONObject json = new JSONObject();

    JSONWrite(String path)
    {
        this.path = path;
    }

    public void write(Employee emp)
    {
        try {
            json.put("First Name",emp.firstName);
            json.put("Last Name",emp.lastName);
            json.put("Date of Birth",emp.dateOfBirth);
            json.put("Salary",emp.salary);
            json.put("CNP",emp.CNP);
            json.put("Gender",emp.gender);
            json.put("Email",emp.email);
            json.put("Date of Hiring",emp.dateOfHiring);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeDb(List<DBEmployee> Demp)
    {

        JSONArray finalItem = new JSONArray();
        for (DBEmployee emp:
             Demp) {
            JSONObject json2 = new JSONObject();
            json2.put("ID",emp.ID);
            json2.put("First Name",emp.firstName);
            json2.put("Last Name",emp.lastName);
            json2.put("Date of Birth",emp.dateOfBirth);
            json2.put("Salary",emp.salary);
            json2.put("CNP",emp.CNP);
            json2.put("Gender",emp.gender);
            json2.put("Email",emp.email);
            json2.put("Date of Hiring",emp.dateOfHiring);
            finalItem.put(json2);

                    }
            try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
                out.write(finalItem.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void writeEdit(String id,Employee emp)
    {
        json.put("ID",id);
        write(emp);

    }

    public void writeDelete(String id)
    {
        json.put("ID",id);
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            out.write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void empty()
    {
        while(json.length()>0)
            json.remove(json.keys().next());
    }

}
