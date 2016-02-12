/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (c) 2016-2016 Pentaho Corporation.
// All Rights Reserved.
*/
package mondrian.rolap;

import mondrian.olap.Member;
import mondrian.test.FoodMartTestCase;
import mondrian.util.Bug;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit Test for {@link RolapMemberBase}.
 */
public class RolapMemberBaseTest extends FoodMartTestCase {

    private static final String PROPERTY_NAME = "property1";

    // property values and their expected formatted values
    private static final Map<Object, String> PROPERTY_VALUES =
            new HashMap<Object, String>() {{
        put("String1", "String1");
        put(123456, "123456");
        put(123456L, "123456");
        put(123.456, "123.456");
        put(123.456f, "123.456");
        put(123.456d, "123.456");
        put(1.23456e2, "123.456");
        put(0.123456, "0.123456");
    }};
    private static final Map<Object, String> PROPERTY_VALUES_E_NOTATION =
            new HashMap<Object, String>() {{
        put(1200000000000000.0, "1200000000000000");
        put(0.0000000000021, "0.0000000000021");
    }};

    private RolapMemberBase rolapMemberBase;

    @Override
    public void setUp() {
        RolapLevel level = mock(RolapLevel.class);
        RolapHierarchy hierarchy = mock(RolapHierarchy.class);
        RolapDimension dimension = mock(RolapDimension.class);
        RolapProperty property = mock(RolapProperty.class);
        RolapProperty[] properties = {property};
        when(level.getHierarchy()).thenReturn(hierarchy);
        when(hierarchy.getDimension()).thenReturn(dimension);
        when(level.getProperties()).thenReturn(properties);
        when(property.getName()).thenReturn(PROPERTY_NAME);
        rolapMemberBase = new RolapMemberBase(
            mock(RolapMember.class),
            level,
            1,
            null,
            Member.MemberType.REGULAR);
    }

    /**
     * Test for {@link RolapMemberBase#getPropertyFormattedValue(String)}.
     *
     * Given that the property value is a number.
     *
     * When the formatted value is requested, then the output should not contain
     * any unwanted decimal values due to floating point representation.
     */
    public void testPropertyValuesFormattingFloatingPoint() {
        for (Map.Entry<Object, String> entry : PROPERTY_VALUES.entrySet()) {
            rolapMemberBase.setProperty(PROPERTY_NAME, entry.getKey());

            String formattedValue =
                    rolapMemberBase.getPropertyFormattedValue(PROPERTY_NAME);

            assertEquals(entry.getValue(), formattedValue);
        }
    }

    /**
     * Test for {@link RolapMemberBase#getPropertyFormattedValue(String)}.
     *
     * Given that the property value is a number.
     *
     * When the formatted value is requested,
     * then the output should not contain any scientific notations.
     */
    public void testPropertyValuesFormattingScientificNotationNeglecting() {
        if (Bug.BugScientificNotationNeglectFixed) {
            for (Map.Entry<Object, String> entry
                    : PROPERTY_VALUES_E_NOTATION.entrySet())
            {
                rolapMemberBase.setProperty(PROPERTY_NAME, entry.getKey());

                String formattedValue = rolapMemberBase
                        .getPropertyFormattedValue(PROPERTY_NAME);

                assertEquals(entry.getValue(), formattedValue);
            }
        }
    }
}
// End RolapMemberBaseTest.java