public class Application
{
	private DependencyContainer container;
	private DummyDatabase db;

	public Application() {
		this.container = new DependencyContainer();
		// could be changed to package scan
		this.container.register(IConfig.class, new DummyConfig());
		this.db = container.query(DummyDatabase.class);
	}
	public static void main(String[] args)
	{
		Application app = new Application();
		app.start();
	}

	public void start() {
		this.db.connect();
	}
}
