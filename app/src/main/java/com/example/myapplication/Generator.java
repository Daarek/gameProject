package com.example.myapplication;

public class Generator {

  public int generate () {
    double generated = Math.random();
    if (generated < 0.6){
      return 0; //empty tile
    } else if (generated < 0.7){
      return 1; //tree
    } else if (generated < 0.8){
      return 2; //stone
    } else if (generated < 0.9){
      return 3; //grass
    } else if (generated < 1) {
      return 4;//bush
    } /* else {
      //обработать ошибку генератора
    }*/
    return 0;
  }

}
