package fithteen.engine.memento;

import java.util.Arrays;

public class Memento implements Cloneable{
     final private int[][] state;
     public Memento(int[][] state){
         this.state = Arrays.stream(state)
                 .map(int[]::clone)
                 .toArray(int[][]::new);
     }

     public int[][] getState(){
         return this.state;
     }


    @Override
    protected Memento clone() throws CloneNotSupportedException {
        Memento clone = (Memento) super.clone();
        return new Memento(state.clone());
    }

}
