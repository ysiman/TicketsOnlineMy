package org.itsimulator.germes.app.service.transform.impl;

import org.itsimulator.germes.app.infra.util.ReflectionUtil;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.powermock.modules.junit4.PowerMockRunner;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import java.util.Collections;
import java.util.List;

/**
 * Verifies functionality of the {@link SimpleDTOTransformer} unit
 *
 * @author Morenets
 *
 */
@RunWith(JMockit.class)
public class CachedFieldProviderTest2 {
    private FieldProvider provider;

    @Mocked
    private final ReflectionUtil reflectionUtil = null;

    @Before
    public void setup() {
        provider = new CachedFieldProvider();
    }

    @Test
    public void testGetFieldNamesSuccess() {
        List<String> fields = provider.getFieldNames(Source2.class, Destination2.class);
        assertFalse(fields.isEmpty());
        assertTrue(fields.contains("value"));
    }

    @Test
    public void testGetFieldNamesCachedSuccess() {
        List<String> fields = provider.getFieldNames(Source2.class, Destination2.class);
        List<String> fields2 = provider.getFieldNames(Source2.class, Destination2.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

    @Test
    @Ignore
    public void testGetFieldNamesAreCached() {
        List<String> fields = provider.getFieldNames(Source2.class, Destination2.class);

        PowerMockito.mockStatic(ReflectionUtil.class);
        when(ReflectionUtil.findSimilarFields(Source2.class, Destination2.class)).thenReturn(Collections.emptyList());

        assertTrue(ReflectionUtil.findSimilarFields(Source2.class, Destination2.class).isEmpty());
        List<String> fields2 = provider.getFieldNames(Source2.class, Destination2.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

    @Test
    public void testGetFieldNamesAreCachedUsingJMockit() {
        new Expectations() {
            {
                ReflectionUtil.findSimilarFields(Source2.class, Destination2.class);
                result = Collections.singletonList("name");
            }
        };

        List<String> fields = provider.getFieldNames(Source2.class, Destination2.class);

        new Expectations() {
            {
                ReflectionUtil.findSimilarFields(Source2.class, Destination2.class);
                result = Collections.emptyList();
            }
        };

        assertTrue(ReflectionUtil.findSimilarFields(Source2.class, Destination2.class).isEmpty());
        List<String> fields2 = provider.getFieldNames(Source2.class, Destination2.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

    public static final class MockReflectionUtil extends MockUp<ReflectionUtil> {
        protected static List<String> fields;

        @Mock
        public static List<String> findSimilarFields(Class<?> clz1, Class<?> clz2) {
            return fields;
        }
    }

    @Test
    public void testGetFieldNamesAreCachedUsingMockupsAPI() {
        new MockReflectionUtil();

        MockReflectionUtil.fields = Collections.singletonList("name");

        List<String> fields = provider.getFieldNames(Source2.class, Destination2.class);

        MockReflectionUtil.fields = Collections.emptyList();

        assertTrue(ReflectionUtil.findSimilarFields(Source2.class, Destination2.class).isEmpty());
        List<String> fields2 = provider.getFieldNames(Source2.class, Destination2.class);
        assertFalse(fields.isEmpty());
        assertEquals(fields, fields2);
    }

}

class Source2 {
    int value;
}

class Destination2 {
    int value;
}
