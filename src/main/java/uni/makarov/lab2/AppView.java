package uni.makarov.lab2;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

//Hierarchy
/*
SplitPane
    AnchorPane
        HBox
            VBox
                CheckBox
                CheckBox
                ...
            VBox
                ComboBox
                ComboBox
                ...
        VBox
            HBox
                ToggleButton
                ToggleButton
                ToggleButton
            HBox
                Button
                Button
                Button
    VBox
        Label
        TextArea
*/

public class AppView {

    private final AppController controller;
    private final AppModel model;

    private VBox root;

    private MenuItem openFile;

    private CheckBox nameChkB;
    private ComboBox nameComB;

    private CheckBox annotationChkB;
    private ComboBox annotationComB;

    private CheckBox typeChkB;
    private ComboBox typeComB;

    private CheckBox authorChkB;
    private ComboBox authorComB;

    private CheckBox touChkB;
    private ComboBox touComB;

    private CheckBox addressChkB;
    private ComboBox addressComB;

    private ToggleGroup apiToggles;

    private Button searchBtn;
    private Button clearBtn;
    private Button convertBtn;

    private Label foundResultsLabel;
    private TextArea searchResultTextArea;


    AppView(AppController controller, AppModel model){
        this.controller = controller;
        this.model = model;

        initView();
        initActions();
    }

    private void initView(){
        root = new VBox();

        MenuBar menu = new MenuBar();
        Menu fileMenu = new Menu("File");
        openFile = new MenuItem("Open...");

        menu.getMenus().add(fileMenu);
        fileMenu.getItems().add(openFile);

        SplitPane splitPane = new SplitPane();

        AnchorPane leftPane = new AnchorPane();

        HBox xmlVariablesHBox = new HBox();

        VBox chkVBox = new VBox();
        VBox cmbVBox = new VBox();

        nameChkB = new CheckBox("Name");
        nameComB = new ComboBox();
        nameComB.setDisable(true);

        annotationChkB = new CheckBox("Annotation");
        annotationComB = new ComboBox();
        annotationComB.setDisable(true);

        typeChkB = new CheckBox("Type");
        typeComB = new ComboBox();
        typeComB.setDisable(true);

        authorChkB = new CheckBox("Author");
        authorComB = new ComboBox();
        authorComB.setDisable(true);

        touChkB = new CheckBox("Terms of Usage");
        touComB = new ComboBox();
        touComB.setDisable(true);

        addressChkB = new CheckBox("Address");
        addressComB = new ComboBox();
        addressComB.setDisable(true);

        chkVBox.getChildren().addAll(nameChkB, annotationChkB, typeChkB, authorChkB, touChkB, addressChkB);
        cmbVBox.getChildren().addAll(nameComB, annotationComB, typeComB, authorComB, touComB, addressComB);

        chkVBox.setSpacing(30);
        cmbVBox.setSpacing(22);

        xmlVariablesHBox.getChildren().addAll(chkVBox, cmbVBox);
        xmlVariablesHBox.setAlignment(Pos.CENTER_LEFT);
        xmlVariablesHBox.setSpacing(50);

        leftPane.getChildren().addAll(xmlVariablesHBox);
        AnchorPane.setTopAnchor(xmlVariablesHBox, 50.0);
        AnchorPane.setLeftAnchor(xmlVariablesHBox, 50.0);

        apiToggles = new ToggleGroup();

        VBox controlsVBox = new VBox();

        HBox togglesHBox = new HBox();
        HBox buttonsHBox = new HBox();

        ToggleButton domBtn = new ToggleButton("DOM");
        ToggleButton saxBtn = new ToggleButton("SAX");
        ToggleButton xpathBtn = new ToggleButton("XPath");

        domBtn.setToggleGroup(apiToggles);
        saxBtn.setToggleGroup(apiToggles);
        xpathBtn.setToggleGroup(apiToggles);

        domBtn.setUserData(apiType.DOM);
        saxBtn.setUserData(apiType.SAX);
        xpathBtn.setUserData(apiType.XPATH);

        togglesHBox.getChildren().addAll(domBtn, saxBtn, xpathBtn);
        togglesHBox.setSpacing(30);

        searchBtn = new Button("Search");
        clearBtn = new Button("Clear");
        convertBtn = new Button("Convert to HTML");

        buttonsHBox.getChildren().addAll(searchBtn, clearBtn, convertBtn);
        buttonsHBox.setSpacing(30);

        controlsVBox.getChildren().addAll(togglesHBox, buttonsHBox);
        controlsVBox.setSpacing(50);

        leftPane.getChildren().add(controlsVBox);

        AnchorPane.setLeftAnchor(controlsVBox, 50.0);
        AnchorPane.setBottomAnchor(controlsVBox, 50.0);

        VBox rightPane = new VBox();

        foundResultsLabel = new Label("????????????????: 0");
        searchResultTextArea = new TextArea();

        rightPane.getChildren().addAll(foundResultsLabel, searchResultTextArea);
        VBox.setVgrow(searchResultTextArea, Priority.ALWAYS);

        splitPane.getItems().addAll(leftPane, rightPane);
        SplitPane.setResizableWithParent(leftPane, false);
        SplitPane.setResizableWithParent(rightPane, false);

        leftPane.maxWidthProperty().bind(rightPane.widthProperty());

        root.getChildren().addAll(menu, splitPane);
        VBox.setVgrow(splitPane, Priority.ALWAYS);
    }

