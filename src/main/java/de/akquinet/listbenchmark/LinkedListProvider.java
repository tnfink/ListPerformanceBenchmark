package de.akquinet.listbenchmark;

import java.util.LinkedList;
import java.util.List;

public class LinkedListProvider implements ListProvider {
    @Override
    public List provideList(final int initial) {
        return new LinkedList();
    }
}
