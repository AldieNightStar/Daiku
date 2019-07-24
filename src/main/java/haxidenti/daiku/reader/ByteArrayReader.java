package haxidenti.daiku.reader;

import haxidenti.daiku.EndException;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ByteArrayReader implements Reader {
    private byte[] arr;
    private int ptr = 0;

    public ByteArrayReader(byte[] b) {
        arr = b;
    }

    public ByteArrayReader(File f) throws IOException {
        arr = Files.readAllBytes(f.toPath());
    }

    @Override
    public byte read() {
        try {
            return arr[ptr++];
        } catch (Exception e) {
            throw new EndException();
        }
    }

    @Override
    public void setPointer(int ptr) {
        this.ptr = ptr;
    }
}
