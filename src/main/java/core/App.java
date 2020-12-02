package core;

import core.beans.Client;
import core.beans.Event;
import core.beans.EventType;
import core.loggers.IEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private IEventLogger defaultLogger;
    private Map<EventType, IEventLogger> loggers;

    App(){}

    App(Client client, IEventLogger eventLogger, Map<EventType, IEventLogger> loggers){
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        //app.client = new Client("1", "John Smith");
        //app.consoleEventLogger = new ConsoleEventLogger();
        Event event = (Event)ctx.getBean("event");
        app.logEvent(event, "Some event for user 1", EventType.INFO);
        app.logEvent(event, "Some event #2 for user 1", EventType.ERROR);
        ctx.close();
    }

    private void logEvent(Event event, String msg, EventType eventType){
        String message = msg.replaceAll(client.getId(), client.getName());
        event.setMsg(message);

        IEventLogger logger = loggers.get(eventType);
        if(logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }
}
