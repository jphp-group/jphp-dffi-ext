package org.develnext.jphp.ext.system.classes;

import com.sun.jna.Function;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import org.develnext.jphp.ext.system.DFFIExtension;
import php.runtime.annotation.Reflection;
import php.runtime.annotation.Reflection.Signature;
import php.runtime.env.Environment;
import php.runtime.lang.BaseObject;
import php.runtime.reflection.ClassEntity;

@Reflection.Namespace(DFFIExtension.NS)
public class DFFIConsole extends BaseObject {
    public DFFIConsole(Environment env) {
        super(env);
    }

    protected DFFIConsole(ClassEntity entity) {
        super(entity);
    }

    public DFFIConsole(Environment env, ClassEntity clazz) {
        super(env, clazz);
    }

    private static Boolean isTerm = null;
    private static Boolean isColorSupport = null;

    @Signature
    public static boolean isXTerm() {
        if (isTerm != null) {
            return isTerm;
        }

        String term = System.getenv("TERM");
        return isTerm = ("xterm".equalsIgnoreCase(term));
    }

    @Signature
    public static boolean hasColorSupport() {
        if (isColorSupport != null) {
            return isColorSupport;
        }

        if (isXTerm()) {
            return isColorSupport = true;
        }

        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("windows")) {
            try {
                float osVersion = Float.parseFloat(System.getProperty("os.version"));
                if (osVersion >= 10.0f) {
                    return isColorSupport = true;
                }
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                return isColorSupport = false;
            }
        }

        return isColorSupport = false;
    }

    @Signature
    public static boolean enableColors() {
        if (isXTerm()) {
            return true;
        }

        String osName = System.getProperty("os.name");

        if (osName.toLowerCase().startsWith("windows")) {
            try {
                float osVersion = Float.parseFloat(System.getProperty("os.version"));
                if (osVersion >= 10.0f) {
                    enableColorsForWindows();
                    return true;
                }

                return isColorSupport = false;
            } catch (NumberFormatException e) {
                System.err.println(e.getMessage());
                return isColorSupport = false;
            }
        }

        return true;
    }

    protected static void enableColorsForWindows() {
        // Set output mode to handle virtual terminal sequences
        Function GetStdHandleFunc = Function.getFunction("kernel32", "GetStdHandle");
        WinDef.DWORD STD_OUTPUT_HANDLE = new WinDef.DWORD(-11);
        WinNT.HANDLE hOut = (WinNT.HANDLE) GetStdHandleFunc.invoke(WinNT.HANDLE.class, new Object[]{STD_OUTPUT_HANDLE});

        WinDef.DWORDByReference p_dwMode = new WinDef.DWORDByReference(new WinDef.DWORD(0));
        Function GetConsoleModeFunc = Function.getFunction("kernel32", "GetConsoleMode");
        GetConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{hOut, p_dwMode});

        int ENABLE_VIRTUAL_TERMINAL_PROCESSING = 4;
        WinDef.DWORD dwMode = p_dwMode.getValue();
        dwMode.setValue(dwMode.intValue() | ENABLE_VIRTUAL_TERMINAL_PROCESSING);
        Function SetConsoleModeFunc = Function.getFunction("kernel32", "SetConsoleMode");
        SetConsoleModeFunc.invoke(WinDef.BOOL.class, new Object[]{hOut, dwMode});
    }
}
