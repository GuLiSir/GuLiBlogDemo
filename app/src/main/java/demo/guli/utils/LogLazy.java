package demo.guli.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Formatter;

/**
 * Log工具，类似android.util.Log。 tag自动产生，格式:
 * customTagPrefix:className.methodName(Line:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(Line:lineNumber)。
 * http://blog.csdn.net/finddreams
 * 复制的博客地址:http://blog.csdn.net/iplayvs2008/article/details/40422999
 * 我调用另一个类转换了,所以此类的类名可以为Log
 */
public class LogLazy {

    private static String customTagPrefix = "demo_"; // 自定义Tag的前缀，可以是作者名
    private static final String ROOT = Environment.getExternalStorageDirectory().getPath() + "/finddreams/"; // SD卡中的根目录
    private static final String PATH_LOG_INFO = ROOT + "info/";

    private LogLazy() {
    }

    // 容许打印日志的类型，默认是true，设置为false则不打印
    private static boolean allowD = true;
    private static boolean allowE = true;
    private static boolean allowI = true;
    private static boolean allowV = true;
    private static boolean allowW = true;
    private static boolean allowWtf = true;
    public static boolean DEBUG =true;
//    public static boolean DEBUG = true;

    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)"; // 占位符
        String callerClazzName = caller.getClassName(); // 获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);// 我的修改,获取倒数第二个类名,原因是我用另一个log输出的.
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber()); // 替换
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    /**
     * 自定义的logger
     */
    private static CustomLogger customLogger;

    private interface CustomLogger {
        void d(String tag, String content);

        void d(String tag, String content, Throwable tr);

        void e(String tag, String content);

        void e(String tag, String content, Throwable tr);

        void i(String tag, String content);

        void i(String tag, String content, Throwable tr);

        void v(String tag, String content);

        void v(String tag, String content, Throwable tr);

        void w(String tag, String content);

        void w(String tag, String content, Throwable tr);

        void w(String tag, Throwable tr);

        void wtf(String tag, String content);

        void wtf(String tag, String content, Throwable tr);

        void wtf(String tag, Throwable tr);
    }

    public static void d(String content) {
        if (!DEBUG)
            return;
        if (!allowD)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content);
        } else {
            Log.d(tag, content);

        }
    }

    public static void d(String content, Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowD)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.d(tag, content, tr);
        } else {
            Log.d(tag, content, tr);
        }
    }

    public static void e(String content) {
        if (!DEBUG)
            return;
        if (!allowE)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content);
        } else {
            Log.e(tag, content);
        }
    }

    public static void e(String content, Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowE)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.e(tag, content, tr);
        } else {
            Log.e(tag, content, tr);
        }
    }

    public static void i(String content) {
        if (!DEBUG)
            return;
        if (!allowI)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag, content);
        } else {
            Log.i(tag, content);
        }

    }

    public static void i(String content, Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowI)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.i(tag, content, tr);
        } else {
            Log.i(tag, content, tr);
        }

    }

    public static void v(String content) {
        if (!DEBUG)
            return;
        if (!allowV)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag, content);
        } else {
            Log.v(tag, content);
        }
    }

    public static void v(String content, Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowV)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.v(tag, content, tr);
        } else {
            Log.v(tag, content, tr);
        }
    }

    public static void w(String content) {
        if (!DEBUG)
            return;
        if (!allowW)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
//        String tag = generateTag(caller);
        String tag = caller.toString();
        if (customLogger != null) {
            customLogger.w(tag, content);
        } else {
            Log.w(tag, content);
        }
    }

    public static void w(String content, Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowW)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, content, tr);
        } else {
            Log.w(tag, content, tr);
        }
    }

    public static void w(Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowW)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.w(tag, tr);
        } else {
            Log.w(tag, tr);
        }
    }

    public static void wtf(String content) {
        if (!DEBUG)
            return;
        if (!allowWtf)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, content);
        } else {
            Log.wtf(tag, content);
        }
    }

    public static void wtf(String content, Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowWtf)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, content, tr);
        } else {
            Log.wtf(tag, content, tr);
        }
    }

    public static void wtf(Throwable tr) {
        if (!DEBUG)
            return;
        if (!allowWtf)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (customLogger != null) {
            customLogger.wtf(tag, tr);
        } else {
            Log.wtf(tag, tr);
        }
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }


    /**
     * 根据文件路径 递归创建文件
     *
     * @param file
     */
    public static void createDipPath(String file) {
        String parentFile = file.substring(0, file.lastIndexOf("/"));
        File file1 = new File(file);
        File parent = new File(parentFile);
        if (!file1.exists()) {
            parent.mkdirs();
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * A little trick to reuse a formatter in the same thread
     */
    private static class ReusableFormatter {

        private Formatter formatter;
        private StringBuilder builder;

        private ReusableFormatter() {
            builder = new StringBuilder();
            formatter = new Formatter(builder);
        }

        private String format(String msg, Object... args) {
            formatter.format(msg, args);
            String s = builder.toString();
            builder.setLength(0);
            return s;
        }
    }

    private static final ThreadLocal<ReusableFormatter> thread_local_formatter = new ThreadLocal<ReusableFormatter>() {
        protected ReusableFormatter initialValue() {
            return new ReusableFormatter();
        }
    };

    public static String format(String msg, Object... args) {
        ReusableFormatter formatter = thread_local_formatter.get();
        return formatter.format(msg, args);
    }
}