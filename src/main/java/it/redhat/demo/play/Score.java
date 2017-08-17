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

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Score {

    private long start;
    private long simple = 0l;
    private long complex = 0l;

    public void start() {
        this.start = System.currentTimeMillis();
    }

    private BigDecimal getTime() {
        long time = System.currentTimeMillis() - start;
        if (time == 0l) {
            time = 1l;
        }

        return new BigDecimal(time);
    }

    private BigDecimal getSimpleScore(BigDecimal time) {

        return new BigDecimal(simple).divide(time, 2, RoundingMode.HALF_UP);

    }

    private BigDecimal getComplexScore(BigDecimal time) {

        return new BigDecimal(complex).divide(time, 2, RoundingMode.HALF_UP);

    }

    public void addSimple() {
        simple++;
    }

    public void addComplex() {
        complex++;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{ ");

        BigDecimal time = getTime();
        BigDecimal simpleScore = getSimpleScore(time);
        BigDecimal complexScore = getComplexScore(time);

        builder.append("Time: ");
        builder.append(time);
        builder.append(" - Simple: ");
        builder.append(simple);
        builder.append(" - Complex: ");
        builder.append(complex);
        builder.append(" - Simple Score: ");
        builder.append(simpleScore);
        builder.append(" - Complex Score: ");
        builder.append(complexScore);

        builder.append(" }");

        return builder.toString();
    }
}
