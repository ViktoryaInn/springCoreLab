package core.loggers;

import core.beans.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;

    public CacheFileLogger(String fileName, int cacheSize){
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    public void destroy(){
        if(!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    private void writeEventsFromCache(){
        cache.forEach(super::logEvent);
    }

    @Override
    public void logEvent(Event event){
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventsFromCache();
            cache.clear();
        }
    }
}
