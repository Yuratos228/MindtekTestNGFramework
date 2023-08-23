package dataProviders;

import org.testng.annotations.DataProvider;

public class BooksApiDataProvider {
    @DataProvider(name = "query-params")
    public Object[][] queryParamsData(){
        return new Object[][]{
                {"non-fiction", 2},
                {"fiction", 1},
                {"fiction", 0}
        };
    }
}
