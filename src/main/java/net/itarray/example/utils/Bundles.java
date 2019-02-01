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
        return String.valueOf(bundles.get(String.class.hashCode() * bundleName.hashCode()).bundleValue);
    }

    public static void putInt(String bundleName, int bundleValue) {
        Bundle bundle = new Bundle(Integer.class, bundleName, bundleValue);
        bundles.put(bundle.hashCode(), bundle);
    }

    public static Integer getInt(String bundleName) {
        return (Integer) bundles.get(String.class.hashCode() * bundleName.hashCode()).bundleValue;
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
