package fithteen.engine.memento;

public class Memento implements Cloneable{
     final private int[][] state;
     public Memento(int[][] state){
         this.state = state.clone();
     }

     public int[][] getState(){
         return this.state;
     }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Memento(state.clone());
    }
}
