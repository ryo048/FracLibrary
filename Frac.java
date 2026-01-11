package library.fracLibrary;

public class Frac implements Comparable<Frac>{

    private long a; //numerator
    private long b; //denominator
    public Frac(long a, long b){ // a/b
        if (b == 0) throw new IllegalArgumentException("Denominator cannot be zero.");
        this.a = a;
        this.b = b;
        simplification();
    }

    private void simplification(){ //simplifying fractions
        long gcd = gcd(Math.abs(a), Math.abs(b));
        a /= gcd;
        b /= gcd;
        if(a == 0) b = 1;
        if(b < 0){
            a = -a;
            b = -b;
        }
    }

    private long gcd(long a, long b){ //search for greatest common divisor
        while (b != 0){
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static Frac toFrac(long value){
        return new Frac(value, 1);
    }

    public Frac add(Frac other){ //addition
        if(other.b == this.b) return new Frac(this.a + other.a, this.b);
        long newA = Math.multiplyExact(this.a, other.b) + Math.multiplyExact(other.a, this.b);
        long newB = Math.multiplyExact(this.b, other.b);
        return new Frac(newA, newB);
    }

    public Frac add(long other){ //addition
        return this.add(toFrac(other));
    }

    public Frac sub(Frac other){ //subtraction
        return this.add(other.negate());
    }

    public Frac sub(long other){ //subtraction
        return this.sub(toFrac(other));
    }

    public Frac mul(Frac other){ //multiplication
        long newA = Math.multiplyExact(this.a, other.a);
        long newB = Math.multiplyExact(this.b, other.b);
        return new Frac(newA, newB);
    }

    public Frac mul(long other){ //multiplication
        return this.mul(toFrac(other));
    }

    public Frac div(Frac other){ //division
        if (other.a == 0) throw new ArithmeticException("Cannot divide by zero.");
        return this.mul(other.reciprocal());
    }
    
    public Frac div(long other){ //division
        return this.div(toFrac(other));
    }

    public Frac max(Frac other){ //maximum
        return (this.compareTo(other) >= 0) ? this : other;
    }

    public static Frac max(Frac[] fractions){ //maximum of list
        Frac max = fractions[0];
        for(Frac fraction : fractions){
            if(fraction.compareTo(max) > 0) max = fraction;
        }
        return max;
    }

    public Frac min(Frac other){ //minimum
        return (this.compareTo(other) <= 0) ? this : other;
    }

    public static Frac min(Frac[] fractions){ //minimum of list
        Frac min = fractions[0];
        for(Frac fraction : fractions){
            if(fraction.compareTo(min) < 0) min = fraction;
        }
        return min;
    }

    private static Frac[] mapAll(Frac[] fractions, java.util.function.Function<Frac, Frac> function){
        Frac[] temp = new Frac[fractions.length];
        for(int i = 0; i < fractions.length; i++) temp[i] = function.apply(fractions[i]);
        return temp;
    }

    public static Frac[] copyOf(Frac[] fractions){
        return mapAll(fractions, frac -> new Frac(frac.a, frac.b));
    }

    public double toDecimal(){
        return (double)a / b;
    }

    public static double[] toDecimalAll(Frac[] fractions){
        double[] temp = new double[fractions.length];
        for(int i = 0; i < fractions.length; i++) temp[i] = fractions[i].toDecimal();
        return temp;
    }

    public Frac negate(){
        return new Frac(-a, b);
    }

    public static Frac[] negateAll(Frac[] fractions){
        return mapAll(fractions, frac -> frac.negate());
    }

    public static void swap(Frac[] fractions, int i, int j){
        Frac temp = fractions[i];
        fractions[i] = fractions[j];
        fractions[j] = temp;
    }

    public Frac reciprocal(){
        if(a == 0) throw new ArithmeticException("Cannot take reciprocal of zero.");
        return new Frac(b, a);
    }

    public static Frac[] reciprocalAll(Frac[] fractions){
        return mapAll(fractions, frac -> frac.reciprocal());
    }

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
        return java.util.Objects.hash(a, b);
    }

    @Override
    public String toString(){
        if(a == 0) return "0";
        if(b == 1) return String.valueOf(a);
        return a + "/" + b;
    }
}






