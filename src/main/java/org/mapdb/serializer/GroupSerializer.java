package org.mapdb.serializer;

import org.mapdb.DataInput2;
import org.mapdb.DataOutput2;
import org.mapdb.Serializer;

import java.io.IOException;
import java.util.Comparator;

/**
 * Created by jan on 2/29/16.
 */
public interface GroupSerializer<A> extends Serializer<A> {

    A valueArrayBinaryGet(DataInput2 input, int keysLen, int pos) throws IOException;



    int valueArrayBinarySearch(A key, DataInput2 input, int keysLen, Comparator comparator) throws IOException;


    int valueArraySearch(Object keys, A key);

    int valueArraySearch(Object keys, A key, Comparator comparator);

    void valueArraySerialize(DataOutput2 out, Object vals) throws IOException;

    Object valueArrayDeserialize(DataInput2 in, int size) throws IOException;

    A valueArrayGet(Object vals, int pos);

    int valueArraySize(Object vals);

    Object valueArrayEmpty();

    Object valueArrayPut(Object vals, int pos, A newValue);


    Object valueArrayUpdateVal(Object vals, int pos, A newValue);

    Object valueArrayFromArray(Object[] objects);

    Object valueArrayCopyOfRange(Object vals, int from, int to);

    Object valueArrayDeleteValue(Object vals, int pos);

    Object[] valueArrayToArray(Object vals);


    /** returns value+1, or null if there is no bigger value. */
    A nextValue(A value);

}