    private void initActions(){
        openFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open XML File");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));

            File file = fileChooser.showOpenDialog(new Stage());
            if(file == null || !file.exists()) {
                foundResultsLabel.setText("FILE NOT FOUND!");
                return;
            }
            controller.setXMLFile(file);

            try {
                setComboBox();
            } catch (IllegalArgumentException e){
                e.printStackTrace();
            }
        });

        nameChkB.setOnAction(event -> nameComB.setDisable(!nameChkB.isSelected()));

        annotationChkB.setOnAction(event -> annotationComB.setDisable(!annotationChkB.isSelected()));

        typeChkB.setOnAction(event -> typeComB.setDisable(!typeChkB.isSelected()));

        authorChkB.setOnAction(event -> authorComB.setDisable(!authorChkB.isSelected()));

        touChkB.setOnAction(event -> touComB.setDisable(!touChkB.isSelected()));

        addressChkB.setOnAction(event -> addressComB.setDisable(!addressChkB.isSelected()));

        searchBtn.setOnAction(event -> {
            try{
                controller.changeAPI((apiType) apiToggles.getSelectedToggle().getUserData());
            } catch (NullPointerException e){
                foundResultsLabel.setText("API NOT PICKED!");
                return;
            }
            ArrayList<String> searchAttributes = getSearchAttributes();
            if (searchAttributes != null) {
                ArrayList<Resource> searchResultArr = controller.searchRequest(searchAttributes);
                if(searchResultArr != null)
                showResults(searchResultArr);
            }
        });

        clearBtn.setOnAction(event -> {
            searchResultTextArea.setText("");
            foundResultsLabel.setText("????????????????: ");
        });

        convertBtn.setOnAction(event -> controller.convertRequest());
    }

    ArrayList<String> getSearchAttributes(){
        ArrayList<String> searchAttributes = new ArrayList<>();

        try {
            if (!nameComB.isDisabled()) {
                searchAttributes.add(nameComB.getValue().toString());
            } else {
                searchAttributes.add("");
            }

            if (!annotationComB.isDisabled()) {
                searchAttributes.add(annotationComB.getValue().toString());
            } else {
                searchAttributes.add("");
            }

            if (!typeComB.isDisabled()) {
                searchAttributes.add(typeComB.getValue().toString());
            } else {
                searchAttributes.add("");
            }

            if (!authorComB.isDisabled()) {
                searchAttributes.add(authorComB.getValue().toString());
            } else {
                searchAttributes.add("");
            }

            if (!touComB.isDisabled()) {
                searchAttributes.add(touComB.getValue().toString());
            } else {
                searchAttributes.add("");
            }

            if (!addressComB.isDisabled()) {
                searchAttributes.add(addressComB.getValue().toString());
            } else {
                searchAttributes.add("");
            }
        } catch (NullPointerException e){
            foundResultsLabel.setText("One of the attributes is not chosen!");
            return null;
        }
        return searchAttributes;
    }

    private void showResults(ArrayList<Resource> searchResultsArr){
        foundResultsLabel.setText("????????????????: " + searchResultsArr.size());
        StringBuilder stringBuilder = new StringBuilder("----------------------------------------\n");
        for (Resource resource : searchResultsArr) {
            ArrayList<String> resourceAttributes = resource.getAttributes();
            stringBuilder.append("Name: ").append(resourceAttributes.get(0)).append("\n");
            stringBuilder.append("Annotation: ").append(resourceAttributes.get(1)).append("\n");
            stringBuilder.append("Type: ").append(resourceAttributes.get(2)).append("\n");
            stringBuilder.append("Author: ").append(resourceAttributes.get(3)).append("\n");
            stringBuilder.append("Terms of Use: ").append(resourceAttributes.get(4)).append("\n");
            stringBuilder.append("Address: ").append(resourceAttributes.get(5)).append("\n");
            stringBuilder.append("----------------------------------------\n");
        }
        searchResultTextArea.setText(stringBuilder.toString());
    }

    private void setComboBox(){
        purgeComboBox();
        ArrayList<ArrayList<String>> xmlAttributes = controller.getAttributes();
        nameComB.getItems().addAll(xmlAttributes.get(0));
        annotationComB.getItems().addAll(xmlAttributes.get(1));
        typeComB.getItems().addAll(xmlAttributes.get(2));
        authorComB.getItems().addAll(xmlAttributes.get(3));
        touComB.getItems().addAll(xmlAttributes.get(4));
        addressComB.getItems().addAll(xmlAttributes.get(5));
    }

    private void purgeComboBox(){
        ArrayList<ComboBox> comboBoxes = new ArrayList<>();
        comboBoxes.add(nameComB);
        comboBoxes.add(annotationComB);
        comboBoxes.add(typeComB);
        comboBoxes.add(authorComB);
        comboBoxes.add(touComB);
        comboBoxes.add(addressComB);
        for (ComboBox comboBox : comboBoxes) {
            while (true) {
                try {
                    comboBox.getItems().remove(0);
                } catch (IndexOutOfBoundsException e){
                    break;
                }
            }
        }
    }

    public Parent asParent(){
        return root;
    }
}
