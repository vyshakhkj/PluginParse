package plugin.parser;
import java.util.HashMap;
import java.util.Map;


public class PluginManager {

	Map<String, String> plugins = new HashMap<String, String>();

	Map<String, Object> cached = new HashMap<String, Object>();

	public PluginManager(String fileName) {
		loadPlugins(fileName);
	}

	private void loadPlugins(String fileName) {
		plugins = ConfigurationParser.collectPlugins(fileName);
	}

	public String execute(ExecutionContext executionContext) {
		OperationContainer computation = (OperationContainer) executionContext.get("input");
		if (computation == null) {
			System.out.println("Cannot find input Object");
			return "Cannot find input Object";
		}
		String handler = getHandler(computation.operator);
		if (handler == null) {
			System.out.println("Cannot find the handler");
			return "Cannot find the handler";
		}
		return executeViaHandler(handler, executionContext);
	}

	private String executeViaHandler(String handler, ExecutionContext executionContext) {
		BinaryOperation binaryOperation = getInterface(handler);
		if (binaryOperation == null) {
			return "Error";
		}
		if (binaryOperation.preExecute(executionContext)) {
			binaryOperation.execute(executionContext);
			String result = binaryOperation.postExecute(executionContext);
			return result;
		}
		return "Error";
	}

	private BinaryOperation getInterface(String handler) {
		BinaryOperation cachedBinaryOperation = (BinaryOperation) cached.get(handler);
		if (cachedBinaryOperation != null) {
			return cachedBinaryOperation;
		}
		Object b = null;
		try {
			Class operationClass = Class.forName("plugin.parser." + handler);
			b = operationClass.newInstance();
		} catch (Exception e) {
			System.out.println("Error occurred on getInterface " + e);
			return null;
		}
		BinaryOperation binaryOperation = (BinaryOperation) b;
		cached.put(handler, binaryOperation);
		return binaryOperation;
	}

	private String getHandler(String operator) {
		String handler = plugins.get(operator);
		if (handler != null) {
			System.out.println("Handler is : " + handler);
		}
		return handler;
	}
}
