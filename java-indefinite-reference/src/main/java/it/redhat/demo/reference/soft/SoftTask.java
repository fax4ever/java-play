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
package it.redhat.demo.reference.soft;

import it.redhat.demo.reference.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class SoftTask implements Callable<Long> {

    private static Logger LOG = LoggerFactory.getLogger(SoftTask.class);

    private static ThreadLocal<Long> localCache = new ThreadLocal();

    @Override
    public Long call() throws Exception {

        if (localCache.get() == null) {
            LOG.info("Taking Data from Shared Service");
            Data data = SoftReferenceService.getData();
            localCache.set(data.result());
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Soft Result " + localCache.get());
        }

        return localCache.get();
    }
    
}
