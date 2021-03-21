public class DummyDatabase
{
	private IConfig config;

	public DummyDatabase(IConfig config) {
		this.config = config;
	}
	public void connect() {
		System.out.println("Connecting to database: " + this.config.getValue("dummy"));
	}
}
