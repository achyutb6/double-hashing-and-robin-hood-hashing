package aab180004;

/**
 * @author Achyut Bhandiwad
 * MyInteger class which implements HashCodeInterface which has the hashcode2 funtion
 */

public class MyInteger implements DoubleHashing.HashCodeInterface, Comparable<MyInteger> {
    Integer number;

    public MyInteger(Integer number){
        this.number = number;
    }
    public MyInteger(Long number){
        this.number = Math.toIntExact(number);
    }

    @Override
    public int hashCode2() {
        return 1 + number%9;
    }

    public boolean equals(MyInteger obj) {
        return number.compareTo(obj.number) == 0;
    }

    @Override
    public int compareTo(MyInteger o) {
        return number.compareTo((o.number));
    }
}
