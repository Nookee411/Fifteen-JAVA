package fithteen.engine.core;

public class Pair<T> {
    T first;
    T second;
    public Pair(T first,T second){
        this.first = first;
        this.second = second;
    }

    public Pair<T> create(T first,T second){
        return  new Pair<T>(first,second);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
