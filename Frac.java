package library.fracLibrary;

import java.util.function.Function;
import java.util.Objects;

public class Frac implements Comparable<Frac>{

    private long a; //numerator
    private long b; //denominator

    /** Constructor for a fraction a/b */
    public Frac(long a, long b){
        if (b == 0) throw new IllegalArgumentException("Denominator cannot be zero.");
        this.a = a;
        this.b = b;
        simplification();
    }

    /** Reduce fraction to simplest form */
    private void simplification(){
        long gcd = gcd(Math.abs(a), Math.abs(b));
        a /= gcd;
        b /= gcd;
        if(a == 0) b = 1; 
        if(b < 0){
            a = -a;
            b = -b;
        }
    }

    /** Compute greatest common divisor of two numbers */
    private long gcd(long a, long b){
        while (b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /** Convert long to fraction */
    public static Frac toFrac(long value){
        return new Frac(value, 1);
    }

    /** Add another fraction */
    public Frac add(Frac other){
        if(other.b == this.b) return new Frac(this.a + other.a, this.b);
        long newA = Math.multiplyExact(this.a, other.b) + Math.multiplyExact(other.a, this.b);
        long newB = Math.multiplyExact(this.b, other.b);
        return new Frac(newA, newB);
    }

    /** Add a long integer */
    public Frac add(long other){
        return this.add(toFrac(other));
    }

    /** Subtract another fraction */
    public Frac sub(Frac other){
        return this.add(other.negate());
    }

    /** Subtract a long integer */
    public Frac sub(long other){
        return this.sub(toFrac(other));
    }

    /** Multiply by another fraction */
    public Frac mul(Frac other){
        long newA = Math.multiplyExact(this.a, other.a);
        long newB = Math.multiplyExact(this.b, other.b);
        return new Frac(newA, newB);
    }

    /** Multiply by a long integer */
    public Frac mul(long other){
        return this.mul(toFrac(other));
    }

    /** Divide by another fraction */
    public Frac div(Frac other){
        if (other.a == 0) throw new ArithmeticException("Cannot divide by zero.");
        return this.mul(other.reciprocal());
    }
    
    /** Divide by a long integer */
    public Frac div(long other){
        return this.div(toFrac(other));
    }

    /** Return maximum of this and another fraction */
    public Frac max(Frac other){
        return (this.compareTo(other) >= 0) ? this : other;
    }

    /** Maximum of an array of fractions */
    public static Frac max(Frac[] fractions){
        Frac max = fractions[0];
        for(Frac fraction : fractions){
            if(fraction.compareTo(max) > 0) max = fraction;
        }
        return max;
    }

    /** Return minimum of this and another fraction */
    public Frac min(Frac other){
        return (this.compareTo(other) <= 0) ? this : other;
    }

    /** Minimum of an array of fractions */
    public static Frac min(Frac[] fractions){
        Frac min = fractions[0];
        for(Frac fraction : fractions){
            if(fraction.compareTo(min) < 0) min = fraction;
        }
        return min;
    }

    /** Map a function over all fractions in an array */
    public static Frac[] mapAll(Frac[] fractions, Function<Frac, Frac> function){
        Frac[] temp = new Frac[fractions.length];
        for(int i = 0; i < fractions.length; i++) temp[i] = function.apply(fractions[i]);
        return temp;
    }

    /** Deep copy of a fraction array */
    public static Frac[] copyOf(Frac[] fractions){
        return mapAll(fractions, frac -> new Frac(frac.a, frac.b));
    }

    /** Convert fraction to decimal */
    public double toDecimal(){
        return (double)a / b;
    }

    /** Convert array of fractions to decimal array */
    public static double[] toDecimalAll(Frac[] fractions){
        double[] temp = new double[fractions.length];
        for(int i = 0; i < fractions.length; i++) temp[i] = fractions[i].toDecimal();
        return temp;
    }

    /** Return negated fraction */
    public Frac negate(){
        return new Frac(-a, b);
    }

    /** Negate all fractions in an array */
    public static Frac[] negateAll(Frac[] fractions){
        return mapAll(fractions, frac -> frac.negate());
    }

    /** Swap two elements in an array */
    public static void swap(Frac[] fractions, int i, int j){
        Frac temp = fractions[i];
        fractions[i] = fractions[j];
        fractions[j] = temp;
    }

    /** Return reciprocal of fraction */
    public Frac reciprocal(){
        if(a == 0) throw new ArithmeticException("Cannot take reciprocal of zero.");
        return new Frac(b, a);
    }

    /** Reciprocal of all fractions in an array */
    public static Frac[] reciprocalAll(Frac[] fractions){
        return mapAll(fractions, frac -> frac.reciprocal());
    }

    // ===============================
    // Comparable and Object overrides
    // ===============================

    @Override
    public int compareTo(Frac other){
        return Long.compare(Math.multiplyExact(this.a, other.b), Math.multiplyExact(other.a, this.b));
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(!(object instanceof Frac)) return false;
        Frac other = (Frac) object;
        return this.a == other.a && this.b == other.b;
    }

    @Override
    public int hashCode(){
        return Objects.hash(a, b);
    }

    @Override
    public String toString(){
        if(a == 0) return "0";
        if(b == 1) return String.valueOf(a);
        return a + "/" + b;
    }
    
}