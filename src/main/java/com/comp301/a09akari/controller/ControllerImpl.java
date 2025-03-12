package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;

public class ControllerImpl implements ClassicMvcController {

  private Model model;
  private CellType cobj = CellType.CORRIDOR;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int index = this.model.getActivePuzzleIndex();
    int size = this.model.getPuzzleLibrarySize() - 1;

    if (index == size) {
      this.model.setActivePuzzleIndex(0);

    } else {
      model.setActivePuzzleIndex(this.model.getActivePuzzleIndex() + 1);
    }
  }

  @Override
  public void clickPrevPuzzle() {
    if (this.model.getActivePuzzleIndex() == 0) {
      this.model.setActivePuzzleIndex(this.model.getPuzzleLibrarySize() - 1);
    } else {
      this.model.setActivePuzzleIndex(this.model.getActivePuzzleIndex() - 1);
    }
  }

  @Override
  public void clickRandPuzzle() {

    int random = (int) ((this.model.getPuzzleLibrarySize()) * (Math.random()));
    if (this.model.getActivePuzzleIndex() != random) {
      this.model.setActivePuzzleIndex(random);
    } else {
      clickRandPuzzle();
    }
  }

  @Override
  public void clickResetPuzzle() {
    this.model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (this.model.getActivePuzzle().getCellType(r, c) == CellType.CORRIDOR) {
      if (this.model.isLamp(r, c)) {
        this.model.removeLamp(r, c);
      } else {
        this.model.addLamp(r, c);
      }
    }
  }
}
