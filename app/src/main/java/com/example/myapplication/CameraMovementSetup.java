package com.example.myapplication;

import static com.example.myapplication.Tile.PLAYER;

public class CameraMovementSetup {
    Setup setup;
    MapData mapData;
    int shiftUp = 0;
    int shiftDown = 0;
    int shiftRight = 0;
    int shiftLeft = 0;
    public CameraMovementSetup(){
        setup = MainActivity.getSetup();
        mapData = setup.getMapData();
    }

    public boolean cameraUp () {
        if (shiftUp == 20){
            return false;
        }
        mapData.playerPos[1]--;
        if (shiftDown == 0) {
            Tile test;
            Boolean isBorder;
            try {
                test = mapData.floor[mapData.topLeftCorner[0]][mapData.topLeftCorner[1] - 1]; //проверяет, находится-ли он на краю карты
                isBorder = false;
            } catch (Exception error) {
                isBorder = true;
            }
            if (!isBorder) {
                setup.moveup();
                return false;
            } else {
                shiftUp++;
                return true;
            }
        } else {
            shiftDown--;
            return true;
        }
    }

    public boolean cameraDown () {
        if (shiftDown == 20){
            return false;
        }
        mapData.playerPos[1]++;
        if (shiftUp == 0) {
            Tile test;
            Boolean isBorder;
            try {
                test = mapData.floor[mapData.bottomRightCorner[0]][mapData.bottomRightCorner[1] + 1];
                isBorder = false;
            } catch (Exception error) {
                isBorder = true;
            }
            if (!isBorder) {
                setup.movedown();
                return false;
            } else {
                shiftDown++;
                return true;
            }
        } else {
            shiftUp--;
            return true;
        }
    }

    public boolean cameraRight () {
        if (shiftRight == 10){
            return false;
        }
        mapData.playerPos[0]++;
        if (shiftLeft == 0) {
            Tile test;
            Boolean isBorder;
            try {
                test = mapData.floor[mapData.bottomRightCorner[0] + 1][mapData.bottomRightCorner[1]];
                isBorder = false;
            } catch (Exception error) {
                isBorder = true;
            }
            if (!isBorder) {
                setup.moveright();
                return false;
            } else {
                shiftRight++;
                return true;
            }
        } else {
            shiftLeft--;
            return true;
        }
    }

    public boolean cameraLeft () {
        if (shiftLeft == 10){
            return false;
        }
        mapData.playerPos[0]--;
        if (shiftRight == 0) {
            Tile test;
            Boolean isBorder;
            try {
                test = mapData.floor[mapData.topLeftCorner[0] - 1][mapData.topLeftCorner[1]];
                isBorder = false;
            } catch (Exception error) {
                isBorder = true;
            }
            if (!isBorder) {
                setup.moveleft();
                return false;
            } else {
                shiftLeft++;
                return true;
            }
        } else {
            shiftRight--;
            return true;
        }
    }

}
