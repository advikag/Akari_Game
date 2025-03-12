package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class MessageView implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public MessageView(Model model, ClassicMvcController controller) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    VBox Vobj = new VBox();
    Label label = new Label("");
    StackPane layout = new StackPane();

    if (this.model.isSolved()) {
      label = new Label("PUZZLE COMPLETE!");
      label.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 25));
      layout.setAlignment(Pos.TOP_CENTER);
    } else {
      label.setText("Puzzle Number:" + (this.model.getActivePuzzleIndex() + 1));
      label.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 30));
      // label.setStyle("-fx-background-color:BLUE");
      layout.setAlignment(Pos.TOP_CENTER);
      // layout.setSpacing(5);
    }

    layout.getChildren().add(label);
    return layout;
  }
}
