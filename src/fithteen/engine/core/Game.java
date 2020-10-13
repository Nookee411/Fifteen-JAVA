package fithteen.engine.core;

import fithteen.engine.memento.Caretaker;
import fithteen.engine.memento.Memento;

import java.util.Random;

public class Game {
    private int[][] field;
    private final Caretaker saves;
    private int turns;

    public Game(int size){
        this.field = new int[size][size];
        for(int i =0;i<size*size;i++){
            field[i/size][i%size] = i;
        }
        this.saves = new Caretaker();
        this.turns = 0;
    }

    public int getTurns() {
        return turns;
    }

    public int[][] getField() {
        return field;
    }

    public int getValueFromPosition(int i,int j){
        return field[i][j];
    }

    public int getSize(){
        return field.length;
    }

    public Pair<Integer> getPositionFromValue(int value){
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if(field[i][j]==value)
                    return new Pair<Integer>(i,j);
            }
        }
        return new Pair<Integer>(-1,-1);
    }

    public Boolean makeTurn(Integer value){

        turns++;
        Pair<Integer> coord =getPositionFromValue(value);
        int i = coord.first;
        int j = coord.second;
        //Check top side
        if(coord.first>0&&field[coord.first-1][coord.second]==0)
            i--;
        else if(coord.first<getSize()-1 && field[coord.first+1][coord.second]==0)
            i++;
        else if(coord.second>0&&field[coord.first][coord.second-1]==0)
            j--;
        else if(coord.second<getSize()-1 && field[coord.first][coord.second+1]==0)
            j++;

        int temp = field[coord.first][coord.second];
        field[coord.first][coord.second] = field[i][j];
        field[i][j] = temp;
        return (i==coord.first&&j==coord.second);
    }

    public boolean isGameEnd(){
        for (int i = 0; i < field.length*field.length; i++) {
            if(field[i/field.length][i%field.length] != i)
                return false;
        }
        return true;
    }

    public void makeRandomTurn(){
        Random rand = new Random();
        Pair<Integer> zeroPosition= getPositionFromValue(0);
        zeroPosition.first =  Math.min(Math.max(zeroPosition.first + 1-rand.nextInt(2),0),
                getSize()-1);
        zeroPosition.second = Math.min(Math.max(zeroPosition.second+ 1-rand.nextInt(2),0),
                getSize()-1);
        int value = getValueFromPosition(zeroPosition.first,zeroPosition.second);
        makeTurn(value);
        turns= 0;

    }

    public void restore(){
        this.field = saves.load().getState();
    }

    public void saveSate(){
        saves.save(new Memento(this.field));
    }

    public void newGame(){
        turns =0;
        saves.refresh();
        setFieldToDefault();
    }

    private void setFieldToDefault(){
        for(int i =0;i<getSize()*getSize();i++){
            field[i/getSize()][i%getSize()] = i;
        }
    }

}
