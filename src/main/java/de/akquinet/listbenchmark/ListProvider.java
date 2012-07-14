package de.akquinet.listbenchmark;

import java.util.List;

public interface ListProvider<T> {
    List<T> provideList(int initial);
}
