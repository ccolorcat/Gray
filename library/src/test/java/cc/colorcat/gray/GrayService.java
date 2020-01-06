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
 * Date: 2020-01-03
 * GitHub: https://github.com/ccolorcat
 */
public interface GrayService {
    @BoolGray(key = "booleanTrue")
    boolean testBooleanTrue();

    @BoolGray(key = "booleanDefaultTrue", defaultValue = true)
    boolean testBooleanDefaultTrue();

    @BoolGray(key = "booleanFalse")
    boolean testBooleanFalse();

    @BoolGray(key = "booleanDefaultFalse")
    boolean testBooleanDefaultFalse();

    @IntGray(key = "int")
    int testInt();

    @IntGray(key = "intDefault", defaultValue = 12)
    int testIntDefaultValue();

    @FloatGray(key = "float")
    float testFloat();

    @FloatGray(key = "floatDefault", defaultValue = 23.89F)
    float testFloatDefaultValue();

    @DoubleGray(key = "double")
    double testDouble();

    @DoubleGray(key = "doubleDefault", defaultValue = 3.1415926)
    double testDoubleDefaultValue();

    @StringGray(key = "string")
    String testString();

    @StringGray(key = "stringDefault", defaultValue = "default string")
    String testStringDefaultValue();

    @MatchGray(key = "match", matchedValue = "matched value")
    boolean testMatch();

    @MatchGray(key = "matchDynamic")
    boolean testMatch(String matchValue);

    @ContainsGray(key = "containsTrue", containedValue = "test")
    boolean testContainsTrue();

    @ContainsGray(key = "containsFalse", containedValue = "haha")
    boolean testContainsFalse();

    @ContainsGray(key = "containsDynamic")
    boolean testContains(String containsValue);

    String testDynamicExists(@DynamicGray String key);

    String testDynamicDefaultValue(@DynamicGray("dynamic default value") String key);

    String testDynamicDefaultValue2(@DynamicGray String key, String defaultValue);
}
