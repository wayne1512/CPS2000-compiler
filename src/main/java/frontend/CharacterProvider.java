package frontend;

import java.io.Closeable;
import java.io.IOException;

public interface CharacterProvider extends Closeable{
    /**
     * gets the next character, or null if the end has been reached
     *
     * @return next character or null
     */
    Character next();

    long getPointer();

    void rewind();
}
