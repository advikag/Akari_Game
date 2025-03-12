package com.comp301.a09akari.view;

import com.comp301.a09akari.SamplePuzzles;
import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// view related operations are not working

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    /*To write the view, you'll need to fill in the start() method in AppLauncher to actually set up and create your UI. Inside the start() method, you should create a Model instance and a Controller instance.
    To instantiate ModelImpl, you need to pass a PuzzleLibrary object to the constructor. Use the puzzle data provided in the SamplePuzzles class and the provided PuzzleLibraryImpl class to construct and populate
    a suitable PuzzleLibrary which can be passed to the model. Add all puzzles from SamplePuzzles to the PuzzleLibrary. In addition, you may choose to encode and include more puzzles if you want to support a larger puzzle library.
     */

    PuzzleLibrary library = new PuzzleLibraryImpl();

    PuzzleImpl puzzle1 = new PuzzleImpl(SamplePuzzles.PUZZLE_01);
    PuzzleImpl puzzle2 = new PuzzleImpl(SamplePuzzles.PUZZLE_02);
    PuzzleImpl puzzle3 = new PuzzleImpl(SamplePuzzles.PUZZLE_03);
    PuzzleImpl puzzle4 = new PuzzleImpl(SamplePuzzles.PUZZLE_04);
    PuzzleImpl puzzle5 = new PuzzleImpl(SamplePuzzles.PUZZLE_05);

    library.addPuzzle(puzzle1);
    library.addPuzzle(puzzle2);
    library.addPuzzle(puzzle3);
    library.addPuzzle(puzzle4);
    library.addPuzzle(puzzle5);

    Model model = new ModelImpl(library);
    ClassicMvcController controller = new ControllerImpl(model);
    View viewer = new View(model, controller);

    Scene scene = new Scene(viewer.render());
    stage.setScene(scene);

    // in order to dd a view
    model.addObserver(
        (Model mod) -> {
          scene.setRoot(viewer.render());
          stage.sizeToScene();
        });

    stage.show();
  }
}
