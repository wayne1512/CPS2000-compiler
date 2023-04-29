package frontend;

import exceptions.LineNumberProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileCharacterProvider implements CharacterProvider{

    final RandomAccessFile raf;

    public FileCharacterProvider(String path) throws FileNotFoundException{
        raf = new RandomAccessFile(path, "r");
    }

    @Override
    public Character next(){
        try {
            int n = raf.read();
            if (n >= 0) return (char) n;
            return null;
        } catch (IOException e) {
            //throw it as a runtime exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public long getPointer(){
        try {
            return raf.getFilePointer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rewind(){
        try {
            raf.seek(raf.getFilePointer() - 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException{
        raf.close();
    }

    @Override
    public LineNumberProvider createLineNumberProvider(){
        return new LineNumberProvider(){
            @Override
            public String get(long charPos){

                try {
                    long oldPos = getPointer();

                    raf.seek(0);


                    int lineNumber = 0;

                    long startPosOfCurrentLine = 0;
                    while (raf.getFilePointer() < charPos){
                        lineNumber++;
                        startPosOfCurrentLine = raf.getFilePointer();
                        raf.readLine();
                    }

                    //restore raf
                    raf.seek(oldPos);

                    return String.format("[%d:%d]",lineNumber,charPos-startPosOfCurrentLine);


                } catch (IOException e) {
                    return "position "+charPos;
                }


            }
        };
    }
}
