package haxidenti.daiku;

import haxidenti.daiku.reader.Reader;

import java.util.HashMap;
import java.util.function.Consumer;

public class Daiku {
    private HashMap<Byte, Consumer<Reader>> opCodes = new HashMap<>();

    public Daiku registerOpCode(int opCode, Consumer<Reader> func) {
        opCodes.put((byte) opCode, func);
        return this;
    }

    public void run(Reader reader) {
        while (true) {
            try {
                byte opCode = reader.read();
                Consumer<Reader> func = opCodes.get(opCode);
                if (func == null) continue;
                func.accept(reader);
            } catch (EndException e) {
                return;
            }
        }
    }
}
