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

    // ex -Xmx200m -XX:+PrintGCDetails -XX:+PrintReferenceGC 10 10
    public static void main(String[] args) {

        int softTimes = 70;
        int weakTimes = 70;

        if (args.length == 2) {
            softTimes = Integer.parseInt(args[0]);
            weakTimes = Integer.parseInt(args[1]);
        }

        ExecutorService softExecutor = Executors.newFixedThreadPool(5);
        ExecutorService weakExecutor = Executors.newFixedThreadPool(5);

        runSoft(softExecutor, softTimes);
        runWeak(weakExecutor, weakTimes);

        waitExecutor(softExecutor, weakExecutor);

    }

    // [Full GC (Ergonomics) [SoftReference, 0 refs, 0.0000117 secs][WeakReference, 90 refs, 0.0000179 secs][FinalReference, 43 refs, 0.0000041 secs][PhantomReference, 0 refs, 0 refs, 0.0000028 secs][JNI Weak Reference, 0.0000210 secs][PSYoungGen: 28672K->13382K(47104K)] [ParOldGen: 136661K->136655K(136704K)] 165333K->150037K(183808K), [Metaspace: 6340K->6212K(1056768K)], 0.5557818 secs] [Times: user=3.91 sys=0.06, real=0.55 secs]
    // [Full GC (Ergonomics) [SoftReference, 0 refs, 0.0000122 secs][WeakReference, 311 refs, 0.0000620 secs][FinalReference, 8 refs, 0.0000172 secs][PhantomReference, 0 refs, 0 refs, 0.0000026 secs][JNI Weak Reference, 0.0000067 secs][PSYoungGen: 28672K->14464K(47104K)] [ParOldGen: 136655K->136650K(136704K)] 165327K->151114K(183808K), [Metaspace: 10349K->10347K(1058816K)], 0.5941806 secs] [Times: user=3.69 sys=0.09, real=0.59 secs]
    // [Full GC (Ergonomics) [SoftReference, 421 refs, 0.0001244 secs][WeakReference, 354 refs, 0.0000968 secs][FinalReference, 11 refs, 0.0000155 secs][PhantomReference, 0 refs, 0 refs, 0.0000029 secs][JNI Weak Reference, 0.0000110 secs][PSYoungGen: 28672K->14179K(47104K)] [ParOldGen: 136650K->136566K(136704K)] 165322K->150745K(183808K), [Metaspace: 10584K->10464K(1058816K)], 0.7726767 secs] [Times: user=5.00 sys=0.09, real=0.77 secs]
    private static void runWeak(ExecutorService weakExecutor, int times) {
        for (int i = 0; i<times; i++) {
            WeakTask weakTask = new WeakTask();

            for (int j=0; j<100; j++) {
                weakExecutor.submit(weakTask);
            }

            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                LOG.error("Sleep interrupted!");
            }
        }
    }


    // [GC (Allocation Failure) [SoftReference, 0 refs, 0.0000228 secs][WeakReference, 0 refs, 0.0000023 secs][FinalReference, 0 refs, 0.0000019 secs][PhantomReference, 0 refs, 0 refs, 0.0000031 secs][JNI Weak Reference, 0.0000132 secs][PSYoungGen: 42112K->15104K(45568K)] 90770K->71626K(114688K), 0.0140055 secs] [Times: user=0.10 sys=0.00, real=0.02 secs]
    // [GC (Allocation Failure) [SoftReference, 0 refs, 0.0000111 secs][WeakReference, 0 refs, 0.0000020 secs][FinalReference, 0 refs, 0.0000018 secs][PhantomReference, 0 refs, 0 refs, 0.0000028 secs][JNI Weak Reference, 0.0000059 secs][PSYoungGen: 38144K->7872K(45568K)] 94666K->75578K(114688K), 0.0123027 secs] [Times: user=0.09 sys=0.00, real=0.01 secs]
    // [Full GC (Ergonomics) [SoftReference, 53 refs, 0.0000373 secs][WeakReference, 228 refs, 0.0000462 secs][FinalReference, 7 refs, 0.0000047 secs][PhantomReference, 0 refs, 0 refs, 0.0000035 secs][JNI Weak Reference, 0.0000233 secs][PSYoungGen: 7872K->2068K(45568K)] [ParOldGen: 67706K->68663K(133632K)] 75578K->70731K(179200K), [Metaspace: 10458K->10458K(1058816K)], 0.3228103 secs] [Times: user=1.94 sys=0.03, real=0.32 secs]
    // [Full GC (Ergonomics) [SoftReference, 53 refs, 0.0000212 secs][WeakReference, 207 refs, 0.0000225 secs][FinalReference, 6 refs, 0.0000027 secs][PhantomReference, 0 refs, 0 refs, 0.0000030 secs][JNI Weak Reference, 0.0000072 secs][PSYoungGen: 23040K->12902K(45568K)] [ParOldGen: 136652K->136652K(136704K)] 159692K->149554K(182272K), [Metaspace: 10455K->10455K(1058816K)], 0.4653517 secs] [Times: user=3.22 sys=0.06, real=0.46 secs]
    // [Full GC (Ergonomics) [SoftReference, 42 refs, 0.0000190 secs][WeakReference, 214 refs, 0.0000355 secs][FinalReference, 7 refs, 0.0000025 secs][PhantomReference, 0 refs, 0 refs, 0.0000025 secs][JNI Weak Reference, 0.0000066 secs][PSYoungGen: 23040K->14597K(45568K)] [ParOldGen: 136652K->136652K(136704K)] 159692K->151249K(182272K), [Metaspace: 10455K->10455K(1058816K)], 0.4357532 secs] [Times: user=3.19 sys=0.03, real=0.44 secs]
    // 13:23:01.702 [pool-2-thread-2] WARN it.redhat.demo.reference.weak.WeakReferenceService - --> Recreate Data Weak reference <--
    private static void runSoft(ExecutorService softExecutor, int times) {
        for (int i = 0; i<times; i++) {
            SoftTask softTask = new SoftTask();

            for (int j=0; j<100; j++) {
                softExecutor.submit(softTask);
            }

            try {
                Thread.sleep(10000l);
            } catch (InterruptedException e) {
                LOG.error("Sleep interrupted!");
            }
        }
    }

    private static void waitExecutor(ExecutorService softExecutor, ExecutorService weakExecutor) {
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
