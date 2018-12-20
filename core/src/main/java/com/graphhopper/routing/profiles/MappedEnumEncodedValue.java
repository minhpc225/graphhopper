/*
 *  Licensed to GraphHopper GmbH under one or more contributor
 *  license agreements. See the NOTICE file distributed with this work for
 *  additional information regarding copyright ownership.
 *
 *  GraphHopper GmbH licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in
 *  compliance with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.graphhopper.routing.profiles;

import com.graphhopper.storage.IntsRef;

import java.util.List;

/**
 * This class implements an EnumEncodedValue and holds an array of EnumAlike objects. It stores just the indices
 * of the used EnumAlike objects.
 */
public final class MappedEnumEncodedValue extends SimpleIntEncodedValue implements EnumEncodedValue {
    private final EnumAlike[] map;

    public MappedEnumEncodedValue(String name, List<? extends EnumAlike> values) {
        super(name, 32 - Integer.numberOfLeadingZeros(values.size()));

        map = values.toArray(new EnumAlike[]{});
    }

    @Override
    public final void setEnum(boolean reverse, IntsRef ref, EnumAlike value) {
        int intValue = value.ordinal();
        super.setInt(reverse, ref, intValue);
    }

    @Override
    public final EnumAlike getEnum(boolean reverse, IntsRef ref) {
        int value = super.getInt(reverse, ref);
        return map[value];
    }
}
