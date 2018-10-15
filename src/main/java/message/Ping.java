package message;

public class Ping {
    private final long count;
    private final long start;

    private Ping(long count, long start) {
        this.count = count;
        this.start = start;
    }

    public static Ping from(long count, long start) {
        return new Ping(count, start);
    }

    public long getCount() {
        return count;
    }

    public long getStart() {
        return start;
    }
}
