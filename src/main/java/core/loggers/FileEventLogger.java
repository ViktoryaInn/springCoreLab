package core.loggers;

import core.beans.Event;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.IOException;

public class FileEventLogger implements IEventLogger{
    private File file;
    private String fileName;

    public FileEventLogger(String fileName){
        this.fileName = fileName;
    }

    public void init() throws IOException {
        file = new File(fileName);
        if(file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("Can't write to file " + fileName);
        }else if(!file.exists()){
            file.createNewFile();
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
