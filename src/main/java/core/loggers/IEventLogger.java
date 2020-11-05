package core.loggers;

import core.beans.Event;

public interface IEventLogger {
    void logEvent(Event event);
}
