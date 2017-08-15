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

import java.util.List;
import java.util.Random;

public class PermanentData extends Data {

    private int magic;

    // 4       it.redhat.demo.play.PermanentData::<init> (120 bytes)   made not entrant
    // 4       it.redhat.demo.play.PermanentData::<init> (120 bytes)
    // 3       it.redhat.demo.play.PermanentData::<init> (120 bytes)   made not entrant
    public PermanentData(List<PermanentData> otherPerm, List<Data> tran) {
        super();
        Random random = new Random();

        // %     4       it.redhat.demo.play.Runner::run @ -2 (35 bytes)   made not entrant
        // %     4       it.redhat.demo.play.PermanentData::<init> @ 15 (120 bytes)
        for (int i=0; i<otherPerm.size(); i++) {

            PermanentData data = otherPerm.get(i);
            if (random.nextBoolean()) {
                magic += data.getMagic();
                magic -= tran.size();
            } else {
                // made not entrant
                magic -= data.getMagic();
                magic += tran.size();
            }

        }

        tran.clear();

    }

    public int getMagic() {
        return magic;
    }

    @Override
    public String toString() {
        return " " + magic + " ";
    }

}
