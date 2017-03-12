import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        PostRequestTest.class, GetRequestTest.class
})
public class MainTestSuite {
}
