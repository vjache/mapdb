package org.mapdb;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public abstract class SerializerBase<A> implements Serializer<A> {
    public int fixedSize() {
        return -1;
    }
    public boolean isTrusted() {
        return false;
    }

    public int compare(A first, A second) {
        return ((Comparable) first).compareTo(second);
    }

    public boolean equals(A first, A second) {
        return Objects.equals(first, second);
    }

    public int hashCode(@NotNull A o, int seed) {
        return DataIO.intHash(o.hashCode() + seed);
    }

    public boolean needsAvailableSizeHint() {
        return false;
    }

    public A deserializeFromLong(long input, int available) throws IOException {
        if (CC.ASSERT && available < 0 || available > 8) {
            throw new AssertionError();
        }
        byte[] b = new byte[available];
        DataIO.putLong(b, 0, input, available);
        return deserialize(new DataInput2.ByteArray(b), available);
    }

    public A clone(A value) throws IOException {
        DataOutput2 out = new DataOutput2();
        serialize(out, value);
        DataInput2 in2 = new DataInput2.ByteArray(out.copyBytes());
        return deserialize(in2, out.pos);
    }
}
