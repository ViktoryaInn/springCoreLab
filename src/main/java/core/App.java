package core;

import core.beans.Client;
import core.beans.Event;
import core.loggers.IEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private IEventLogger eventLogger;

    App(){}

    App(Client client, IEventLogger eventLogger){
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args){
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        //app.client = new Client("1", "John Smith");
        //app.consoleEventLogger = new ConsoleEventLogger();
        Event event = (Event)ctx.getBean("event");
        app.logEvent(event, "Some event for user 1");
        ctx.close();
    }

    private void logEvent(Event event, String msg){
        String message = msg.replaceAll(client.getId(), client.getName());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
