package fileParsing;

public class Tuple<T, R> {
    private final T t;
    private final R r;

    public Tuple(T t, R r) {
        this.t = t;
        this.r = r;
    }

    public T getT() {
        return t;
    }

    public R getR() {
        return r;
    }
}
