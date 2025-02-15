/*
 * MIT License
 *
 * Copyright (c) 2002-2023 Mikko Tommila
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.apfloat.spi;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.apfloat.Apfloat;

/**
 * Miscellaneous utility methods.
 *
 * @version 1.12.0
 * @author Mikko Tommila
 */

public class Util
{
    private Util()
    {
    }

    /**
     * Round down to nearest power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return <code>x</code> rounded down to the nearest power of two.
     */

    public static int round2down(int x)
    {
        assert (x >= 0);

        return Integer.highestOneBit(x);
    }

    /**
     * Round down to nearest power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return <code>x</code> rounded down to the nearest power of two.
     */

    public static long round2down(long x)
    {
        assert (x >= 0);

        return Long.highestOneBit(x);
    }

    /**
     * Round up to nearest power of two.
     *
     * @param x The input value, which must be non-negative and not greater than 2<sup>30</sup>.
     *
     * @return <code>x</code> rounded up to the nearest power of two.
     */

    public static int round2up(int x)
    {
        assert (x >= 0);
        assert (x <= 0x40000000);

        if (x == 0)
        {
            return 0;
        }

        int r = 1;

        while (r < x)
        {
            r += r;
        }

        return r;
    }

    /**
     * Round up to nearest power of two.
     *
     * @param x The input value, which must be non-negative and not greater than 2<sup>62</sup>.
     *
     * @return <code>x</code> rounded up to the nearest power of two.
     */

    public static long round2up(long x)
    {
        assert (x >= 0);
        assert (x <= 0x4000000000000000L);

        if (x == 0)
        {
            return 0;
        }

        long r = 1;

        while (r < x)
        {
            r += r;
        }

        return r;
    }

    /**
     * Round down to nearest power of two or three times a power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return <code>x</code> rounded down to nearest power of two or three times a power of two.
     */

    public static int round23down(int x)
    {
        assert (x >= 0);

        if (x == 0)
        {
            return 0;
        }

        int r = 1, p = 0;

        while (r <= x && r > 0)     // Detect overflow
        {
            p = r;
            if (r == 1)
            {
                r = 2;
            }
            else if (r == (r & -r))
            {
                r = r / 2 * 3;
            }
            else
            {
                r = r / 3 * 4;
            }
        }

        return p;
    }

    /**
     * Round down to nearest power of two or three times a power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return <code>x</code> rounded down to nearest power of two or three times a power of two.
     */

    public static long round23down(long x)
    {
        assert (x >= 0);

        if (x == 0)
        {
            return 0;
        }

        long r = 1, p = 0;

        while (r <= x && r > 0)     // Detect overflow
        {
            p = r;
            if (r == 1)
            {
                r = 2;
            }
            else if (r == (r & -r))
            {
                r = r / 2 * 3;
            }
            else
            {
                r = r / 3 * 4;
            }
        }

        return p;
    }

    /**
     * Round up to nearest power of two or three times a power of two.
     *
     * @param x The input value, which must be non-negative and not greater than 3 * 2<sup>29</sup>.
     *
     * @return <code>x</code> rounded up to the nearest power of two or three times a power of two.
     */

    public static int round23up(int x)
    {
        assert (x >= 0);
        assert (x <= 0x60000000);

        if (x == 0)
        {
            return 0;
        }

        int r = 1;

        while (r < x)
        {
            if (r == 1)
            {
                r = 2;
            }
            else if (r == (r & -r))
            {
                r = r / 2 * 3;
            }
            else
            {
                r = r / 3 * 4;
            }
        }

        return r;
    }

    /**
     * Round up to nearest power of two or three times a power of two.
     *
     * @param x The input value, which must be non-negative and not greater than 3 * 2<sup>61</sup>.
     *
     * @return <code>x</code> rounded up to the nearest power of two or three times a power of two.
     */

    public static long round23up(long x)
    {
        assert (x >= 0);
        assert (x <= 0x6000000000000000L);

        if (x == 0)
        {
            return 0;
        }

        long r = 1;

        while (r < x)
        {
            if (r == 1)
            {
                r = 2;
            }
            else if (r == (r & -r))
            {
                r = r / 2 * 3;
            }
            else
            {
                r = r / 3 * 4;
            }
        }

        return r;
    }

