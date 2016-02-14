package org.test.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CallDestination;
import org.test.domain.CallInformation;
import org.test.domain.TimeBand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class CallInformationFactoryTest {

    @InjectMocks
    private CallInformationFactory callInformationFactory;

    @Test
    public void testCreate() {

        String inputString = "_02031705557,_01789470476,25/11/2015,12:18:50,00:00:07,UK National,Peak";

        CallInformation callDestination = new CallInformation("_02031705557", "_01789470476",
                "25/11/2015","12:18:50", "00:00:07", CallDestination.UK_National, TimeBand.Peak);

        assertEquals(callDestination, callInformationFactory.create(inputString).get());
    }

    @Test
    public void testCreateInvalidCallDestination() {

        String inputString = "_02031705557,_01789470476,25/11/2015,12:18:50,00:00:07,Invalid,Peak";

        assertTrue(!callInformationFactory.create(inputString).isPresent());
    }

    @Test
    public void testCreateInvalidTimeBand() {

        String inputString = "_02031705557,_01789470476,25/11/2015,12:18:50,00:00:07,UK National,Invalid";

        assertTrue(!callInformationFactory.create(inputString).isPresent());
    }

    @Test
    public void testCreateMissingParam() {

        String inputString = "_02031705557,_01789470476,25/11/2015,12:18:50,00:00:07,UK National";

        assertTrue(!callInformationFactory.create(inputString).isPresent());
    }

}