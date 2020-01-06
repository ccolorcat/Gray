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

import java.lang.reflect.Method;

/**
 * Author: cxx
 * Date: 2020-01-03
 * GitHub: https://github.com/ccolorcat
 */
class FloatProcessor extends ExactGrayValueProcessor<FloatGray> {
    @Nullable
    @Override
    FloatGray getAnnotation(@NonNull Method method) {
        return method.getAnnotation(FloatGray.class);
    }

    @NonNull
    @Override
    GrayParser getGrayParser(@NonNull final FloatGray floatGray) {
        return new GrayParser() {
            @NonNull
            @Override
            String getGrayKey() {
                return floatGray.key();
            }

            @NonNull
            @Override
            Object getGrayDefaultValue() {
                return floatGray.defaultValue();
            }

            @Override
            Object parse(String rawGray) {
                return Float.parseFloat(rawGray);
            }
        };
    }
}
