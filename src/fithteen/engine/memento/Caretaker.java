package fithteen.engine.memento;

import java.util.EmptyStackException;
import java.util.Stack;

public class Caretaker {
    Stack<Memento> mementos;

    public Caretaker() {
        this.mementos = new Stack<>();
    }

    public Memento load() {
        if(mementos.size()!=0)
            return mementos.pop();
        else
            throw new EmptyStackException();
    }

    public void save(Memento memento){
        try {
            mementos.push((Memento) memento.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public void refresh(){
        mementos = new Stack<>();
    }
}
