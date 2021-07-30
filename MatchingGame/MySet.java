package MatchingGame;


import java.util.ArrayList;

class MySet<E> implements SetInterface<E> {

    ArrayList<E> it = new ArrayList<>();

    public String print() {
        String s = "[";
        for (int x = 0; x < it.size(); x++) {
            s += it.get(x);
            if (it.size() - 1 != x) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }

    @Override
    public boolean add(E o) {

        for (int x = 0; x < it.size(); x++) {
            if (it.get(x) == o) {
                return false;
            }
        }
        it.add(o);
        return true;
    }

    @Override
    public void clear() {
        it.clear();
    }

    @Override
    public boolean contains(E o) {
        for (int x = 0; x < it.size(); x++) {
            if (o == it.get(x)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        if (it.size() == 0) {
            return true;
        } else return false;
    }


    @Override
    public boolean remove(E o) {

        if(it.contains(o)){
            it.remove(o);
            return true;
        } else return false;
    }

    @Override
    public int size() {
        return it.size();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }
}

