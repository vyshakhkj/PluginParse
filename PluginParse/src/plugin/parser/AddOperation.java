package plugin.parser;

public class AddOperation implements BinaryOperation {

	OperationContainer operation = new OperationContainer();

	@Override
	public boolean preExecute(ExecutionContext e) {
		System.out.println("PreExecute of Add");
		operation = (OperationContainer) e.get("input");
		return true;

	}

	@Override
	public boolean execute(ExecutionContext e) {
		System.out.println("Execute of Add");
		operation.result = operation.lhs + operation.rhs;
		operation.resultStatus = 0;
		return true;

	}

	@Override
	public String postExecute(ExecutionContext e) {
		System.out.println("PostExecute of Add");
		System.out.println("Result : " + operation.result);
		System.out.println("Result Status : " + operation.resultStatus);
		return String.valueOf(operation.result);
	}
}
