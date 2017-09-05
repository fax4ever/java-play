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
package it.redhat.demo.reference.weak;

import it.redhat.demo.reference.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.UUID;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class WeakReferenceService {

    private static Logger LOG = LoggerFactory.getLogger(WeakReferenceService.class);

    private static final int VOLUMES = 1000000;
    private static final int BOUND = 7;

    private static WeakReferenceService instance;

    private WeakReference<Data> cache;

    private WeakReferenceService() {}

    public static synchronized Data getData() {
        if (instance == null) {
            instance = new WeakReferenceService();
            LOG.info("Create WeakReferenceService instance");
        }

        if (instance.cache == null) {
            instance.cache = new WeakReference<>(build());
            LOG.info("Create Data Weak reference");
        }

        if (instance.cache.get() == null) {
            instance.cache = new WeakReference<>(build());
            LOG.warn("--> Recreate Data Weak reference <--");
        }

        return instance.cache.get();
    }

    private static Data build() {

        Random randomGenerator = new Random();
        Data dataResult = new Data();

        for (int i = 0; i< VOLUMES; i++) {

            dataResult.add(UUID.randomUUID().toString(), randomGenerator.nextInt(BOUND));

        }

        return dataResult;

    }

}
