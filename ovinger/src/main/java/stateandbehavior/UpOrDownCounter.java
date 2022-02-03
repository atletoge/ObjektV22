package stateandbehavior;

public class UpOrDownCounter {

    int end;
    int oppNed;
    int counter;

    public UpOrDownCounter(int start, int end) {
        counter = start;
        this.end = end;
        if (start > end) {
            oppNed = 0;
        }
        if (start < end) {
            oppNed = 1;
        }

    }

    public int getCounter() {
        return counter;
    }

    public boolean count() {
        if (oppNed == 0) {
            if ((counter - 1) >= end) {
                counter--;
                return !(counter == end);
            }
            else {
                return false;
            
            }
        }
        if (oppNed == 1) {
            if ((counter + 1) <= end) {
                counter++;
                return !(counter == end);
            }
            else {
                return false;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        UpOrDownCounter counter = new UpOrDownCounter(5, 9);
        System.out.println(counter.getCounter());
        System.out.println(counter.count());
        System.out.println(counter.getCounter());
    }
}
