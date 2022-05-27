package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;
import org.json.*;


import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class jsonList {
    public String path;
    jsonList(String path)
    {
        this.path = path;
    }

    public void putList(String path, ArrayList<DBEmployee> displayList)
    {
        JSONParser jsonP = new JSONParser();

        try(FileReader reader = new FileReader(path)){
            //Read JSON File
            Object obj = jsonP.parse(reader);
            JSONArray empList = (JSONArray) obj;
            System.out.println(empList);
            //Iterate over emp array
            for(Object data: empList)
            {
                JSONObject data2 = (JSONObject) data;
                DBEmployee dataE = new DBEmployee(
                        (String)data2.get("ID"),
                        (String)data2.get("First Name"),
                        (String)data2.get("Last Name"),
                        (String)data2.get("Date of Birth"),
                        (String)data2.get("Email"),
                        (String)data2.get("Salary"),
                        (String)data2.get("CNP"),
                        (String)data2.get("Gender"),
                        (String)data2.get("Date of Hiring")

                );
                displayList.add(dataE);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}



