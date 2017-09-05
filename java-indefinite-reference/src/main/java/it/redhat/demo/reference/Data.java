/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/
package it.redhat.demo.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public final class Data {

    private Map<String, Integer> data = new HashMap<>();

    public Data() {}

    public void add(String key, Integer value) {
        data.put(key, value);
    }

    public long result() {

        Map<Boolean, Integer> evenOddSplit = data.values().stream()
                .collect(Collectors.partitioningBy(x -> x % 2 == 0,
                        Collectors.summingInt(Integer::intValue)));

        return evenOddSplit.get(Boolean.TRUE) - evenOddSplit.get(Boolean.FALSE);

    }

}
