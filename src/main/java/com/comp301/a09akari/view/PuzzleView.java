package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class PuzzleView implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.BASELINE_CENTER);

    // Puzzle getIsActive = this.model.getActivePuzzle();
    for (int i = 0; i < this.model.getActivePuzzle().getHeight(); ++i) {
      for (int j = 0; j < this.model.getActivePuzzle().getWidth(); ++j) {
        Button button;
        grid.setStyle(
            "-fx-background-color:MISTYROSE;-fx-border-color:BLACK;-fx-pref-width: 600px; -fx-pref-height: 600px");
        // try to set spacing

        // if cell type is corridor
        if (this.model.getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          button = new Button("");
          button.setStyle(
              "-fx-background-color: INDIANRED;-fx-border-color:BLACK;-fx-text-fill:white; -fx-pref-width: 50px; -fx-pref-height: 50px");
          int ctemp = j;
          int rtemp = i;
          grid.add(button, i, j);
          button.setOnAction((ActionEvent event) -> this.controller.clickCell(rtemp, ctemp));

          if (this.model.isLit(i, j)) {
            button.setStyle(
                "-fx-border-color: black; -fx-background-color: LEMONCHIFFON; -fx-pref-width: 50px; -fx-pref-height: 50px");
          } else {
            button.setStyle(
                "-fx-border-color: black; -fx-background-color: MISTYROSE; -fx-pref-width: 50px; -fx-pref-height: 50px");
          }

          // EDITED UP TO HERE
          if (this.model.isLamp(i, j)) {

            if (this.model.isLampIllegal(i, j)) {
              button.setStyle(
                  "-fx-border-color: black; -fx-background-color: INDIANRED; -fx-font-size: 20px; -fx-pref-width: 50px; -fx-pref-height: 50px ");
              button.setText("\u274C");
            } else {
              button.setStyle(
                  "-fx-background-color: LEMONCHIFFON;-fx-border-color:BLACK; -fx-font-size: 20px; -fx-pref-width: 50px; -fx-pref-height: 50px");
              Image bulb = new Image("light-bulb.png");
              ImageView imageview = new ImageView(bulb);
              imageview.setFitHeight(20);
              imageview.setFitWidth(20);
              button.setGraphic(imageview);
              // button.setText("\uF4A1");
            }
          }
        }

        // edited up to here

        // rough
        // button.setStyle("-fx-background-color: CORNFLOWERBLUE;-fx-border-color:ROYALBLUE;
        // -fx-pref-width: 50px; -fx-pref-height: 50px");
        else if (this.model.getActivePuzzle().getCellType(i, j) == CellType.WALL) {
          button = new Button("");
          button.setStyle(
              "-fx-background-color:SLATEGRAY;-fx-border-color:BLACK; -fx-pref-width: 50px; -fx-pref-height: 50px"); // CHANGED THIS FROM RED TO BLACK
          grid.add(button, i, j);
        } else if (this.model.getActivePuzzle().getCellType(i, j) == CellType.CLUE) {
          button = new Button(Integer.toString(this.model.getActivePuzzle().getClue(i, j)));
          button.setStyle(
              "-fx-background-color:INDIANRED;-fx-border-color:BLACK;-fx-text-fill:white; -fx-pref-width: 50px; -fx-pref-height: 50px");
          // newButton.setText(Integer.toString(getIsActive.getClue(i,j)));
          if (this.model.isClueSatisfied(i, j)) {
            button.setStyle(
                "-fx-background-color: AQUAMARINE;-fx-border-color:BLACK;-fx-pref-width: 50px; -fx-pref-height: 50px"); // EDITED FROM WHITE TO PINK
          }
          grid.add(button, i, j);
        }
      }
    }

    return grid;
  }
}
