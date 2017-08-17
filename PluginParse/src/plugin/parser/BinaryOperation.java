package plugin.parser;

public interface BinaryOperation {
	boolean preExecute(ExecutionContext executionContext);

	boolean execute(ExecutionContext executionContext);

	String postExecute(ExecutionContext executionContext);
}
