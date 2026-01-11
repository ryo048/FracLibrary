# FracLibrary (English ver.)

`FracLibrary` is a Java library for working with fractions.  
With this library, you can easily perform arithmetic operations on fractions and manipulate arrays of fractions.

*For more information on each feature, see **[Coming soon](github.com)**.*

## Overview

FracLibrary is a Java library for working with fractions.  
It provides a `Frac` class that supports:

- Addition, subtraction, multiplication, and division of fractions (with automatic simplification)
- Negation and reciprocal operations
- Maximum and minimum calculations for single fractions or arrays
- Conversion of single fractions or arrays of fractions to decimal values
- Comparison between fractions (Comparable<Frac> implemented)
- Array operations (swapping, copying, sorting) that work seamlessly with java.util.Arrays

Multiplication operations use Math.multiplyExact to detect overflow and throw an exception if it occurs.  
Thanks to its immutable design, you can safely use it even in complex calculations.

## Example Usage

```java
import library.fracLibrary.Frac;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Frac f1 = new Frac(1, 2);
        Frac f2 = new Frac(3, 4);

        // Arithmetic operations
        Frac add = f1.add(f2); // 5/4
        Frac sub = f1.sub(f2); // -1/4
        Frac mul = f1.mul(f2); // 3/8
        Frac div = f1.div(f2); // 2/3

        // Negation and reciprocal
        Frac neg = f1.negate();     // -1/2
        Frac rec = f1.reciprocal(); // 2

        // Max and Min
        Frac max = Frac.max(new Frac[]{f1, f2}); // 3/4
        Frac min = Frac.min(new Frac[]{f1, f2}); // 1/2

        // Conversion to decimal
        double dec = f1.toDecimal(); // 0.5

        //----------------------------------------------------

        // Using java.util.Arrays
        Frac[] fractions = {f1, f2, Frac.toFrac(-2), Frac.toFrac(0)};


        System.out.println("Fractions array: " + Arrays.toString(fractions));
        // Output: [1/2, 3/4, -2, 0]

        // Swap elements at index 1 and 2
        Frac.swap(fractions, 1, 2);

        System.out.println("Swapped Fractions array: " + Arrays.toString(fractions));
        // Output: [1/2, -2, 3/4, 0]

        // Sort the array
        Arrays.sort(fractions);

        System.out.println("Sorted Fractions array: " + Arrays.toString(fractions));
        // Output: [-2, 0, 1/2, 3/4]

        // Conversion of fractions to decimals
        double[] decimals = Frac.toDecimalAll(fractions);
        System.out.println("小数変換後の配列: " + Arrays.toString(decimals));
        // 出力例: [-2.0, 0.0, 0.5, 0.75]
    }
}
```
## LICENSE  
FracLibrary is licensed under the MIT License.
For details, please refet to the [LICENSE](./LICENSE) file in the repository.

---

# FracLibrary (Japanese ver.)

`FracLibrary` は、Javaで分数を扱えるようにするライブラリです。  
このライブラリを使えば、分数の演算や配列操作を簡単に行うことができます。

*各機能についての詳細は、 **[準備中](github.com)** で確認できます。*

## 概要

このライブラリの `Frac` クラスは、以下の機能を含みます。
- 分数や整数を含む四則演算 (自動的に既約分数化)
- 分数単体または配列全体の符号反転、逆数計算、少数変換
- 分数同士または配列全体の最大値・最小値の取得
- 分数同士の比較 ( `Comparable<Frac>` を実装)
- 配列の入れ替え、複製
- その他 `java.util.Arrays` を利用する配列操作

乗算時には Math.multiplyExact を使用し、オーバーフローを検知して例外を投げます。   
不変 (immutable) 設計なので、複雑な計算でも安心して利用できます。

## 使用例

```java
import library.fracLibrary.Frac;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Frac f1 = new Frac(1, 2);
        Frac f2 = new Frac(3, 4);

        // 四則演算
        Frac add = f1.add(f2); // 5/4
        Frac sub = f1.sub(f2); // -1/4
        Frac mul = f1.mul(f2); // 3/8
        Frac div = f1.div(f2); // 2/3

        // 逆数・符号反転
        Frac neg = f1.negate();     // -1/2
        Frac rec = f1.reciprocal(); // 2

        // 最大値・最小値
        Frac max = Frac.max(new Frac[]{f1, f2}); // 3/4
        Frac min = Frac.min(new Frac[]{f1, f2}); // 1/2

        // 小数への変換
        double dec = f1.toDecimal(); // 0.5

        //----------------------------------------------------

        // java.util.Arrays を使った配列操作
        Frac[] fractions = {f1, f2, Frac.toFrac(-2), Frac.toFrac(0)};

        // 配列の表示
        System.out.println("分数の配列: " + Arrays.toString(fractions));
        // 出力例: [1/2, 3/4, -2, 0]

        // インデックス1と2の要素を入れ替え
        Frac.swap(fractions, 1, 2);
        System.out.println("入れ替え後の配列: " + Arrays.toString(fractions));
        // 出力例: [1/2, -2, 3/4, 0]

        // 配列のソート
        Arrays.sort(fractions);
        System.out.println("ソート後の配列: " + Arrays.toString(fractions));
        // 出力例: [-2, 0, 1/2, 3/4]

        // 配列の少数変換
        double[] decimals = Frac.toDecimalAll(fractions);
        System.out.println("小数変換後の配列: " + Arrays.toString(decimals));
        // 出力例: [-2.0, 0.0, 0.5, 0.75]
    }
}
```
## ライセンス
FracLibraryは MIT ライセンスで公開されています。
詳細はリポジトリ内の [LICENSE](./LICENSE) ファイルを確認してください。
