# Daiku
Daiku - byteCode interpreter miniframework for Java

# What is for
You can create your own bytecode interpreter in java as easy as breath :)

```java
byte[] b = new byte[]{ // Create our byteCode with operations: 0x1 (print string), 0x2 (print "Other function")
                0x1, 0x2C, 0x2D, 0x2E, 0x0,
                0x2
};
new Daiku() // Create Daiku parser
        .registerOpCode(0x1, r -> System.out.println(r.readString())) // Register print opcode
        .registerOpCode(0x2, r -> System.out.println("Other function")) // Register other opcode
        .run(new ByteArrayReader(b)); // Run our bytes to test how it's works
```
