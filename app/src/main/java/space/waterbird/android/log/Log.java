/*
 * Copyright (C) 2013 litesuits.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package space.waterbird.android.log;

import android.os.SystemClock;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * the logger
 *
 */
public final class Log {

    /**
     * isPrint: print switch, true will print. false not print
     */
    public static boolean isPrint = true;
    private static String defaultTag = "Log";

    private Log() {}

    public static void setTag(String tag) {
        defaultTag = tag;
    }

    public static int i(Object o) {
        return isPrint && o != null ? android.util.Log.i(defaultTag, o.toString()) : -1;
    }

    public static int i(String m) {
        return isPrint && m != null ? android.util.Log.i(defaultTag, m) : -1;
    }

    /**
     * ******************** Log **************************
     */
    public static int v(String tag, String msg) {
        return isPrint && msg != null ? android.util.Log.v(tag, msg) : -1;
    }

    public static int d(String tag, String msg) {
        return isPrint && msg != null ? android.util.Log.d(tag, msg) : -1;
    }

    public static int i(String tag, String msg) {
        return isPrint && msg != null ? android.util.Log.i(tag, msg) : -1;
    }

    public static int w(String tag, String msg) {
        return isPrint && msg != null ? android.util.Log.w(tag, msg) : -1;
    }

    public static int e(String tag, String msg) {
        return isPrint && msg != null ? android.util.Log.e(tag, msg) : -1;
    }

    /**
     * ******************** Log with object list **************************
     */
    public static int v(String tag, Object... msg) {
        return isPrint ? android.util.Log.v(tag, getLogMessage(msg)) : -1;
    }

    public static int d(String tag, Object... msg) {
        return isPrint ? android.util.Log.d(tag, getLogMessage(msg)) : -1;
    }

    public static int i(String tag, Object... msg) {
        return isPrint ? android.util.Log.i(tag, getLogMessage(msg)) : -1;
    }

    public static int w(String tag, Object... msg) {
        return isPrint ? android.util.Log.w(tag, getLogMessage(msg)) : -1;
    }

    public static int e(String tag, Object... msg) {
        return isPrint ? android.util.Log.e(tag, getLogMessage(msg)) : -1;
    }

    private static String getLogMessage(Object... msg) {
        if (msg != null && msg.length > 0) {
            StringBuilder sb = new StringBuilder();
            for (Object s : msg) {
                if (s != null) {
                    sb.append(s.toString());
                }
            }
            return sb.toString();
        }
        return "";
    }

    /**
     * ******************** Log with Throwable **************************
     */
    public static int v(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.v(tag, msg, tr) : -1;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.d(tag, msg, tr) : -1;
    }

    public static int i(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.i(tag, msg, tr) : -1;
    }

    public static int w(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.w(tag, msg, tr) : -1;
    }

    public static int e(String tag, String msg, Throwable tr) {
        return isPrint && msg != null ? android.util.Log.e(tag, msg, tr) : -1;
    }

    /**
     * ******************** TAG use Object Tag **************************
     */
    public static int v(Object tag, String msg) {
        return isPrint ? android.util.Log.v(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int d(Object tag, String msg) {
        return isPrint ? android.util.Log.d(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int i(Object tag, String msg) {
        return isPrint ? android.util.Log.i(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int w(Object tag, String msg) {
        return isPrint ? android.util.Log.w(tag.getClass().getSimpleName(), msg) : -1;
    }

    public static int e(Object tag, String msg) {
        return isPrint ? android.util.Log.e(tag.getClass().getSimpleName(), msg) : -1;
    }

    /**
     * ******************************* format log  **********************************
     */
    public static int v(String Tag, String format, Object... msg) {
        return isPrint ? android.util.Log.v(Tag, buildMessage(format, msg)) : -1;
    }
    public static int d(String Tag, String format, Object... msg) {
        return isPrint ? android.util.Log.d(Tag, buildMessage(format, msg)) : -1;
    }
    public static int i(String Tag, String format, Object... msg) {
        return isPrint ? android.util.Log.i(Tag, buildMessage(format, msg)) : -1;
    }
    public static int w(String Tag, String format, Object... msg) {
        return isPrint ? android.util.Log.w(Tag, buildMessage(format, msg)) : -1;
    }
    public static int e(String Tag, String format, Object... msg) {
        return isPrint ? android.util.Log.e(Tag, buildMessage(format, msg)) : -1;
    }

    /**
     * Formats the caller's provided message and prepends useful info like
     * calling thread ID and method name.
     */
    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.CHINA, format, args);

        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();
        String caller = "<unknown>";
        /*
            Walk up the stack looking for the first caller outside the Log
            It will be at least two frames up, so start there
         */
        for(int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if(!clazz.equals(Log.class)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.CHINA, "[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
    }


    static class MarkerLog {
        /**  */
        private static final long MIN_DURATION_FOR_LOGGING_MS = 0;
        private static class Marker {
            public final String name;
            public final long thread;
            public final long time;

            public Marker(String name, long thread, long time) {
                this.name = name;
                this.thread = thread;
                this.time = time;
            }
        }
        private List<Marker> mMarkers = new ArrayList<>();
        private boolean mFinished = false;

        //add a marker to this log with specified name
        public synchronized void add(String name, long threadId) {
            if(mFinished) {
                throw new IllegalStateException("Marker added to finished log");
            }
            mMarkers.add(new Marker(name, threadId, SystemClock.elapsedRealtime()));
        }


        /**
         * close log , dumping it to logcat if the time difference between the first and last marker
         * is greater the {@link #MIN_DURATION_FOR_LOGGING_MS}.
         *
         * @param header Header String to print Above the marker log
         */
        public synchronized void finish(String header, String tag) {
            mFinished = true;

            long duration = getTotalDuration();
            if(duration <= MIN_DURATION_FOR_LOGGING_MS) {
                return;
            }

            long preTime = mMarkers.get(0).time;
            d(tag, "(%-4d ms) %s", duration, header);
            for(Marker marker : mMarkers) {
                long thisTime = marker.time;
                d("(+%-4d) [%2d] %s", (thisTime - preTime), marker.thread, marker.name);
                preTime = thisTime;
            }
        }
        /** return the time difference between the first and last events in this log*/
        private long getTotalDuration() {
            if(mMarkers.size() == 0) {
                return 0;
            }
            long first = mMarkers.get(0).time;
            long last = mMarkers.get(mMarkers.size() - 1).time;
            return last - first;
        }

        @Override
        protected void finalize() throws Throwable {
            //catch requests that have been collected (and hence end-of-lifed)
            //but had no debugging output printed for them
            if(!mFinished) {
                finish("Request on the loose", "");
                e("", "Marker log finalized without finish() - uncaught exit point for request");

            }
        }
    }

}
