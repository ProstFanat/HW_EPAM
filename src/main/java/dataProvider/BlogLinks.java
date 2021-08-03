package dataProvider;

import org.testng.annotations.DataProvider;

public class BlogLinks {

    @DataProvider(name="blog-links")
    public static Object[] blogLinks() throws Exception{
        return new String[]{
                "news",
                "real-stories",
                "materials",
                "hard-skills",
                "soft-skills",
                "events"
        };
    }

}
