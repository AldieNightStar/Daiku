package haxidenti.daiku.reader;

import java.io.EOFException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public interface Reader {
    /**
     * Read one byte
     * @return byte readed
     * @throws haxidenti.daiku.EndException when bytes is ended
     */
    byte read();

    /**
     * Set pointer position to read
     * @param ptr pointer to set to
     */
    void setPointer(int ptr);

    default short readShort() {
        ByteBuffer buf = ByteBuffer.allocate(2);
        buf.put(read());
        buf.put(read());
        return buf.getShort();
    }

    default int readInt() {
        ByteBuffer buf = ByteBuffer.allocate(Integer.BYTES);
        for (int i = 0; i < Integer.BYTES; i++) {
            buf.put(read());
        }
        return buf.getInt();
    }

    default String readString() {
        List<Byte> bytes = new ArrayList<>();
        while (true) {
            byte b = read();
            if (b == 0x0) break;
            bytes.add(b);
        }
        byte[] arrayOfBytes = new byte[bytes.size()];
        for (int i = 0; i < arrayOfBytes.length; i++) {
            arrayOfBytes[i] = bytes.get(i);
        }
        return new String(arrayOfBytes, StandardCharsets.UTF_8);
    }

    default void endPointer() {
        setPointer(Integer.MAX_VALUE);
    }
}
