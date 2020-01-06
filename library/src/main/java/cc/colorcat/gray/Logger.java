/*
 * Copyright 2020 cxx
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cc.colorcat.gray;

import android.util.Log;

/**
 * Author: cxx
 * Date: 2019-12-29
 * GitHub: https://github.com/ccolorcat
 */
final class Logger {
    static final String TAG = "GRAY";
    private static final int MAX_LENGTH = 1024 * 2;
    private static int sThreshold = Log.ASSERT + 10;

    static void setThreshold(int threshold) {
        sThreshold = threshold;
    }

    static void v(String msg) {
        v(TAG, msg);
    }

    static void v(String tag, String msg) {
        log(tag, msg, Log.VERBOSE);
    }

    static void d(String msg) {
        d(TAG, msg);
    }

    static void d(String tag, String msg) {
        log(tag, msg, Log.DEBUG);
    }

    static void i(String msg) {
        i(TAG, msg);
    }

    static void i(String tag, String msg) {
        log(tag, msg, Log.INFO);
    }

    static void w(String msg) {
        w(TAG, msg);
    }

    static void w(String tag, String msg) {
        log(tag, msg, Log.WARN);
    }

    static void e(String msg) {
        e(TAG, msg);
    }

    static void e(String tag, String msg) {
        log(tag, msg, Log.ERROR);
    }

    static void e(Throwable throwable) {
        e(TAG, throwable);
    }

    static void e(String tag, Throwable throwable) {
        e(tag, "", throwable);
    }

    static void e(String tag, String msg, Throwable throwable) {
        if (Log.ERROR > sThreshold && tag != null && msg != null) {
            Log.e(tag, msg, throwable);
        }
    }

    private static void log(String tag, String msg, int priority) {
        if (tag == null || msg == null || priority < sThreshold) {
            return;
        }
        final int length = msg.length();
        if (length <= MAX_LENGTH) {
            Log.println(priority, tag, msg);
            return;
        }
        for (int start = 0, end; start < length; start = end) {
            end = friendlyEnd(msg, start, Math.min(start + MAX_LENGTH, length));
            Log.println(priority, tag, msg.substring(start, end));
        }
    }

    private static int friendlyEnd(String msg, int start, int end) {
        if (msg.length() == end || msg.charAt(end) == '\n') {
            return end;
        }
        for (int last = end - 1; last > start; --last) {
            if (msg.charAt(last) == '\n') {
                return last + 1;
            }
        }
        return end;
    }

    private Logger() {
        throw new AssertionError("no instance");
    }
}
