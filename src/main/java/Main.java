import actor.PingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import kamon.Kamon;
import kamon.prometheus.PrometheusReporter;
import message.Tick;

import java.time.Duration;

public class Main {

    private static Duration initialDelay = Duration.ofSeconds(3);
    private static Duration interval = Duration.ofSeconds(1);

    private static ActorSystem system;
    private static ActorRef pingActor;

    public static void main(String[] args) {
        System.out.println("I'm running");
        initializeKamon();
        initializeActors();
        schedulePing();
        System.out.println("=======================");
    }

    private static void initializeKamon() {
        System.out.println("Initializing Kamon...");

        Kamon.addReporter(new PrometheusReporter());

        System.out.println("Done!");
    }

    private static void initializeActors() {
        System.out.println("Initializing actors...");

        system = ActorSystem.create("my-actor-system");
        pingActor = system.actorOf(Props.create(PingActor.class));

        System.out.println("Done!");
    }

    private static void schedulePing() {
        System.out.println("Scheduling ping message...");

        system.scheduler().schedule(initialDelay, interval, pingActor, new Tick(), system.dispatcher(), ActorRef.noSender());

        System.out.println("Done!");
    }
}
