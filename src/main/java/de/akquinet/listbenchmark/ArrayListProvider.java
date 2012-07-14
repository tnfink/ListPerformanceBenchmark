package de.akquinet.listbenchmark;

import java.util.ArrayList;
import java.util.List;

public class ArrayListProvider<T> implements ListProvider<T> {


    @Override
    public List<T> provideList(final int initial) {
        return new ArrayList<T>(initial);
    }
}
