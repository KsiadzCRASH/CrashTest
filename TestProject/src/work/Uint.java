/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package work;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author nerull
 */
public class Uint /*extends Number*/ implements Serializable, Comparable<Uint>, Cloneable {

    /*private static class UintCache {
        private UintCache() {
        }

        static final Uint cache[] = new Uint[11]; // cache for [-1, 9]

        static {
            for (int i = 0; i < cache.length; i++)
                cache[i] = Uint.valueOf(Long.valueOf(i - 1));
        }
    }*/

    private static final long serialVersionUID = 2471218926150208486L;

    private Long value;

    public Uint(Long v) {
        if (v == null)
            throw new IllegalArgumentException("Argument typu Uint nie moze byc null");
        value = v;
    }

    public Uint(int v) {
        value = new Long(v);
    }

    public Uint(BigDecimal v) {
        value = v.longValue();
    }

    public static Uint fromObject(Object object) {

        if (object == null) {
            return null;
        } else {

            if (object instanceof Uint) return (Uint) object;
            if (object instanceof Integer) return new Uint((Integer) object);
            if (object instanceof Long) return new Uint((Long) object);
            if (object instanceof BigDecimal) return new Uint((BigDecimal) object);
            if (object instanceof String) return new Uint((String) object);

            return null;
        }
    }
    
    public static Uint ZERO() {
    	return fromObject(0);
    }
    
    public static Uint JEDEN() {
    	return fromObject(1);
    }
    
    public Uint negate() {
    	return new Uint(-this.value);
    }

    /**
     * Tworzenie Uint z wartości tekstowej, jeśli nie uda się skonwertować string na Long
     * rzucany jest wyjątek: NumberFormatException,
     *
     * @param value wartość tworzonego Uinta, nie jest dozwolnona wartość null, wyjątek: IllegalArgumentException
     * @author jacek.markiewicz (2010-11-26)
     */
    public Uint(String value) {

        if (value == null)
            throw new IllegalArgumentException("Argument typu Uint nie moze byc null");

        this.value = new Long(value);
    }

    public Uint() {
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (!(obj instanceof Uint))
            return false;
        Uint other = (Uint) obj;
        if (value == null) {
            if (other.value != null)
                return false;
        } else if (!value.equals(other.value))
            return false;
        return true;
    }

    /**
     * Porównuje dwie wartości - osbługuje nulle
     *
     * @param aValue1 wartość 1
     * @param aValue2 wartość 2
     * @return
     */
    public static boolean equalsStatic(Uint aValue1, Uint aValue2) {
        if (aValue1 == aValue2) //ta sama referencja + nulle
            return true;
        if (aValue1 == null || aValue2 == null)//jeden jest nullem
            return false;
        else //obydwa róznie i nie nulle
            return aValue1.equals(aValue2);
    }

    @Override
    public String toString() {
        return (value != null) ? value.toString() : String.valueOf((Object) null);
    }

    public static Long getLong(Uint uint) {
        return (uint != null) ? uint.getValue() : null;
    }

    public static String asString(Uint uint) {
        return (uint != null) ? uint.toString() : String.valueOf((Object) null);
    }

    /**
     * Funkcja zawsze zwraca wartość, dla null zwraca 0
     *
     * @param value
     * @return
     * @author Jacek.Markiewicz (2010-11-02)
     */
    public static Long asLong(Uint value) {
        return isNull(value) ? 0L : value.getValue();
    }

    /**
     * Funkcja zwraca Uint jako int, dla null zwraca 0
     *
     * @param value
     * @return
     * @author Jacek.Markiewicz (2010-11-07)
     */
    public static int asInt(Uint value) {
        return isNull(value) ? 0 : value.intValue();
    }

    public static Uint valueOf(Long value) {
        return value == null ? null : new Uint(value);
    }

    public static Uint valueOf(int intValue) {
//    	final int offset = 1; 
//    	if (intValue >= -1 && intValue < 10) {
//    		return UintCache.cache[(int)intValue + offset]; 
//    	}
        return Uint.valueOf((long) intValue);
    }

    public static Uint valueOf(Integer intValue) {
        return intValue == null ? null : Uint.valueOf((long) intValue);
    }

