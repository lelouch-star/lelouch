package org.lelouch.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by lelouch on 16-12-2.
 */
public class RException {

    private static Logger log = LoggerFactory.getLogger(RException.class);

    public static void run(String type, String e) {
//        System.err.println("RuntimeException " + "\n 类型: " + type + "\n 错误信息: " + e);
        log.error("RuntimeException " + "\n 类型: " + type + "\n 错误信息: " + e);
        throw new RuntimeException(e);

    }

    /**
     * 获取异常详细信息
     *
     * @param e
     *
     * @return
     */
    public static String getStackTraceInfo(Exception e) {

        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();

            return sw.toString();
        } catch (Exception ex) {
            log.error("getStackTraceInfo "+ ex);
            return "发生错误";
        } finally {
            if (sw != null){
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    log.error("getStackTraceInfo "+ e1);
                }
            }
            if (pw != null){
                pw.close();
            }
        }
    }

    /**
     * 获取异常详细信息
     *
     * @param cause
     *
     * @return
     */
    public static String getStackTraceInfo(Throwable cause) {

        StringWriter sw = null;
        PrintWriter pw = null;

        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            cause.printStackTrace(pw);//将出错的栈信息输出到printWriter中
            pw.flush();
            sw.flush();

            return sw.toString();
        } catch (Exception ex) {
            log.error("getStackTraceInfo "+ ex);
            return "发生错误";
        } finally {
            if (sw != null){
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    log.error("getStackTraceInfo "+ e1);
                }
            }
            if (pw != null){
                pw.close();
            }
        }
    }
}
