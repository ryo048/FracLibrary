package FracLibrary;

public class FracLibrary implements Comparable<FracLibrary>{

    private long a; //numerator
    private long b; //denominator
    public FracLibrary(long a, long b){ // a/b
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

    public static FracLibrary toFrac(long value){
        return new FracLibrary(value, 1);
    }

    public FracLibrary add(FracLibrary other){ //addition
        if(other.b == this.b) return new FracLibrary(this.a + other.a, this.b);
        long newA = Math.multiplyExact(this.a, other.b) + Math.multiplyExact(other.a, this.b);
        long newB = Math.multiplyExact(this.b, other.b);
        return new FracLibrary(newA, newB);
    }

    public FracLibrary add(long other){ //addition
        return this.add(toFrac(other));
    }

    public FracLibrary sub(FracLibrary other){ //subtraction
        return this.add(other.negate());
    }

    public FracLibrary sub(long other){ //subtraction
        return this.sub(toFrac(other));
    }

    public FracLibrary mul(FracLibrary other){ //multiplication
        long newA = Math.multiplyExact(this.a, other.a);
        long newB = Math.multiplyExact(this.b, other.b);
        return new FracLibrary(newA, newB);
    }

    public FracLibrary mul(long other){ //multiplication
        return this.mul(toFrac(other));
    }

    public FracLibrary div(FracLibrary other){ //division
        if (other.a == 0) throw new ArithmeticException("Cannot divide by zero.");
        return this.mul(other.reciprocal());
    }
    
    public FracLibrary div(long other){ //division
        return this.div(toFrac(other));
    }

    public FracLibrary max(FracLibrary other){ //maximum
        return (this.compareTo(other) >= 0) ? this : other;
    }

    public static FracLibrary max(FracLibrary[] fractions){ //maximum of list
        FracLibrary max = fractions[0];
        for(FracLibrary fraction : fractions){
            if(fraction.compareTo(max) > 0) max = fraction;
        }
        return max;
    }

    public FracLibrary min(FracLibrary other){ //minimum
        return (this.compareTo(other) <= 0) ? this : other;
    }

    public static FracLibrary min(FracLibrary[] fractions){ //minimum of list
        FracLibrary min = fractions[0];
        for(FracLibrary fraction : fractions){
            if(fraction.compareTo(min) < 0) min = fraction;
        }
        return min;
    }

    private static FracLibrary[] mapAll(FracLibrary[] fractions, java.util.function.Function<FracLibrary, FracLibrary> function){
        FracLibrary[] temp = new FracLibrary[fractions.length];
        for(int i = 0; i < fractions.length; i++) temp[i] = function.apply(fractions[i]);
        return temp;
    }

    public static FracLibrary[] copyOf(FracLibrary[] fractions){
        return mapAll(fractions, frac -> new FracLibrary(frac.a, frac.b));
    }

    public double toDecimal(){
        return (double)a / b;
    }

    public static double[] toDecimalAll(FracLibrary[] fractions){
        double[] temp = new double[fractions.length];
        for(int i = 0; i < fractions.length; i++) temp[i] = fractions[i].toDecimal();
        return temp;
    }

    public FracLibrary negate(){
        return new FracLibrary(-a, b);
    }

    public static FracLibrary[] negateAll(FracLibrary[] fractions){
        return mapAll(fractions, frac -> frac.negate());
    }

    public static void swap(FracLibrary[] fractions, int i, int j){
        FracLibrary temp = fractions[i];
        fractions[i] = fractions[j];
        fractions[j] = temp;
    }

    public FracLibrary reciprocal(){
        if(a == 0) throw new ArithmeticException("Cannot take reciprocal of zero.");
        return new FracLibrary(b, a);
    }

    public static FracLibrary[] reciprocalAll(FracLibrary[] fractions){
        return mapAll(fractions, frac -> frac.reciprocal());
    }

    @Override
    public int compareTo(FracLibrary other){
        return Long.compare(Math.multiplyExact(this.a, other.b), Math.multiplyExact(other.a, this.b));
    }

    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(!(object instanceof FracLibrary)) return false;
        FracLibrary other = (FracLibrary) object;
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






