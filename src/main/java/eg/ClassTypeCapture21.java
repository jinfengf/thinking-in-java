package eg;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.HashMap;
import java.util.Map;

import generics.ClassTypeCapture;

/**
 * Created by jiguang on 2018/7/25.
 */

public class ClassTypeCapture21<T> {
    Class<?> kind;
    Map<String, Class<?>> map;

    public ClassTypeCapture21(Class<?> kind) {
        this.kind = kind;
    }

    public ClassTypeCapture21(Class<?> kind, Map<String, Class<?>> map) {
        this.kind = kind;
        this.map = map;
    }

    public boolean f(Object arg) {
        return kind.isInstance(arg);
    }

    public void addType(String typename, Class<?> kind) {
        map.put(typename, kind);
    }

    public Object createNew(String typename) throws IllegalAccessException, InstantiationException {
        if (map.containsKey(typename)) {
            return map.get(typename).newInstance();
        }
        System.out.println(typename + " class not available");
        return null;
    }
    public static void main(String[] args) {
        ClassTypeCapture21<Building> ctt1 = new ClassTypeCapture21<Building>(Building.class);
        System.out.println(ctt1.f(new Building()));
        System.out.println(ctt1.f(new House()));
        ClassTypeCapture21<House> ctt2 = new ClassTypeCapture21<House>(House.class);
        System.out.println(ctt2.f(new Building()));
        System.out.println(ctt2.f(new House()));
        ClassTypeCapture21<Building> ct =
        new ClassTypeCapture21<Building>(Building.class, new HashMap<String, Class<?>>());
        ct.addType("House", House.class);
        ct.addType("Building", Building.class);
        System.out.println("ct.map = " + ct.map);
        try {
            Building b = (Building) ct.createNew("Building");
            House h = (House) ct.createNew("House");
            System.out.println("b.getClass().getName():" + b.getClass().getName());
            System.out.println("h.getClass().getName():" + h.getClass().getName());
            System.out.println("House h is instance House: " + (h instanceof House));
            System.out.println("House h is instance of Building: " + (h instanceof Building));
            System.out.println("Building b is instance of House: " + (b instanceof House));
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException in main");
        } catch (InstantiationException e) {
            System.out.println("InstantiationException in main");
        }
    }
}

class Building {}
class House extends Building {}
