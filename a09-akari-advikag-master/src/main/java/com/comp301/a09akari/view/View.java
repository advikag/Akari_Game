package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.Model;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class View implements FXComponent {
  private ClassicMvcController controller;
  private Model model;

  public View(Model model, ClassicMvcController controller) {
    this.controller = controller;
    this.model = model;
  }

  @Override
  public Parent render() {
    VBox layout = new VBox();
    layout.setPrefHeight(600);
    layout.setPrefWidth(600);
    // layout.setAlignment(Pos.CENTER);
    Label label = new Label();

    FXComponent messageview = new MessageView(this.model, this.controller);
    FXComponent puzzleview = new PuzzleView(this.model, this.controller);
    FXComponent controlview = new ControlView(this.model, this.controller);

    layout.getChildren().add(messageview.render());
    layout.getChildren().add(puzzleview.render());
    layout.getChildren().add(controlview.render());

    /*label.setText("Puzzle Number:"+(this.model.getActivePuzzleIndex()));
    label.setFont(Font.font("Helvetica",FontWeight.BOLD,FontPosture.REGULAR,20));
    layout.setAlignment(Pos.TOP_CENTER);
    layout.setSpacing(5);*/

    return layout;

    /*Label newlabel = new Label();

    newlabel.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 20));
    layout.setAlignment(Pos.TOP_CENTER);
    layout.getChildren().add(newlabel);
    layout.setSpacing(5);

    //control panel
    ViewControlImpl control = new ViewControlImpl(this.controller, this.model);
    layout.getChildren().add(control.render());

    //puzzle grid
    ViewPuzzleImpl puzzle = new ViewPuzzleImpl(this.controller,this.model);
    layout.getChildren().add(puzzle.render());*/

    /*if((this.model.isSolved()))
    {
        Label newLabel = new Label("Puzzle Solved!!!!!!");
        layout.getChildren().addAll(newLabel);
    }
    return layout;*/

  }
}
