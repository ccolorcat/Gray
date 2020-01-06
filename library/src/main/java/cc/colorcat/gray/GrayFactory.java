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

import java.lang.reflect.Proxy;

/**
 * Author: cxx
 * Date: 2020-01-03
 * GitHub: https://github.com/ccolorcat
 */
public class GrayFactory {
    private final GrayProvider mProvider;

    public GrayFactory(@NonNull GrayProvider provider) {
        mProvider = Utils.requireNonNull(provider, "provider == null");
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new GrayProxy(mProvider)
        );
    }
}
