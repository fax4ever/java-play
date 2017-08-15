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

package it.redhat.demo.play;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {

    public static final int TIMES = 1000000000;
    public int module = 1000;
    private List<PermanentData> perms = new ArrayList<PermanentData>();
    private List<Data> tran = new ArrayList<Data>();

    public static void main(String[] args) {

        Runner runner = new Runner();
        runner.run();

    }

    //  4       it.redhat.demo.play..Runner:persist (62 bytes)   made not entrant
    //  4       it.redhat.demo.play..Runner:persist (62 bytes)
    //  4       it.redhat.demo.play..Runner:persist (62 bytes)   made not entrant
    private void persist() {

        Random random = new Random();

        module = random.nextInt(2000) + 1;
        perms.add(new PermanentData(perms, tran));
        int size = perms.size();

        if (size % 100 == 0) {
            System.out.println(size);
        }

    }

    // 4       it.redhat.demo.play.Runner::tran (20 bytes)   COMPILE SKIPPED: concurrent class loading
    // This is often a bug in the compiler, but the usual remedy in all cases is to refactor the code into some‐ thing simpler that the compiler can handle
    // 4       it.redhat.demo.play.Runner::tran (20 bytes)
    // 3       it.redhat.demo.play.Runner::tran (20 bytes)
    private void tran() {

        Data data = new Data();
        tran.add(data);

    }

    // 4       it.redhat.demo.play.Runner::tran (20 bytes)   made not entrant
    // 3       it.redhat.demo.play.Runner::run (35 bytes)
    private void run() {

        // %     4       it.redhat.demo.play.Runner::run @ 2 (35 bytes)
        // %     3       it.redhat.demo.play.Runner::run @ -2 (35 bytes)   made not entrant
        // %     4       it.redhat.demo.play.Runner::run @ -2 (35 bytes)   made not entrant
        // %     3       it.redhat.demo.play.Runner::run @ 2 (35 bytes)
        // %     4       it.redhat.demo.play.Runner::run @ 2 (35 bytes)
        // %     3       it.redhat.demo.play.Runner::run @ -2 (35 bytes)   made not entrant
        for (int i=0; i<TIMES; i++) {

            if (i % module == 0) {
                persist();
            } else {
                tran();
            }

        }

    }

}
