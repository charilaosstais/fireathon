package com.mcm.config;

import java.util.Map;



public class Constants {

    public static final String SERVER_ROOT = "/mcm/";
    //public static final String SERVER_ROOT = "/wubbyTest/";
	//public static final String SERVER_ROOT = "/wubbyCloud/";
    
    public static final String SERVER_IP = "localhost"; // LOCAL SERVER
    //public static final String SERVER_IP = "5.189.168.89"; // VPS SERVER
    
    
    public static final String SERVER_PORT = "8080";
    
    public static final String DB_URL = "jdbc:mysql://192.168.1.11:3306/mcm"; // "jdbc:mysql://192.168.2.10:3306/webDB";
    //public static final String DB_URL = "jdbc:mysql://" + SERVER_IP + ":3306/webDBTest"; // "jdbc:mysql://192.168.2.10:3306/webDB";
    //public static final String DB_URL = "jdbc:mysql://" + SERVER_IP + ":3306/webDBTest2"; // "jdbc:mysql://192.168.2.10:3306/webDB";
    
    //public static final String DB_URL ="jdbc:mysql://192.168.2.10:3306/webDB";
    
    /* ---------------------------------------------------------------------
    USER_TYPE
    --------------------------------------------------------------------- */
    public enum USER_TYPE {

        UNKNOWN("UNKNOWN", 0),
        ADMIN("Admin", 1),
        CUSTOMER("Customer", 2),
        DEVELOPER("Developer", 3),
        MANUFACTURER("Manufacturer", 4),
        DEVICE("Device", 5);
    	
    	
        private final String name;
        private final int code;

        private USER_TYPE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public static USER_TYPE getUserType(String value) {
            if (value.equals(USER_TYPE.ADMIN.getName())) {
                return USER_TYPE.ADMIN;
            } else if (value.equals(USER_TYPE.CUSTOMER.getName())) {
                return USER_TYPE.CUSTOMER;
            } else if (value.equals(USER_TYPE.DEVELOPER.getName())) {
                return USER_TYPE.DEVELOPER;
            } else if (value.equals(USER_TYPE.MANUFACTURER.getName())) {
                return USER_TYPE.MANUFACTURER;
            } else if (value.equals(USER_TYPE.DEVICE.getName())) {
                return USER_TYPE.DEVICE;
            } else {
                return USER_TYPE.UNKNOWN;
            }
        }

        public static USER_TYPE getUserType(int value) {
            if (value == USER_TYPE.ADMIN.getCode()) {
                return USER_TYPE.ADMIN;
            } else if (value == USER_TYPE.CUSTOMER.getCode()) {
                return USER_TYPE.CUSTOMER;
            } else if (value == USER_TYPE.DEVELOPER.getCode()) {
                return USER_TYPE.DEVELOPER;
            } else if (value == USER_TYPE.DEVICE.getCode()) {
                return USER_TYPE.DEVICE;
            } else {
                return USER_TYPE.UNKNOWN;
            }
        }

    }
    
    public enum BOOLEAN_FLAG_VALUE {

    	DONTCARE("DONTCARE", -1),
        TRUE("TRUE", 1),
        FALSE("FALSE", 0);
    	
        private final String name;
        private final int code;

        private BOOLEAN_FLAG_VALUE(String name, int code) {
            this.name = name;
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        public static BOOLEAN_FLAG_VALUE getUserType(String value) {
            if (value.equals(BOOLEAN_FLAG_VALUE.DONTCARE.getName())) {
                return BOOLEAN_FLAG_VALUE.DONTCARE;
            } else if (value.equals(BOOLEAN_FLAG_VALUE.TRUE.getName())) {
                return BOOLEAN_FLAG_VALUE.TRUE;
            } else if (value.equals(BOOLEAN_FLAG_VALUE.FALSE.getName())) {
                return BOOLEAN_FLAG_VALUE.FALSE;
            } else {
            	return BOOLEAN_FLAG_VALUE.TRUE;
            }
        }

        public static BOOLEAN_FLAG_VALUE getUserType(int value) {
            if (value == BOOLEAN_FLAG_VALUE.DONTCARE.getCode()) {
                return BOOLEAN_FLAG_VALUE.DONTCARE;
            } else if (value == BOOLEAN_FLAG_VALUE.TRUE.getCode()) {
                return BOOLEAN_FLAG_VALUE.TRUE;
            } else if (value == BOOLEAN_FLAG_VALUE.FALSE.getCode()) {
                return BOOLEAN_FLAG_VALUE.FALSE;
            } else {
            	return BOOLEAN_FLAG_VALUE.TRUE;
            }
        }

    }
    
    public enum DEFAULT_PARAMETERS{
    	
    	SERVER("server", SERVER_IP),
    	PORT("port", SERVER_PORT);
    	//PATH("path", "/Downloads");
    	
    	
        private final String name;
        private final String value;
    	
    	 private DEFAULT_PARAMETERS(String name, String value) {
             this.name = name;
             this.value = value;
         }

    	 public String getName() {
             return name;
         }

         public String getValue() {
             return value;
         }
    }

    /* ---------------------------------------------------------------------
    NULL CHECKS
    --------------------------------------------------------------------- */
    public static boolean StringIsNullOrEmpty(String s) {
        if (s == null) return true;
        if (s.isEmpty()) return true;
        return false;
    }

    public static boolean StringIsNullOrEmptyORContainsEmpty(String s) {
        if (s == null) return true;
        if (s.isEmpty()) return true;
        if (s.contains(" ")) return true;
        return false;
    }
    public static boolean IntegerIsNullOrEmpty(Integer i) {
        if (i == null) return true;
        return false;
    }

    public static boolean LongIsNullOrEmpty(Long i) {
        if (i == null) return true;
        return false;
    }

}
