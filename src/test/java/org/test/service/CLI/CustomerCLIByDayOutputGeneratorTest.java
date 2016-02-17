package org.test.service.CLI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.test.domain.CustomerCLIByDay;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomerCLIByDayOutputGeneratorTest {

    @InjectMocks
    CustomerCLIByDayOutputGenerator customerCLIByDayOutputGenerator;

    @Test
    public void testGetResultsJustHeader() {
        List<String> results = customerCLIByDayOutputGenerator.getResults(new HashMap<>());

        assertEquals(results.get(0), "Date,CLI,TotalCost");
    }

    @Test
    public void testGetRenderedResults() {
        Map<String, Map<String, CustomerCLIByDay>> cliMap = new HashMap<>();
        Map<String, CustomerCLIByDay> dateMap = new HashMap<>();
        CustomerCLIByDay customerCLIByDay = mock(CustomerCLIByDay.class);
        when(customerCLIByDay.getTotalCost()).thenReturn(BigDecimal.ONE);
        dateMap.put("01-01-01", customerCLIByDay);
        cliMap.put("1", dateMap);

        List<String> results = customerCLIByDayOutputGenerator.getResults(cliMap);

        assertEquals(results.get(0), "Date,CLI,TotalCost");
        assertEquals(results.get(1), "1,01-01-01,1.00");
    }

}