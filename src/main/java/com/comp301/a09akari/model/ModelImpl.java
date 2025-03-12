package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  // int row;
  // int column;
  private PuzzleLibrary library;
  private int active = 0;
  private int[][] locations;
  private List<ModelObserver> activeObservers = new ArrayList<>();

  public ModelImpl(PuzzleLibrary library) {
    if (library == null) {
      throw new IllegalArgumentException();
    }

    this.library = library;
    setActivePuzzleIndex(active);
    locations = new int[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
  }

  @Override
  public void addLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else if (r < 0
        || c < 0
        || r >= getActivePuzzle().getHeight()
        || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    locations[r][c] = 1;
    notifyObservers();
  }

  @Override
  public void removeLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else if (r < 0
        || c < 0
        || r >= getActivePuzzle().getHeight()
        || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    locations[r][c] = 0;
    notifyObservers();
  }

  @Override
  public boolean isLit(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else if (r < 0
        || c < 0
        || r >= getActivePuzzle().getHeight()
        || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (isLamp(r, c)) {
      return true;
    }

    for (int i = (r - 1); i >= 0; --i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }

    for (int i = r + 1; i < getActivePuzzle().getHeight(); ++i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }

    for (int i = (c - 1); i >= 0; --i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }

    for (int i = c + 1; i < getActivePuzzle().getWidth(); ++i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    return false;
  }

  // rough
  @Override
  public boolean isLamp(int r, int c) {
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException();
    } else if (r < 0
        || c < 0
        || r >= getActivePuzzle().getHeight()
        || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (locations[r][c] == 1) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (!isLamp(r, c)) {
      throw new IllegalArgumentException();
    } else if (r < 0
        || c < 0
        || r >= getActivePuzzle().getHeight()
        || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }

    for (int i = (r - 1); i >= 0; --i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }

    for (int i = r + 1; i < getActivePuzzle().getHeight(); ++i) {
      if (getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(i, c)) {
        return true;
      }
    }

    for (int i = (c - 1); i >= 0; --i) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }

    for (int i = c + 1; i < getActivePuzzle().getWidth(); i++) {
      if (getActivePuzzle().getCellType(r, i) != CellType.CORRIDOR) {
        break;
      }
      if (isLamp(r, i)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return this.library.getPuzzle(getActivePuzzleIndex());
  }

  @Override
  public int getActivePuzzleIndex() {
    return this.active;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException();
    }
    this.active = index;
    this.locations = new int[getActivePuzzle().getHeight()][getActivePuzzle().getWidth()];
    notifyObservers();
  }

  @Override
  public int getPuzzleLibrarySize() {
    return this.library.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < getActivePuzzle().getHeight(); ++i) {
      for (int j = 0; j < getActivePuzzle().getWidth(); ++j) {
        if (locations[i][j] == 1) {
          locations[i][j] = 0;
        }
      }
    }
    notifyObservers();
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < getActivePuzzle().getHeight(); ++i) {
      for (int j = 0; j < getActivePuzzle().getWidth(); ++j) {

        if (getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
          if (!isLit(i, j)) {
            return false;
          }
          if (isLamp(i, j)) {
            if (isLampIllegal(i, j)) {
              return false;
            }
          }
        }

        if (getActivePuzzle().getCellType(i, j) == CellType.CLUE) {

          if (!isClueSatisfied(i, j)) {
            return false;
          }
        }

        if (getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR
            && isLamp(i, j)
            && isLampIllegal(i, j)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }

    int number = getActivePuzzle().getClue(r, c);
    int counter = 0;

    if (r + 1 < getActivePuzzle().getHeight()
        && getActivePuzzle().getCellType(r + 1, c) == CellType.CORRIDOR
        && isLamp(r + 1, c)) {
      ++counter;
    }
    if (c + 1 < getActivePuzzle().getWidth()
        && getActivePuzzle().getCellType(r, c + 1) == CellType.CORRIDOR
        && isLamp(r, c + 1)) {
      ++counter;
    }
    if (c - 1 >= 0
        && getActivePuzzle().getCellType(r, c - 1) == CellType.CORRIDOR
        && isLamp(r, c - 1)) {
      ++counter;
    }

    if (r - 1 >= 0
        && getActivePuzzle().getCellType(r - 1, c) == CellType.CORRIDOR
        && isLamp(r - 1, c)) {
      ++counter;
    }
    if (counter == number) {
      return true;
    } else {
      return false;
    }
  }
  // rough

  @Override
  public void addObserver(ModelObserver observer) {
    this.activeObservers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    this.activeObservers.remove(observer);
  }

  public void notifyObservers() {
    for (ModelObserver observer : this.activeObservers) {
      observer.update(this);
    }
  }
}
