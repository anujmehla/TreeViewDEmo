package sample;


import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

/*
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
*/
    Label response;
    public void start(Stage myStage)
    {
        myStage.setTitle("Tree View Demo");
        FlowPane rootNode = new FlowPane(10,10);
        rootNode.setAlignment(Pos.CENTER);
        Scene scene= new Scene(rootNode,300,300);
        myStage.setScene(scene);
        response = new Label("No selection");
        TreeItem<String> tiRoot= new TreeItem<String>("Food");
        TreeItem<String> tiFruit=new TreeItem<String>("Fruit");
        TreeItem<String> tiApples=new TreeItem<String>("Apples");
        tiApples.getChildren().add(new TreeItem<String>("Fuji"));
        tiApples.getChildren().add(new TreeItem<String>("Winesap"));
        tiApples.getChildren().add(new TreeItem<String>("Jonathan"));
        tiFruit.getChildren().add(tiApples);
        tiFruit.getChildren().add(new TreeItem<String>("Pears"));
        tiFruit.getChildren().add(new TreeItem<String>("Oranges"));
        tiRoot.getChildren().add(tiFruit);

        TreeItem<String> tiVegetables=new TreeItem<String>("Vegetables");
        tiVegetables.getChildren().add(new TreeItem<String>("Corn"));
        tiVegetables.getChildren().add(new TreeItem<String>("Peas"));
        tiVegetables.getChildren().add(new TreeItem<String>("Broccoli"));
        tiVegetables.getChildren().add(new TreeItem<String>("beans"));
        tiRoot.getChildren().add(tiVegetables);

        TreeItem<String> tiNuts=new TreeItem<String>("Nuts");
        tiNuts.getChildren().add(new TreeItem<String>("Walnuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Peanuts"));
        tiNuts.getChildren().add(new TreeItem<String>("Pecans"));
        tiRoot.getChildren().add(tiNuts);

        TreeView<String> tvFood= new TreeView<String>(tiRoot);
        MultipleSelectionModel<TreeItem<String>> tvSelModel=tvFood.getSelectionModel();
        tvSelModel.selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue, TreeItem<String> newValue) {
                if(newValue!=null)
                {
                    String path =newValue.getValue();
                    TreeItem<String> tmp = newValue.getParent();
                    while (tmp!=null)
                    {
                        path=tmp.getValue()+" -> "+path;
                        tmp=tmp.getParent();
                    }
                    response.setText("Selection is "+newValue.getValue()+"\nComplete path is "+path);
                }
            }
        });
        rootNode.getChildren().addAll(tvFood,response);
        myStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
