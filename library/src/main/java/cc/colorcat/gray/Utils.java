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

/**
 * Author: cxx
 * Date: 2020-01-02
 * GitHub: https://github.com/ccolorcat
 */
final class Utils {
    static <T> T requireNonNull(T t, String msg) {
        if (t == null) throw new NullPointerException(msg);
        return t;
    }

    static <T> T nullElse(T value, T elseValue) {
        return value != null ? value : elseValue;
    }

    static boolean isNotEmpty(CharSequence text) {
        return text != null && text.length() != 0;
    }

    static boolean equals(CharSequence a, CharSequence b) {
        return (a == b) || (a != null && a.equals(b));
    }

    private Utils() {
        throw new AssertionError("no instance");
    }
}
