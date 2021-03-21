public class DummyConfig implements IConfig
{
	@Override
	public String getValue(String key)
	{
		return "dummy";
	}
}
