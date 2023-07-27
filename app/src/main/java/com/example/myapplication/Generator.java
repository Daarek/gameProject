package com.example.myapplication;

public class Generator { //генерация ресурсов, тайлов и структур (позже)

  public Tile generate () {
    double generated = Math.random(); //от 0 до 1
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

  public int generateResource(Tile type) {
    double generated = Math.random();
    switch (type){
      case EMPTY: return 0;
      case BUSH: return (int)(generated * 10 + 1);
      case GRASS: return (int)(generated * 10 + 1);
      case TREE: return (int)(generated * 5 + 1);
      case STONE: return (int)(generated * 5 + 1);
    }
    return 0;
  }

}
