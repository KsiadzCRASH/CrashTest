/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.marshalling;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.asseco.amms.model.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 *
 * @author nerull
 */
public final class ClassUtils {

    private static Map<Class, List<Field>> propertyMemory = new HashMap<Class, List<Field>>();
    private static Map<Class, List<Method>> methodMemory = new HashMap<Class, List<Method>>();
    private static Map<Class<Entity>, String> tableNameMemory = new HashMap<Class<Entity>, String>();

    private ClassUtils() {
    }

    /**
     * Funkcja służy zwracaniu nazwy tabeli dla podanej encji.
     * Połączenia przechowuje w pamięci.
     * @param aClass Nazwa encji
     * @return Nazwa tabeli w bazie
     */
    public static String findTableNameForClass(Class<Entity> aClass){
        String tableName = null;
        tableName = tableNameMemory.get(aClass);
        if (tableName == null){
            tableName = aClass.getClass().getAnnotation(Table.class).name();
            tableNameMemory.put(aClass, tableName);
        }
        return tableName;

    }


    public static <E extends Object> List<Field> getFields(Class<E> clazz) {
        List<Field> fields = propertyMemory.get(clazz);
        if (fields == null) {
            fields = new ArrayList<Field>();
            for (Class cl : getClasses(clazz)) {
            	List<Field> lst = new ArrayList<Field>(Arrays.asList(cl.getDeclaredFields()));
            	Field remove = null;
            	for (Field field : lst) {
            		if (field.getName().equals("serialVersionUID")) {
            			remove = field;
            			break;
            		}
            	}
            	lst.remove(remove);
                fields.addAll(lst);
            }
            propertyMemory.put(clazz, fields);
        }
        return fields;
    }

    public static <E extends Object, A extends Annotation> List<Field> findFieldForAnnotation (Class<E> clazz, Class<A> annotationType) {
        List<Field> fields = new ArrayList<Field>();
        for (Field field : getFields(clazz)) {
            if (field.isAnnotationPresent(annotationType)) {
                fields.add(field);
            }
        }
        return fields;
    }

    /**
     * Wyszukuje pola danego typu
     * @param clazz klaa obiektu
     * @param aClassType typ pola które szukamy
     * @param <E> klasa obiektu
     * @param <A> klasa pola
     * @return lista pól
     */
    public static <E extends Object, A extends Class> List<Field> findFieldsForType (Class<E> clazz, A aClassType) {
        List<Field> fields = new ArrayList<Field>();
        for (Field field : getFields(clazz)) {
            if (field.getType().isAssignableFrom(aClassType) || aClassType.isAssignableFrom(field.getType())) {
                fields.add(field);
            }
        }
        return fields;
    }

    public static <E extends Object> Field findFieldForName (Class<E> clazz, String name) {
        for (Field field : getFields(clazz)) {
            if (field.getName().equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * Wyszukuje pole na podstawie podanego ciągu znaków. Jeżeli name zawiera kropkę to parsuje jako podpola i wywołuje
     * rekurencyjnie
      * @param clazz klasa
     * @param name pole (może zawierać kropki)
     * @param findSubfields jeżeli true to wyszukuje także rekurencyjnie
     * @param <E>
     * @return
     */
    public static <E extends Object> Field findFieldForName(Class<E> clazz, String name, boolean findSubfields)
    {
        int dotIndex = name.indexOf(".");
        boolean containsSubfields = dotIndex >= 0;
        String pFieldName = name;
        if (containsSubfields && findSubfields)
        {
            pFieldName = name.substring(0, dotIndex);
        }
        //weryfikacja
        for (Field field : getFields(clazz))
        {
            if (field.getName().equals(pFieldName))
            {
                return containsSubfields && findSubfields ? findFieldForName(field.getType(), name.substring(dotIndex + 1), findSubfields) : field;
            }
        }
        return null;
    }

    private static <E extends Object> Method getReadWriteMethod (Field field, Class<E> clazz, boolean setter) {
        String methodName = (setter ? "set" : "get") + capitalize(field.getName());
        Method method = null;
        List<Method> methods = methodMemory.get(clazz);
        if (methods == null) {
            methods = new ArrayList<Method>();
            for (Class cl : getClasses(clazz)) {
                methods.addAll(Arrays.asList(cl.getDeclaredMethods()));
            }
            methodMemory.put(clazz, methods);
        }
        for (Method method1 : methods) {
            if (method1.getName().equals(methodName)) {
                method = method1;
                break;
            }
        }
        if (method == null) {
            throw new RuntimeException ("Nie znaleziono metody set/get dla pola: " + field.getName());
        }
        return method;
    }
    public static <E extends Object> Method getWriteMethod (Field field, Class<E> clazz) {
        return getReadWriteMethod(field, clazz, true);
    }

    public static <E extends Object> Method getReadMethod (Field field, Class<E> clazz) {
        return getReadWriteMethod(field, clazz, false);
    }

    private static String capitalize(String name) {
	if (name == null || name.length() == 0) {
	    return name;
        }
	return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static List<Class> getClasses(Class clazz) {
        List<Class> classes = new ArrayList<Class>();
        for (Class cl = clazz; cl != Object.class; cl = cl.getSuperclass()) {
            classes.add(cl);
        }
        return classes;
    }
    
    public static void derivedClass2BaseClass(BaseEntity derivedClass, BaseEntity baseClass)
    {       
    	List<Field> derivedClassFields = ClassUtils.getFields(derivedClass.getClass());
        List<Field> baseClassFields = ClassUtils.getFields(baseClass.getClass());
        for (Field baseClassField : baseClassFields)
            for (Field derivedClassField : derivedClassFields)
                if (baseClassField.getName().equals(derivedClassField.getName()) && baseClassField.getModifiers() == 2 )
                {
                	Method read = ClassUtils.getReadMethod(derivedClassField, derivedClass.getClass());
                	Method write = ClassUtils.getWriteMethod(baseClassField, baseClass.getClass());
                	Object origValue = null;
                	
                    try
                    {
                        origValue = (Object) read.invoke(derivedClass);
                        write.invoke(baseClass, origValue);

                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    } 
                }
    }
}
