package actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import kamon.Kamon;
import message.Ping;
import message.Pong;
import message.Tick;

public class PingActor extends AbstractActor {

    private final ActorRef pongActor = getContext().actorOf(Props.create(PongActor.class));
    private int count = 0;

    @Override
    public Receive createReceive() {
        return receiveBuilder()
            .match(Tick.class, this::handleTick)
            .match(Pong.class, this::handlePong)
            .build();
    }

    private void handleTick(Tick tick) {
        Kamon.counter("manual.log.ping.actor.tick.message.received").increment();
        System.out.println(count + " - Sending PING");
        pongActor.tell(Ping.from(count, System.currentTimeMillis()), getSelf());
    }

    private void handlePong(Pong msg) {
        Kamon.counter("manual.log.ping.actor.pong.message.received").increment();
        System.out.println(msg.getCount() + " - Got PONG");
        System.out.println(msg.getCount() + " - Diff was " + msg.diffInMillis() + "ms");
        System.out.println("=======================");
        count++;
    }
}
