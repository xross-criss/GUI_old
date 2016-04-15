package Z_5_1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Hailstone implements Iterable<Integer> {

    private Integer start;

    public Hailstone(Integer start) {
        this.start = start;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new HailstoneIterator();
    }

    private static Integer nextCollatz(Integer a) {
        if (a == null) {
            return null;
        }
        return (a % 2 == 0) ? (a / 2) : (3 * a + 1);
    }

    private class HailstoneIterator implements Iterator<Integer> {

        private Integer cursor;
        private Boolean wasOne = Boolean.FALSE;

        public HailstoneIterator() {
            this.cursor = Hailstone.this.start;
        }

        @Override
        public boolean hasNext() {
            return this.cursor != null && !this.wasOne;
        }

        @Override
        public Integer next() {
            if (this.hasNext()) {
                if (this.cursor == 1) {
                    this.wasOne = Boolean.TRUE;
                }
                int current = this.cursor;
                if (!this.wasOne) {
                    this.cursor = Hailstone.nextCollatz(this.cursor);
                }
                return current;
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}