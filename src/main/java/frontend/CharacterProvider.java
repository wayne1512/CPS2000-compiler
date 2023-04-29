package frontend;

import exceptions.LineNumberProvider;

import java.io.Closeable;

public interface CharacterProvider extends Closeable{
    /**
     * gets the next character, or null if the end has been reached
     *
     * @return next character or null
     */
    Character next();

    long getPointer();

    void rewind();

    LineNumberProvider createLineNumberProvider();
}
