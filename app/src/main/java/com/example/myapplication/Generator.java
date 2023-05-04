package com.example.myapplication;

public class Generator { //генерация тайлов и структур (позже)

  public Tile generate () {
    double generated = Math.random();
    if (generated < 0.6){
      return Tile.EMPTY;
    } else if (generated < 0.7){
      return Tile.TREE;
    } else if (generated < 0.8){
      return Tile.STONE;
    } else if (generated < 0.9){
      return Tile.GRASS;
    } else {
      return Tile.BUSH;
    }
  }

}
