package com.yanll.framework.util;

public class USystem {

    // AIX
    // Digital Unix
    // FreeBSD
    // HP UX
    // Irix
    // Linux
    // Mac OS
    // Mac OS X
    // MPE/iX
    // Netware 4.11
    // OS/2
    // Solaris
    // Windows 2000
    // Windows 95
    // Windows 98
    // Windows NT
    // Windows Vista
    // Windows XP

    /**
     * Operating System
     */
    public enum Os {
        LINUX("linux"), SOLARIS("solaris"), BSD("bsd"), MAC("mac"), WINDOWS("windows");

        private final String osNameContains;

        private Os(final String osNameContains) {
            this.osNameContains = osNameContains;
        }

        public final String getOsNameContains() {
            return osNameContains;
        }
    }

    private USystem() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Finds out if the curent os supports symbolically linking files
     *
     * @return true if the os supports symlinks
     */
    public static boolean osSupportsLinking() {
        Os os = getOs();
        return os == Os.LINUX || os == Os.MAC || os == Os.SOLARIS || os == Os.BSD;
    }

    /**
     * @return the os
     */
    public static Os getOs() {
        Os os = null;
        String osNameLower = System.getProperty("os.name").toLowerCase();
        for (Os curOs : Os.values()) {
            if (osNameLower.contains(curOs.getOsNameContains())) {
                os = curOs;
            }
        }
        return os;
    }
}
