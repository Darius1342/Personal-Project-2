package application;

import org.json.simple.parser.ParseException;

import java.net.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class Server
{
    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    JSONWrite deleteWrite = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\delete.json");
    JSONWrite editWrite = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\edit.json");
    JSONWrite addWrite = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\add.json");
    // constructor with port
    public Server(int port)
    {

        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // Get IP and store it somewhere
            String IP = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            int srvPort = server.getLocalPort();
            // Getting the IP of a client
            InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            String clientIP = socketAddress.getAddress().getHostAddress();

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            // Database Connectivity goes here + preparation to write to a JSON file every data packet
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/team-project","root","Roflmao1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from team_project");
            JSONWrite writeFromDB = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\tempdb.json");
            ArrayList<DBEmployee> dbArray = new ArrayList<DBEmployee>();

            while(resultSet.next())
            {
                    System.out.println(resultSet.getString("firstName"));
                    String ID = String.valueOf(resultSet.getInt("ID"));
                DBEmployee DBEmp = new DBEmployee(
                        ID,
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("dateOfBirth"),
                        resultSet.getString("email"),
                        resultSet.getString("salary"),
                        resultSet.getString("CNP"),
                        resultSet.getString("gender"),
                        resultSet.getString("dateOfHiring")

                );

                dbArray.add(DBEmp);
            }
            writeFromDB.writeDb(dbArray);


            String line = "";

            // reads message from client until "Over" is sent
            while (!line.equals("Exit"))
            {


                String yourIP ="IP: " + clientIP;
                String serverIP = "ServerIP: "+IP;
                String yourHostName ="Host Name: " + hostName;
                String yourPort = "Port: " + srvPort;
                System.out.println(yourIP);
                System.out.println(serverIP);
                System.out.println(yourHostName);
                System.out.println(yourPort);
                System.out.println("-------------------\n\n");

                try
                {
                    line = in.readUTF();

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
                if(line.equals("#ADD"))
                {

                    addWrite.empty();
                }
                if(line.equals("#EDIT"))
                {

                    editWrite.empty();
                }
                if(line.equals("#DELETE"))
                {

                    deleteWrite.empty();
                }
                if(line.equals("#ADD_CONFIRM"))
                {
                    ReadAdd addConfirm = new ReadAdd();
                    Statement statementAddUpdate = connection.createStatement();
                    Statement statementAddQuery = connection.createStatement();
                    String checkHighestID = "select max(ID) from team_project";
                    ResultSet resultAdd = statementAddQuery.executeQuery(checkHighestID);
                    resultAdd.next();
                    String update = String.format("INSERT INTO team_project(ID,firstName, lastName, dateOfBirth, email, salary, CNP, gender, dateOfHiring) VALUES (%d,'%s'," +
                                    "'%s'," +
                                    "'%s'," +
                                    "'%s','%s','%s','%s','%s');",
                            resultAdd.getInt("max(ID)")+1,
                            addConfirm.firstName,
                            addConfirm.lastName,
                            addConfirm.dob,
                            addConfirm.email,
                            addConfirm.sal,
                            addConfirm.cnp,
                            addConfirm.gender,
                            addConfirm.doh);
                    int result = statementAddUpdate.executeUpdate(update);


                    addWrite.empty();
                }
                if(line.equals("#EDIT_CONFIRM"))
                {
                    ReadEdit editConfirm = new ReadEdit();
                    Statement statementEditUpdate = connection.createStatement();
                    String update = String.format("UPDATE team_project SET firstName = '%s', lastName = '%s', dateOfBirth = '%s', email = '%s', salary = '%s', CNP = '%s', " +
                            "gender = '%s', dateOfHiring = '%s' WHERE ID = %d;",
                            editConfirm.firstName,
                            editConfirm.lastName,
                            editConfirm.dob,
                            editConfirm.email,
                            editConfirm.sal,
                            editConfirm.cnp,
                            editConfirm.gender,
                            editConfirm.doh,
                            Integer.valueOf(editConfirm.id));
                    Integer result = statementEditUpdate.executeUpdate(update);

                    editWrite.empty();

                }
                if(line.equals("#DELETE_CONFIRM"))
                {
                    ReadDelete deleteConfirm = new ReadDelete();
                    Statement statementDeleteUpdate = connection.createStatement();
                    String update = String.format("DELETE FROM team_project where ID = %d",Integer.valueOf(deleteConfirm.id));
                    Integer result = statementDeleteUpdate.executeUpdate(update);

                    deleteWrite.empty();
                }


            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
            connection.close();
        }
        catch(IOException | ParseException | SQLException i)
        {
            System.out.println(i);
        }

    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}