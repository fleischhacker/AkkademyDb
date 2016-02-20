package com.akkademy;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.pf.ReceiveBuilder;
import com.akkademy.messages.StringMessage;

/**
 * Created by lukasfleischhacker on 20.02.16.
 */
public class AkkademyLastString extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(),this);
    protected String lastMessage = null;

    private AkkademyLastString() {
        receive (
                ReceiveBuilder.match( StringMessage.class, message -> {
                    log.info("Eduard Laser: {}", message.getMessage());
                      lastMessage = message.getMessage();
                  }).matchAny( o ->
                                log.info("received unknown message {}", o)
                ).build()
        );
    }
}