    /**
     * Square root rounded down to nearest power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return Square root of <code>x</code> rounded down to nearest power of two.
     */

    public static int sqrt4down(int x)
    {
        assert (x >= 0);

        if (x == 0)
        {
            return 0;
        }

        int r = 1;

        while ((x >>= 2) > 0)
        {
            r <<= 1;
        }

        return r;
    }

    /**
     * Square root rounded down to nearest power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return Square root of <code>x</code> rounded down to nearest power of two.
     */

    public static long sqrt4down(long x)
    {
        assert (x >= 0);

        if (x == 0)
        {
            return 0;
        }

        long r = 1;

        while ((x >>= 2) > 0)
        {
            r <<= 1;
        }

        return r;
    }

    /**
     * Square root rounded up to nearest power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return Square root of <code>x</code> rounded up to nearest power of two.
     */

    public static int sqrt4up(int x)
    {
        assert (x >= 0);

        if (x == 0)
        {
            return 0;
        }

        int r = 1, p = 1;

        while (p < x && p > 0)      // Detect overflow
        {
            p <<= 2;
            r <<= 1;
        }

        return r;
    }

    /**
     * Square root rounded up to nearest power of two.
     *
     * @param x The input value, which must be non-negative.
     *
     * @return Square root of <code>x</code> rounded up to nearest power of two.
     */

    public static long sqrt4up(long x)
    {
        assert (x >= 0);

        if (x == 0)
        {
            return 0;
        }

        long r = 1, p = 1;

        while (p < x && p > 0)      // Detect overflow
        {
            p <<= 2;
            r <<= 1;
        }

        return r;
    }

    /**
     * Base-2 logarithm rounded down to nearest power of two.
     *
     * @param x The input value, which must be positive.
     *
     * @return <code>log<sub>2</sub>(x)</code> rounded down to nearest integer.
     */

    public static int log2down(int x)
    {
        assert (x > 0);

        return 31 - Integer.numberOfLeadingZeros(x);
    }

    /**
     * Base-2 logarithm rounded down to nearest power of two.
     *
     * @param x The input value, which must be positive.
     *
     * @return <code>log<sub>2</sub>(x)</code> rounded down to nearest integer.
     */

    public static int log2down(long x)
    {
        assert (x > 0);

        return 63 - Long.numberOfLeadingZeros(x);
    }

    /**
     * Base-2 logarithm rounded up to nearest power of two.
     *
     * @param x The input value, which must be positive.
     *
     * @return <code>log<sub>2</sub>(x)</code> rounded up to nearest integer.
     */

    public static int log2up(int x)
    {
        assert (x > 0);

        return log2down(x) + (x == (x & -x) ? 0 : 1);
    }

    /**
     * Base-2 logarithm rounded up to nearest power of two.
     *
     * @param x The input value, which must be positive.
     *
     * @return <code>log<sub>2</sub>(x)</code> rounded up to nearest integer.
     */

    public static int log2up(long x)
    {
        assert (x > 0);

        return log2down(x) + (x == (x & -x) ? 0 : 1);
    }

    /**
     * Returns the argument <code>y</code> limited to <code>Apfloat.INFINITE</code>.
     * In case <code>x</code> is <code>Apfloat.INFINITE</code>, then <code>Apfloat.INFINITE</code>
     * is returned, otherwise <code>y</code>. Also if <code>y</code> is negative or zero, this is
     * treated as a case of overflow, and <code>Apfloat.INFINITE</code> is returned. The
     * return value is thus always positive.
     *
     * @param x The argument that is tested to be <code>Apfloat.INFINITE</code>.
     * @param y The argument that is returned if <code>x</code> is not <code>Apfloat.INFINITE</code>.
     *
     * @return <code>(x == Apfloat.INFINITE || y &lt;= 0 ? Apfloat.INFINITE : y)</code>
     */

    public static long ifFinite(long x, long y)
    {
        return (x == Apfloat.INFINITE || y <= 0 ? Apfloat.INFINITE : y);
    }

    /**
     * Convert iterator to stream.
     *
     * @param <T> The type.
     * @param iterator The iterator.
     *
     * @return The stream.
     *
     * @since 1.12.0
     */

    public static <T> Stream<T> stream(Iterator<T> iterator)
    {
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
