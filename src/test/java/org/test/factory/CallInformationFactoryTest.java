package org.test.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallDestination;
import org.test.domain.CallInformation;
import org.test.domain.TimeBand;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CallInformationFactoryTest {

    @InjectMocks
    private CallInformationFactory callInformationFactory;

    @Test
    public void testCreate() {

        String inputString = "_02031705557,_01789470476,25/11/2015,12:18:50,00:00:07,UK National,Peak";

        CallInformation callDestination = new CallInformation("_02031705557", "_01789470476",
                LocalDate.parse("25/11/2015",  DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse("12:18:50", DateTimeFormatter.ISO_LOCAL_TIME), 7, CallDestination.UK_National, TimeBand.Peak);

        assertEquals(callDestination, callInformationFactory.create(inputString));
    }

}