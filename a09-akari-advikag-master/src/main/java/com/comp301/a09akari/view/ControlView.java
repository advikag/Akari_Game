package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  ControlView(Model model, ClassicMvcController controller) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    HBox layout = new HBox();

    Button reset = new Button("Reset Puzzle");
    reset.setOnAction((ActionEvent event) -> this.controller.clickResetPuzzle());
    reset.setStyle("-fx-background-color:PLUM;-fx-border-color:BLACK;-fx-pref-height: 150px");
    layout.getChildren().add(reset);

    // previous button
    Button previous = new Button("\u25C0 Previous Puzzle");
    previous.setOnAction((ActionEvent event) -> this.controller.clickPrevPuzzle());
    previous.setStyle("-fx-background-color:PLUM;-fx-border-color:BLACK;-fx-pref-height: 150px");
    layout.getChildren().add(previous);

    // next
    Button nextB = new Button("Next Puzzle \u25B6");
    nextB.setStyle("-fx-background-color:PLUM;-fx-border-color:BLACK;-fx-pref-height: 150px");
    nextB.setOnAction((ActionEvent event) -> this.controller.clickNextPuzzle());
    layout.getChildren().add(nextB);

    // random

    Button ran = new Button("Random Puzzle ");
    ran.setStyle("-fx-background-color:PLUM;-fx-border-color:BLACK;-fx-pref-height: 150px");
    ran.setOnAction((ActionEvent event) -> this.controller.clickRandPuzzle());
    layout.getChildren().add(ran);

    layout.setAlignment(Pos.TOP_CENTER);
    return layout;
  }
}
