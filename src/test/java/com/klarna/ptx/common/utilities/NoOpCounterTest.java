package com.klarna.ptx.common.utilities;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.metrics.SimpleCounter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

class NoOpCounterTest {

    private NoOpCounter<String> subject = new NoOpCounter<>("application", "counter");

    @Test
    public void testMapIncrementsCounter() {
        Configuration configMock = Mockito.mock(Configuration.class);
        RuntimeContext contextMock = Mockito.mock(RuntimeContext.class, Answers.RETURNS_DEEP_STUBS);
        Mockito.when(contextMock.getMetricGroup()
                .addGroup(Mockito.anyString())
                .addGroup(Mockito.anyString(), Mockito.anyString())
                .addGroup(Mockito.anyString())
                .counter(Mockito.anyString())).thenReturn(new SimpleCounter());

        subject.setRuntimeContext(contextMock);
        subject.open(configMock);

        subject.map("");
        Assertions.assertEquals(1, subject.counter.getCount());
    }
}