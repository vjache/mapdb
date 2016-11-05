package org.mapdb.serializer;

import org.mapdb.DataInput2;
import org.mapdb.SerializerBase;

import java.io.IOException;
import java.util.Comparator;

/**
 * Created by vj on 05.11.16.
 */
public abstract class GroupSerializerBase<A> extends SerializerBase<A> implements GroupSerializer<A> {
    public A valueArrayBinaryGet(DataInput2 input, int keysLen, int pos) throws IOException {
        Object keys = valueArrayDeserialize(input, keysLen);
        return valueArrayGet(keys, pos);
//        A a=null;
//        while(pos-- >= 0){
//            a = deserialize(input, -1);
//        }
//        return a;
    }

    public int valueArrayBinarySearch(A key, DataInput2 input, int keysLen, Comparator comparator) throws IOException {
        Object keys = valueArrayDeserialize(input, keysLen);
        return valueArraySearch(keys, key, comparator);
//        for(int pos=0; pos<keysLen; pos++){
//            A from = deserialize(input, -1);
//            int comp = compare(key, from);
//            if(comp==0)
//                return pos;
//            if(comp<0)
//                return -(pos+1);
//        }
//        return -(keysLen+1);
    }

    public Object[] valueArrayToArray(Object vals){
        Object[] ret = new Object[valueArraySize(vals)];
        for(int i=0;i<ret.length;i++){
            ret[i] = valueArrayGet(vals,i);
        }
        return ret;
    }

    public A nextValue(A value){
        throw new UnsupportedOperationException("Next Value not supported");
    }
}
