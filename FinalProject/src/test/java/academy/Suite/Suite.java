package academy.Suite;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@SuiteDisplayName("SuitTests")
@RunWith(JUnitPlatform.class)
@SelectPackages("academy")
//@SelectClasses({LoginTest.class, GridFilterTest.class, HistogramTest.class, ScatterPlotTest.class})

public class Suite {

}
