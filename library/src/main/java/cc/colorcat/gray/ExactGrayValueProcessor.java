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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Author: cxx
 * Date: 2020-01-03
 * GitHub: https://github.com/ccolorcat
 */
abstract class ExactGrayValueProcessor<A extends Annotation> implements AnnotationProcessor {
    @Override
    public Object process(GrayProvider provider, Method method, Object[] args) {
        A annotation = getAnnotation(method);
        if (annotation == null) return null;
        GrayParser parser = getGrayParser(annotation);
        String grayKey = parser.getGrayKey();
        try {
            String rawData = provider.get(grayKey);
            if (rawData != null) {
                return parser.parse(rawData);
            }
            Logger.w("The gray key(" + grayKey + ") has null value.");
        } catch (Throwable throwable) {
            Logger.e(Logger.TAG, "error on parse grayValue by " + grayKey, throwable);
        }
        return parser.getGrayDefaultValue();
    }

    @Nullable
    abstract A getAnnotation(@NonNull Method method);

    @NonNull
    abstract GrayParser getGrayParser(@NonNull A a);
}
