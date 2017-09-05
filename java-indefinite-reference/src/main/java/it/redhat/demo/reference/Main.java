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

import it.redhat.demo.reference.soft.SoftTask;
import it.redhat.demo.reference.weak.WeakTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Fabio Massimo Ercoli (C) 2017 Red Hat Inc.
 */
public class Main {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        ExecutorService softExecutor = Executors.newFixedThreadPool(5);
        ExecutorService weakExecutor = Executors.newFixedThreadPool(5);

        SoftTask softTask = new SoftTask();
        WeakTask weakTask = new WeakTask();

        for (int i=0; i<100; i++) {
            softExecutor.submit(softTask);
            weakExecutor.submit(weakTask);
        }

        try {

            LOG.debug("Attempt to shutdown executor");
            weakExecutor.shutdown();
            softExecutor.shutdown();
            weakExecutor.awaitTermination(1000, TimeUnit.SECONDS);
            softExecutor.awaitTermination(1000, TimeUnit.SECONDS);

        } catch (InterruptedException e) {

            LOG.error("Tasks interrupted!");

        } finally {

            if (!softExecutor.isTerminated()) {
                LOG.warn("Cancel non-finished tasks");
            }
            if (!weakExecutor.isTerminated()) {
                LOG.warn("Cancel non-finished tasks on weakExecutor");
            }

            softExecutor.shutdownNow();
            weakExecutor.shutdownNow();

            LOG.debug("Shutdown finished!");

        }

    }

}
