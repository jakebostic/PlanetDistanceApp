import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import java.text.NumberFormat;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;



public class SSDistanceApp extends Application {
	private ChoiceBox<PlanetValue> startField;
	private ChoiceBox<PlanetValue> endField;
	private TextField distanceField;
	private TextField distanceMilesField;

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Solar System Distance Calculator");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets(25, 25, 25, 25));
		grid.setHgap(10);
		grid.setVgap(10);

		Scene scene = new Scene(grid, 300, 250);

		grid.add(new Label("From Planet: "), 0, 0);
		startField = choiceBox();
		grid.add(startField, 1, 0);

		grid.add(new Label("To Planet: "), 0, 1);
		endField = choiceBox();
		grid.add(endField, 1, 1);

		grid.add(new Label("Distance (AU): "), 0, 2);
		distanceField = new TextField();
		distanceField.setEditable(false);
		grid.add(distanceField, 1, 2);
		
		grid.add(new Label("Distance (Miles): "), 0, 3);
		distanceMilesField = new TextField();
		distanceMilesField.setEditable(false);
		grid.add(distanceMilesField, 1, 3);

		Button calculateBtn = new Button("Calculate");
		calculateBtn.setOnAction(event -> calculateButtonClicked());

		Button exitBtn = new Button("Exit");
		exitBtn.setOnAction(event -> exitButtonClicked());

		HBox buttonBox = new HBox(10);
		buttonBox.getChildren().add(calculateBtn);
		buttonBox.getChildren().add(exitBtn);
		buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
		grid.add(buttonBox, 0, 4, 2, 1);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public ChoiceBox<PlanetValue> choiceBox() {
		ChoiceBox<PlanetValue> cb = new ChoiceBox<>();
		cb.getItems().add(PlanetValue.Sun);
		cb.getItems().add(PlanetValue.Mercury);
		cb.getItems().add(PlanetValue.Venus);
		cb.getItems().add(PlanetValue.Earth);
		cb.getItems().add(PlanetValue.Mars);
		cb.getItems().add(PlanetValue.Jupiter);
		cb.getItems().add(PlanetValue.Saturn);
		cb.getItems().add(PlanetValue.Uranus);
		cb.getItems().add(PlanetValue.Neptune);
		cb.getItems().add(PlanetValue.Pluto);
		cb.setTooltip(new Tooltip("Explore the solar system"));
		return cb;
	}

	public void calculateButtonClicked() {
		Validation v = new Validation();
		String errorMsg = "";
		errorMsg += v.isDuplicate(startField.getSelectionModel().getSelectedItem().getNumVal(),
								  endField.getSelectionModel().getSelectedItem().getNumVal());
		if (errorMsg.isEmpty()) {
			double start = startField.getSelectionModel().getSelectedItem().getNumVal();
			double end = endField.getSelectionModel().getSelectedItem().getNumVal();
			double distance = Math.abs(start - end);
			
			//converts AU to miles
			double distanceInMiles = Math.abs(start - end * 92955807.3);

			NumberFormat distanceAU = NumberFormat.getNumberInstance();
			distanceField.setText(distanceAU.format(distance));
			
			NumberFormat distanceMiles = NumberFormat.getNumberInstance();
			distanceMilesField.setText(distanceMiles.format(distanceInMiles));
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setHeaderText("Duplicate Entries");
			alert.setContentText(errorMsg);
			alert.showAndWait();
		} 
	}

	public void exitButtonClicked() {
		System.exit(0);
	}

	public static void main(String[] args) {
		launch(args);

	}

}
