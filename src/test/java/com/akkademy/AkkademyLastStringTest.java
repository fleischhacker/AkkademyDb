package com.akkademy;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import com.akkademy.messages.StringMessage;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by lukasfleischhacker on 20.02.16.
 */
public class AkkademyLastStringTest {
    ActorSystem system = ActorSystem.create();

    @Test
    public void checkLastMessageIfOneMessageIsSent() {
        TestActorRef<AkkademyLastString> actorRef = TestActorRef.create(system, Props.create(AkkademyLastString.class));
        actorRef.tell(new StringMessage("Laser Schelle"), ActorRef.noSender());

        AkkademyLastString akkademyLastString = actorRef.underlyingActor();
        assertEquals(akkademyLastString.lastMessage, "Laser Schelle");
    }

    @Test
    public void checkLastMessageIfTwoMessagesAreSent() {
        TestActorRef<AkkademyLastString> actorRef = TestActorRef.create(system, Props.create(AkkademyLastString.class));
        actorRef.tell(new StringMessage("Laser Schelle"), ActorRef.noSender());
        actorRef.tell(new StringMessage("Laser Kick"), ActorRef.noSender());

        AkkademyLastString akkademyLastString = actorRef.underlyingActor();
        assertEquals(akkademyLastString.lastMessage, "Laser Kick");
    }
}
