package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return this.board[0].length;
  }

  @Override
  public int getHeight() {
    return this.board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (c > this.getWidth() || r > this.getHeight() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }
    if (this.board[r][c] == 5) {
      return CellType.WALL;
    } else if (this.board[r][c] < 5) {
      return CellType.CLUE;
    } else {
      return CellType.CORRIDOR;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (c > this.getWidth() || r > this.getHeight() || r < 0 || c < 0) {
      throw new IndexOutOfBoundsException();
    }

    if (getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException();
    }
    return this.board[r][c];
  }
}
