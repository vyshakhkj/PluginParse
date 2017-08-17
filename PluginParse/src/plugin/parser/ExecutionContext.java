package plugin.parser;
import java.util.HashMap;
import java.util.Map;


public class ExecutionContext {
	Map<String, Object> executionContext = new HashMap<String, Object>();

	public ExecutionContext() {
	}

	public Object get(String str) {
		return executionContext.get(str);
	}

	public void put(String str, Object obj) {
		executionContext.put(str, obj);
	}

}
