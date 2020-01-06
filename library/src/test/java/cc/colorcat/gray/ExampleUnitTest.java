package cc.colorcat.gray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private Map<String, String> rawGray = new HashMap<>();

    {
        rawGray.put("booleanTrue", Boolean.TRUE.toString());
        rawGray.put("booleanFalse", Boolean.FALSE.toString());
        rawGray.put("int", "100");
        rawGray.put("float", "89.2F");
        rawGray.put("double", "0.98");
        rawGray.put("string", "string value");
        rawGray.put("match", "matched value");
        rawGray.put("matchDynamic", "dynamic matched value");
        rawGray.put("containsTrue", "this is a test");
        rawGray.put("containsFalse", "hihi");
        rawGray.put("containsDynamic", "contains dynamic value");
        rawGray.put("dynamicExists", "dynamic exists value");
    }

    @Test
    public void testGrayService() {
        GrayFactory factory = new GrayFactory(new GrayProvider() {
            @Nullable
            @Override
            public String get(@NonNull String key) {
                return rawGray.get(key);
            }
        });
        GrayService service = factory.create(GrayService.class);
        assertTrue(service.testBooleanTrue());
        assertTrue(service.testBooleanDefaultTrue());
        assertFalse(service.testBooleanFalse());
        assertFalse(service.testBooleanDefaultFalse());
        assertEquals(service.testInt(), 100);
        assertEquals(service.testIntDefaultValue(), 12);
        assertEquals(service.testFloat(), 89.2F, 0.00001F);
        assertEquals(service.testFloatDefaultValue(), 23.89F, 0.00001F);
        assertEquals(service.testDouble(), 0.98, 0.00001);
        assertEquals(service.testDoubleDefaultValue(), 3.1415926, 0.00001);
        assertEquals(service.testString(), "string value");
        assertEquals(service.testStringDefaultValue(), "default string");
        assertTrue(service.testMatch());
        assertTrue(service.testMatch("dynamic matched value"));
        assertFalse(service.testMatch("dynamic"));
        assertTrue(service.testContainsTrue());
        assertFalse(service.testContainsFalse());
        assertTrue(service.testContains("dynamic value"));
        assertFalse(service.testContains("this"));
        assertEquals(service.testDynamicExists("dynamicExists"), "dynamic exists value");
        assertEquals(service.testDynamicDefaultValue("test"), "dynamic default value");
        assertEquals(service.testDynamicDefaultValue2("test", "test value"), "test value");
    }
}