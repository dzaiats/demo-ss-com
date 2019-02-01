package net.itarray.example.utils;

import java.util.HashMap;
import java.util.Map;

public class Bundles {

    private static Map<Integer, Bundle> bundles = new HashMap<>();

    public static void putString(String bundleName, String bundleValue) {
        Bundle bundle = new Bundle(String.class, bundleName, bundleValue);
        bundles.put(bundle.hashCode(), bundle);
    }

    public static String getString(String bundleName) {
        Bundle b = bundles.get(String.class.hashCode() * bundleName.hashCode());
        return b != null ? String.valueOf(b.bundleValue) : null;
    }

    public static void putInt(String bundleName, int bundleValue) {
        Bundle bundle = new Bundle(Integer.class, bundleName, bundleValue);
        bundles.put(bundle.hashCode(), bundle);
    }

    public static Integer getInt(String bundleName) {
        Bundle b = bundles.get(Integer.class.hashCode() * bundleName.hashCode());
        return b != null ? (Integer) b.bundleValue : 0;
    }

    public static void putObjectOfType(Object o, String bundleName, Object bundleValue) {
        Bundle bundle = new Bundle(o, bundleName, bundleValue);
        bundles.put(bundle.hashCode(), bundle);
    }

    public static Object getObjectOfType(Object o, String bundleName) {
        Bundle b = bundles.get(o.hashCode() * bundleName.hashCode());
        return b != null ? b.bundleValue : 0;
    }

    private static class Bundle {
        Class bundleClass;
        String bundleName;
        Object bundleValue;

        Bundle(Object object, String bundleName, Object bundleValue) {
            this.bundleClass = (Class) object;
            this.bundleName = bundleName;
            this.bundleValue = bundleValue;
        }

        public int hashCode() {
            return bundleClass.hashCode() * bundleName.hashCode();
        }
    }
}
