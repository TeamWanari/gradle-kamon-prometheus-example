package message;

public class Pong {
    private final long count;
    private final long start;
    private final long end;

    private Pong(Ping pingMsg, long end) {
        this.count = pingMsg.getCount();
        this.start = pingMsg.getStart();
        this.end = end;
    }

    public static Pong from(Ping pingMsg, long end) {
        return new Pong(pingMsg, end);
    }

    public long getCount() {
        return count;
    }

    public long diffInMillis() {
        return end - start;
    }
}
