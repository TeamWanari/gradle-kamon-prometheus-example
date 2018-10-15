package actor;

import akka.actor.AbstractActor;
import kamon.Kamon;
import message.Ping;
import message.Pong;

public class PongActor extends AbstractActor {
    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Ping.class, this::handlePing)
            .build();
    }

    private void handlePing(Ping msg) {
        Kamon.counter("manual.log.pong.actor.ping.message.received").increment();
        System.out.println(msg.getCount() + " - Got PING");
        System.out.println(msg.getCount() + " - Sending PONG");
        sender().tell(Pong.from(msg, System.currentTimeMillis()), getSelf());
    }
}