    public static Uint valueOf(BigDecimal bigDecimalValue) {
        return bigDecimalValue == null ? null : Uint.valueOf(bigDecimalValue.longValue());
    }

        public static Uint valueOf(BigInteger bigIntegerValue) {
        return bigIntegerValue == null ? null : Uint.valueOf(bigIntegerValue.longValue());
    }

    public static Uint valueOf(String aStringValue) {
        return valueOf(Long.valueOf(aStringValue));
    }

    public static boolean isNull(Uint wart) {
        return ((wart == null || wart.getValue() == null)) ? true : false;
    }

    public static boolean isNotNull(Uint wart) {
        return !isNull(wart);
    }

    public Uint add(Uint value) {
        if (isNotNull(value)) {
            this.value = this.value + value.value;
            return valueOf(this.value);
        }
        return valueOf(this.value);
    }

    /**
     * Metoda konvertuje listę Uint-ów na listę Long-ów
     * Jeśli Uint == null, wtedy jest pomijany
     *
     * @param listaUint
     * @return
     */
    public static List<Long> convertToList(List<Uint> listaUint) {
        if (listaUint == null) {
            return null;
        }
        List<Long> lista = new ArrayList<Long>(listaUint.size());
        for (Uint id : listaUint) {
            if (id != null) {
                lista.add(Uint.getLong(id));
            }
        }
        return lista;
    }

    /**
     * Metoda konwertuje listę BigDecimali na listę Uint-ów Jeśli BigDecimali == null, wtedy jest pomijany
     *
     * @param listaBD
     * @return
     */
    public static List<Uint> convertBigDecToUintList(List<BigDecimal> listaBD) {
        if (listaBD == null) {
            return null;
        }
        List<Uint> lista = new ArrayList<Uint>();
        for (BigDecimal id : listaBD) {
            if (id != null) {
                lista.add(Uint.valueOf(id));
            }
        }
        return lista;
    }
    /**
     * Metoda konvertuje listę Long-ów na listę Uint-ów Jeśli Long == null, wtedy jest pomijany
     *
     * @param listaLong
     * @return
     */
    public static List<Uint> convertToUintList(List<Long> listaLong) {
        if (listaLong == null) {
            return null;
        }
        List<Uint> lista = new ArrayList<Uint>(listaLong.size());
        for (Long id : listaLong) {
            if (id != null) {
                lista.add(Uint.valueOf(id));
            }
        }
        return lista;
    }

    /**
     * Metoda konvertuje zbiór Uint-ow na listę Uint-ów Jeśli Uint == null, wtedy jest pomijany
     *
     * @param setUint
     * @return
     */
    public static List<Uint> convertToUintList(Set<Uint> setUint) {
        if (setUint == null) {
            return null;
        }

        return new ArrayList<Uint>(setUint);
    }

    /**
     * Metoda konvertuje zbiór Uint-ow na listę Uint-ów Jeśli Uint == null, wtedy jest pomijany
     *
     * @param setUint
     * @return
     */
    public static List<Uint> convertToUintListL(Set<Long> setUint) {
        if (setUint == null) {
            return null;
        }

        List<Uint> ret = new ArrayList<Uint>();
        for (Long longVal : setUint) {
            ret.add(Uint.valueOf(longVal));
        }

        return ret;
    }

    /**
     * Metoda konvertuje zbiór Uint-ow na listę Uint-ów Jeśli Uint == null, wtedy jest pomijany
     *
     * @param setUint
     * @return
     */
    public static List<Long> convertToLongList(Set<Long> setUint) {
        if (setUint == null) {
            return null;
        }

        return new ArrayList<Long>(setUint);
    }

    @Override
    public int compareTo(Uint o) {
        if (value == null && (o == null || o.value == null)) {
            return 0;
        }
        if (o == null || o.value == null) {
            return 1;
        }

        return value.compareTo(o.getValue());
    }

    //	@Override
//	public double doubleValue() {
//		return (double) value;
//	}
//
//	@Override
//	public float floatValue() {
//		return (float) value;
//	}
//
    public int intValue() {
        return value.intValue();
    }

    public long longValue() {
        return (long) value;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
