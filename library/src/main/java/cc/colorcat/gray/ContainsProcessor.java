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

import java.lang.reflect.Method;

/**
 * Author: cxx
 * Date: 2020-01-03
 * GitHub: https://github.com/ccolorcat
 */
class ContainsProcessor implements AnnotationProcessor {
    @Override
    public Object process(GrayProvider provider, Method method, Object[] args) {
        ContainsGray containsGray = method.getAnnotation(ContainsGray.class);
        if (containsGray == null) return null;
        String rawData = provider.get(containsGray.key());
        if (rawData == null) return false;
        String containedValue = containsGray.containedValue();
        if (Utils.isNotEmpty(containedValue)) {
            return rawData.contains(containedValue);
        }
        if (args == null || args.length != 1 || args[0].getClass() != String.class) {
            throw new IllegalArgumentException("ContainsGray's containedValue is empty, the " + method.getName()
                    + " must have and only have one String value.");
        }
        return rawData.contains(args[0].toString());
    }
}
