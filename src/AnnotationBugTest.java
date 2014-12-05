import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class AnnotationBugTest {

    static class DerivedClass extends ParentClass {
        @SomeAnnotation
        @Override
        public int getFoo() { return 5; }
    }

    public static void main(String[] args) throws IntrospectionException {
        PropertyDescriptor[] descs = Introspector.getBeanInfo(DerivedClass.class).getPropertyDescriptors();
        for (PropertyDescriptor desc : descs) {
            if (!desc.getName().equals("foo")) {
                continue;
            }

            Method readMethod = desc.getReadMethod();
            SomeAnnotation ann = readMethod.getAnnotation(SomeAnnotation.class);
            if (ann == null) {
                throw new AssertionError("Failed");
            } else {
                System.out.println("OK");
            }
        }

    }
}
