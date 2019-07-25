import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InterfaceAddress;
import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {
        start(TestClass.class);
    }

    private static void start(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        try {
            testSuite(methods, BeforeSuite.class); //Проверяем @BeforeSuite
            testClassMethods(methods); //Проверяем @Test
            testSuite(methods, AfterSuite.class); //Проверяем @AfterSuite
        }
        catch (RuntimeException e) {
            System.err.println("RuntimeException: " + e);
        }
        catch (Exception e) {
            System.err.println("Exception: " + e);
        }
    }

    //Данный метод подходит для тестирования @BeforeSuite и @AfterSuite.
    private static void testSuite(Method[] methods, Class testSuite) throws Exception {
        if(!testSuite.equals(BeforeSuite.class) && !testSuite.equals(AfterSuite.class))
            throw new RuntimeException("Неподходящий класс");
        Method methodSuite = null;
        for (Method method: methods) {
            if (method.getAnnotation(testSuite) != null) {
                if(methodSuite != null){
                    throw new RuntimeException();
                }
                methodSuite = method;
            }
        }
        if (methodSuite == null) {
            throw new RuntimeException();
        }
        else {
            methodSuite.invoke(null);
        }
    }

    //Данный метод подходит для тестирования @Test
    private static void testClassMethods(Method[] methods) throws Exception {
        ArrayList<Method> listOfMethods = new ArrayList<>();
        for (Method method : methods) {
            if(method.getAnnotation(Test.class) != null) {
                listOfMethods.add(method);
            }
        }
        bubbleSort(listOfMethods);
        for (Method method : listOfMethods) {
            method.invoke(null);
        }
    }

    private static void bubbleSort(ArrayList<Method> arr){
        for(int i = arr.size()-1 ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
                if(arr.get(j).getAnnotation(Test.class).prior() > arr.get(j+1).getAnnotation(Test.class).prior()) {
                    Method tmp = arr.get(j);
                    arr.set(j,arr.get(j+1));
                    arr.set(j+1,tmp);
                }
            }
        }
    }
}




