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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: cxx
 * Date: 2020-01-02
 * GitHub: https://github.com/ccolorcat
 */
class GrayProxy implements InvocationHandler {
    private static final Map<Class<?>, List<AnnotationProcessor>> PROCESSORS = new HashMap<>();

    static {
        PROCESSORS.put(Boolean.TYPE, Arrays.asList(new BoolProcessor(), new MatchProcessor(), new ContainsProcessor()));
        PROCESSORS.put(Integer.TYPE, Collections.<AnnotationProcessor>singletonList(new IntProcessor()));
        PROCESSORS.put(Float.TYPE, Collections.<AnnotationProcessor>singletonList(new FloatProcessor()));
        PROCESSORS.put(Double.TYPE, Collections.<AnnotationProcessor>singletonList(new DoubleProcessor()));
        PROCESSORS.put(String.class, Arrays.asList(new StringProcessor(), new DynamicProcessor()));
    }

    @NonNull
    private final GrayProvider mProvider;

    GrayProxy(@NonNull GrayProvider provider) {
        mProvider = Utils.requireNonNull(provider, "provider == null");
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Class<?> returnType = method.getReturnType();
        List<AnnotationProcessor> processors = PROCESSORS.get(returnType);
        if (processors == null) {
            throw new IllegalArgumentException("Unsupported return type(boolean, int, float, double, String): " + returnType);
        }
        Object result = null;
        for (AnnotationProcessor processor : processors) {
            result = processor.process(mProvider, method, args);
            if (result != null) {
                break;
            }
        }
        if (result == null) {
            throw new IllegalArgumentException("The method " + method.getName()
                    + "'s returnType does not math it's annotation.");
        }
        return result;
    }
}
