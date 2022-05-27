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
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /* Client Setup */

        // initialize socket and input output streams
        Socket socket            = new Socket("127.0.0.1", 5000);
        DataInputStream  input   = new DataInputStream(System.in);
        DataOutputStream out     = new DataOutputStream(socket.getOutputStream());
        System.out.println("Connected");



        /* MAIN SCENE SETUP */
        stage.setTitle("Database Administration");
        Scene scene = new Scene(new Group());

        Button add = new Button("Add");
        Button edit = new Button("Edit");
        Button delete = new Button("Delete");
        Button exit = new Button("Exit");

        TableView tableView = new TableView();
//--------------------------------------------------------------------------
        TableColumn<Employee, String> firstNameCol = new TableColumn<>("First Name");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        // --------------------------------------------------------------------------

        TableColumn<Employee, String> lastNameCol = new TableColumn<>("Last Name");

        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
//--------------------------------------------------------------------------

        TableColumn<Employee, String> dateOfBirthCol = new TableColumn<>("Date of Birth");

        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        // --------------------------------------------------------------------------

        TableColumn<Employee, String> salaryCol = new TableColumn<>("Salary");

        salaryCol.setCellValueFactory(new PropertyValueFactory<>("salary"));
        // --------------------------------------------------------------------------

        TableColumn<Employee, String> cnpCol = new TableColumn<>("CNP");

        cnpCol.setCellValueFactory(new PropertyValueFactory<>("CNP"));
        // --------------------------------------------------------------------------

        TableColumn<Employee, String> genderCol = new TableColumn<>("Gender");

        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        // --------------------------------------------------------------------------

        TableColumn<Employee, String> emailCol = new TableColumn<>("Email");

        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
//--------------------------------------------------------------------------

        TableColumn<Employee, String> dateOfHiringCol = new TableColumn<>("Date of Hiring");

        dateOfHiringCol.setCellValueFactory(new PropertyValueFactory<>("dateOfHiring"));
//--------------------------------------------------------------------------


        TableColumn<Employee, String> idCol = new TableColumn<>("ID");

        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));



