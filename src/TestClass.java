public class TestClass {

    //@AfterSuite()
    @BeforeSuite()
    public static void beforeSuite() {
        System.out.println("@BeforeSuite()");
    }

    @Test(prior = 1)
    public static void a() {
        System.out.println("@Test(prior = 1)");
    }

    @Test(prior = 3)
    public static void b() {
        System.out.println("@Test(prior = 3)");
    }

    @Test(prior = 0)
    public static void c() {
        System.out.println("@Test(prior = 0)");
    }

    @Test(prior = 2)
    public static void d() {
        System.out.println("@Test(prior = 2)");
    }

    //@BeforeSuite()
    @AfterSuite()
    public static void afterSuite() {
        System.out.println("@AfterSuite()");
    }

}
