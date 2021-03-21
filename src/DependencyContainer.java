import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DependencyContainer
{
	private HashMap<Class<?>, Object> map = new HashMap<>();

	public DependencyContainer() {

	}

	public <T> T query(Class<T> clazz)
	{
		try
		{
			Object o = map.get(clazz);
			if (o == null) {
				o = createInstance(clazz);
			}
			return (T) o;
		}
		catch (Exception e)
		{
			throw new RuntimeException("Could not return instance of class " + clazz.getSimpleName(), e);
		}
	}

	public <T> void register(Class<T> clazz, T object) {
		map.put(clazz, object);
	}

	private <T> T createInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		Constructor<?>[] constructors = clazz.getConstructors();
		Object[] args;
		for (Constructor c : constructors) {
			Class[] argTypes = c.getParameterTypes();
			args = Arrays.stream(argTypes).map(this::query).toArray();
			T newInstance = (T) c.newInstance(args);
			register(clazz, newInstance);
			return newInstance;
		}

		throw new RuntimeException("No constructor could be called");
	}
}
