package core.loggers;

import core.beans.Event;

import java.util.Collection;

public class CombinedEventLogger implements IEventLogger{
    private final Collection<IEventLogger> loggers;

    public CombinedEventLogger(Collection<IEventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for(IEventLogger eventLogger: loggers){
            eventLogger.logEvent(event);
        }
    }

    public Collection<IEventLogger> getLoggers(){
        return loggers;
    }
}
