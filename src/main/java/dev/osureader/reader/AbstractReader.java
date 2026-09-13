package dev.osureader.reader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class that provides methods
 * for reading binary structures of .db
 * files of the stable osu! client
 *
 * @param <T> the type of object returned by the read() method
 **/

public abstract class AbstractReader<T> {
    /**
     * А variable intended to store the path to the .db file
     **/
    protected Path filePath;

    /**
     * Creates a new instance of the reader with the specified file path
     * @param filePath the path to the .db file to be read
     */
    protected AbstractReader(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the path to the .db file associated with this reader
     * @return the file path
     */
    public Path getFilePath() {
        return filePath;
    }

    /**
     * Sets the path to the .db file to be read
     * @param filePath the new file path
     */
    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Decodes a ULEB128 number
     * that stores the length of the following string.
     * @param buffer the buffer to read from
     * @return the int value of length of the following string
     */
    protected int readULEB128(ByteBuffer buffer) {
        int result = 0;
        int shift = 0;
        while (true) {
            byte b = buffer.get();
            result |= (b & 0x7F) << shift;
            if ((b & 0x80) == 0) break;
            shift += 7;
        }
        return result;
    }

    /**
     * Reads a set of int-float pairs encoded with
     * type markers (0x08 — int marker, 0x0C — float marker).
     * Format: pair count (int), followed by the pairs themselves
     * as [marker][int][marker][float]
     * @param buffer the buffer to read from
     * @return a map where the key is the int value and the value is the corresponding float
     * @throws IllegalStateException if the type marker preceding the int or float does not match the expected value
     */
    protected Map<Integer, Float> readIntFloatPair(ByteBuffer buffer) {
        int pairsCount = buffer.getInt();

        Map<Integer, Float> pairs = new HashMap<>(pairsCount);

        for (int i = 0; i < pairsCount; i++) {
            byte firstMarker = buffer.get();

            if (firstMarker != 0x08) {
                throw new IllegalStateException("invalid 0x08 int marker " + firstMarker);
            }
            int intPair = buffer.getInt();

            byte secondMarker = buffer.get();
            if (secondMarker != 0x0C) {
                throw new IllegalStateException("invalid 0x0C float " + secondMarker);
            }

            float floatPair = buffer.getFloat();

            pairs.put(intPair, floatPair);
        }

        return pairs;
    }

    /**
     * Reads a set of int-double pairs encoded with
     * type markers (0x08 — int marker, 0x0D — double marker).
     * Format: pair count (int), followed by the pairs themselves
     * as [marker][int][marker][double]
     * @param buffer the buffer to read from
     * @return a map where the key is the int value and the value is the corresponding double
     * @throws IllegalStateException if the type marker preceding the int or double does not match the expected value
     */
    protected Map<Integer, Double> readIntDoublePair(ByteBuffer buffer) {
        int pairsCount = buffer.getInt();

        Map<Integer, Double> pairs = new HashMap<>(pairsCount);

        for (int i = 0; i < pairsCount; i++) {
            byte firstMarker = buffer.get();
            if (firstMarker != 0x08) {
                throw new IllegalStateException("invalid 0x08 int marker " + firstMarker);
            }
            int intPair = buffer.getInt();

            byte secondMarker = buffer.get();
            if (secondMarker != 0x0D) {
                throw new IllegalStateException("invalid 0x0D double " + secondMarker);
            }
            double doublePair = buffer.getDouble();

            pairs.put(intPair, doublePair);
        }

        return pairs;
    }

    /**
     * Reads a string in the format used in osu! .db files:
     * a single marker byte, followed (if the marker indicates a non-empty string)
     * by a ULEB128-encoded length and the UTF-8 encoded string bytes
     * @param buffer the buffer to read from
     * @return the string read, or an empty string if the marker indicates the absence of a string
     * @throws IllegalStateException if the string marker is neither 0x00 (empty string) nor 0x0b (non-empty string)
     */
    protected String readString(ByteBuffer buffer) {
        byte stringMarker = buffer.get();
        if (stringMarker == 0) return "";

        if (stringMarker != 0x0b) {
            throw new IllegalStateException("invalid string marker: " + stringMarker);
        }

        int length = readULEB128(buffer);
        byte[] bytes = new byte[length];
        buffer.get(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Reads a single byte from the buffer and interprets
     * it as a boolean: any non-zero value corresponds to true
     * @param buffer the buffer to read from
     * @return true if the byte read is non-zero, otherwise false
     */
    protected boolean readBoolean(ByteBuffer buffer) {
        return buffer.get() != 0;
    }

    /**
     * Reads and parses the .db file whose path is stored
     * in the filePath field, returning the resulting object
     * @return an object of type T obtained from parsing the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public abstract T read() throws IOException;
}
