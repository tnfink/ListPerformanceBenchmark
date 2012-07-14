package de.akquinet.listbenchmark;

import java.util.ArrayList;
import java.util.List;

public class ArrayListIgnoreInitProvider<T> implements ListProvider<T> {


    @Override
    public List<T> provideList(final int initial) {
        return new ArrayList<T>();
    }
}
