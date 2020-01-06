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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Author: cxx
 * Date: 2020-01-03
 * GitHub: https://github.com/ccolorcat
 */
class DynamicProcessor implements AnnotationProcessor {
    @Override
    public Object process(GrayProvider provider, Method method, Object[] args) {
        DynamicGray dynamicGray = findDynamicGray(method);
        if (dynamicGray == null) return null;
        checkReturnType(method);
        final int size = checkArgsAndReturnSize(method, args);
        String rawData = provider.get(args[0].toString());
        String defaultValue = size == 2 ? args[1].toString() : dynamicGray.value();
        return Utils.nullElse(rawData, defaultValue);
    }

    private static void checkReturnType(Method method) {
        if (method.getReturnType() != String.class) {
            throw new IllegalArgumentException("The method " + method.getName()
                    + " annotated by @DynamicGray must return String type.");
        }
    }

    private static int checkArgsAndReturnSize(Method method, Object[] args) {
        int length = args.length;
        if (length > 2) {
            throw new IllegalArgumentException("The method " + method.getName() + " must have 1 or 2 parameters.");
        }
        for (Object arg : args) {
            if (arg.getClass() != String.class) {
                throw new IllegalArgumentException("The method " + method.getName() + "'s parameter must be String.");
            }
        }
        return length;
    }

    private static DynamicGray findDynamicGray(Method method) {
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0, length = annotations.length; i < length; ++i) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType() != DynamicGray.class) continue;
                if (i != 0) {
                    throw new IllegalArgumentException("@DynamicGray must be apply to the first parameter @" + method.getName());
                }
                return (DynamicGray) annotation;
            }
        }
        return null;
    }
}