//--------------------------------------------------------------------------
        tableView.getColumns().add(idCol);
        tableView.getColumns().add(firstNameCol);
        tableView.getColumns().add(lastNameCol);
        tableView.getColumns().add(dateOfBirthCol);
        tableView.getColumns().add(salaryCol);
        tableView.getColumns().add(cnpCol);
        tableView.getColumns().add(genderCol);
        tableView.getColumns().add(emailCol);
        tableView.getColumns().add(dateOfHiringCol);

        ArrayList<DBEmployee> arrayDisplay = new ArrayList<DBEmployee>();
        jsonList display = new jsonList("C:\\Users\\Darius\\Desktop\\application\\tempdb.json");
        display.putList(display.path,arrayDisplay);
        for (DBEmployee emp:
             arrayDisplay) {
            tableView.getItems().add((Object)emp);

        }






        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(tableView);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        vbox.getChildren().addAll(add, edit, delete,exit);
        /* MAIN SCENE SETUP END */
        /* ADD TABLE SCENE */

        Scene addScene;
        TextField firstName = new TextField("First Name");
        GridPane.setConstraints(firstName,0,0);
        TextField lastName = new TextField("Last Name");
        GridPane.setConstraints(lastName,1,0);
        TextField dob = new TextField("Date of Birth");
        GridPane.setConstraints(dob,2,0);
        TextField sal = new TextField("Salary");
        GridPane.setConstraints(sal,0,1);
        TextField cnp = new TextField("CNP");
        GridPane.setConstraints(cnp,1,1);
        TextField gender = new TextField("Gender");
        GridPane.setConstraints(gender,2,1);
        TextField email = new TextField("Email");
        GridPane.setConstraints(email,0,2);
        TextField doh = new TextField("Date of Hiring");
        GridPane.setConstraints(doh,1,2);
        Button confirm = new Button("Confirm");
        GridPane.setConstraints(confirm,0,3);
        GridPane settingAdd = new GridPane();
        settingAdd.getChildren().addAll(firstName,lastName,dob,sal,cnp,gender,email,doh,confirm);
        settingAdd.setVgap(10);
        settingAdd.setHgap(10);
        addScene = new Scene(settingAdd);

        /* ADD TABLE SCENE END */

        /* EDIT SCENE */
        Scene editScene;
        TextField editFN = new TextField("First Name");
        GridPane.setConstraints(editFN,0,0);
        TextField editLN = new TextField("Last Name");
        GridPane.setConstraints(editLN,1,0);
        TextField editDob = new TextField("Date of Birth");
        GridPane.setConstraints(editDob,2,0);
        TextField editSal = new TextField("Salary");
        GridPane.setConstraints(editSal,0,1);
        TextField editCnp = new TextField("CNP");
        GridPane.setConstraints(editCnp,1,1);
        TextField editGender = new TextField("Gender");
        GridPane.setConstraints(editGender,2,1);
        TextField editEmail = new TextField("Email");
        GridPane.setConstraints(editEmail,0,2);
        TextField editDoh = new TextField("Date of Hiring");
        GridPane.setConstraints(editDoh,1,2);
        Button editConfirm = new Button("Confirm");
        GridPane.setConstraints(editConfirm,0,3);
        TextField editId = new TextField("ID");
        GridPane.setConstraints(editId,1,3);
        GridPane settingEdit = new GridPane();
        settingEdit.getChildren().addAll(editFN,editLN,editDob,editSal,editCnp,editGender,editEmail,editDoh,editId,editConfirm);
        settingEdit.setVgap(10);
        settingEdit.setHgap(10);
        editScene = new Scene(settingEdit);
        /* EDIT SCENE END */

        /* DELETE SCENE */
        Scene deleteScene;
        TextField delID = new TextField("ID");
        GridPane.setConstraints(delID,1,0);
        Button deleteConfirm = new Button("Delete");
        GridPane.setConstraints(deleteConfirm,1,1);
        GridPane settingDelete = new GridPane();
        settingDelete.getChildren().addAll(delID,deleteConfirm);
        settingDelete.setHgap(10);
        settingDelete.setVgap(10);
        deleteScene = new Scene(settingDelete);
        /* DELETE SCENE END */

        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Add an employee");
                // Set position of second window, related to primary window.
                newWindow.setX(stage.getX());
                newWindow.setY(stage.getY());
                newWindow.setScene(addScene);
                newWindow.show();
                JSONWrite addWrite = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\add.json");
                try {
                    out.writeUTF("#ADD");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                confirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Employee addedEmployee = new Employee(firstName.getText(),
                                lastName.getText(),
                                dob.getText(),
                                email.getText(),
                                sal.getText(),
                                cnp.getText(),
                                gender.getText(),
                                doh.getText()
                        );

                        addWrite.write(addedEmployee);

                        try {
                            out.writeUTF("#ADD_CONFIRM");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        newWindow.close();
                    }
                });
            }
        });



        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage editStage = new Stage();
                editStage.setX(stage.getX());
                editStage.setY(stage.getY());
                editStage.setScene(editScene);
                editStage.show();
                JSONWrite editWrite = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\edit.json");
                try {
                    out.writeUTF("#EDIT");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                editConfirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Employee editEmployee = new Employee(editFN.getText(),
                                editLN.getText(),
                                editDob.getText(),
                                editEmail.getText(),
                                editSal.getText(),
                                editCnp.getText(),
                                editGender.getText(),
                                editDoh.getText()
                        );
                        try {
                            out.writeUTF("#EDIT_CONFIRM");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        editWrite.writeEdit(editId.getText(),editEmployee);
                        editStage.close();
                    }
                });
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                Stage deleteStage = new Stage();
                deleteStage.setY(stage.getY());
                deleteStage.setX(stage.getX());
                deleteStage.setScene(deleteScene);
                deleteStage.show();
                JSONWrite deleteWrite = new JSONWrite("C:\\Users\\Darius\\Desktop\\application\\delete.json");
                try {
                    out.writeUTF("#DELETE");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                deleteConfirm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            out.writeUTF("#DELETE_CONFIRM");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        deleteWrite.writeDelete(delID.getText());
                        deleteStage.close();
                    }
                });
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try
                {
                    out.writeUTF("Exit");
                    input.close();
                    out.close();
                    socket.close();
                    System.out.println("Closed!");
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

                stage.close();
            }
        });

        stage.setScene(scene);

        stage.show();

    }





    public static void main(String[] args) {

        Application.launch(args);
    }
}
