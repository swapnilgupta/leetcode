package design;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Result {

	static class Expression {
		String name;
		String expressionType;
		String expression;
		List<String> dependencies;
	}

	static class Group {
		String groupName;
		List<Expression> expressions;
	}

	public static List<String> evaluate(String api) {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(api);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}

			in.close();
			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String jsonData = content.toString();

		Gson gson = new Gson();
		Type listType = new TypeToken<List<Group>>() {}.getType();
		List<Group> groups = gson.fromJson(jsonData, listType);

		List<String> results = new ArrayList<>();

		for (Group group : groups) {
			Map<String, String> evaluatedExpressions = new LinkedHashMap<>();
			List<Expression> sortedExpressions = topologicalSort(group.expressions);
			for (Expression expression : sortedExpressions) {
				evaluateExpression(expression, evaluatedExpressions);
			}
			results.add(formatOutput(group.groupName, evaluatedExpressions));
		}
		return results;
	}

	private static List<Expression> topologicalSort(List<Expression> expressions) {
		Map<String, Expression> expressionMap = new HashMap<>();
		for (Expression expression : expressions) {
			expressionMap.put(expression.name, expression);
		}

		List<Expression> sortedExpressions = new ArrayList<>();
		Set<String> visited = new HashSet<>();
		Set<String> visiting = new HashSet<>();

		for (Expression expression : expressions) {
			if (!visited.contains(expression.name)) {
				topologicalSortUtil(expression, expressionMap, visited, visiting, sortedExpressions);
			}
		}

		return sortedExpressions;
	}

	private static void topologicalSortUtil(Expression expression, Map<String, Expression> expressionMap, Set<String> visited, Set<String> visiting, List<Expression> sortedExpressions) {
		visiting.add(expression.name);
		for (String dependency : expression.dependencies) {
			if (!visited.contains(dependency)) {
				if (visiting.contains(dependency)) {
					throw new IllegalStateException("Circular dependency detected: " + dependency);
				}
				topologicalSortUtil(expressionMap.get(dependency), expressionMap, visited, visiting, sortedExpressions);
			}
		}
		visiting.remove(expression.name);
		visited.add(expression.name);
		sortedExpressions.add(expression);
	}

	private static void evaluateExpression(Expression expression, Map<String, String> evaluatedExpressions) {
		if (evaluatedExpressions.containsKey(expression.name)) {
			return; // Already evaluated
		}

		// Evaluate dependencies first
		for (String dependency : expression.dependencies) {
			if (!evaluatedExpressions.containsKey(dependency)) {
				throw new IllegalStateException("Dependency not found: " + dependency);
			}
		}

		String evaluatedValue = "";
		switch (expression.expressionType) {
			case "DIRECT":
				evaluatedValue = expression.expression;
				break;
			case "DOLLAR_EXPRESSION":
				evaluatedValue = evaluateComplexExpression(expression.expression, evaluatedExpressions, "$");
				break;
			case "RS_EXPRESSION":
				evaluatedValue = evaluateComplexExpression(expression.expression, evaluatedExpressions, "RS");
				break;
			default:
				throw new IllegalStateException("Unknown expression type: " + expression.expressionType);
		}

		evaluatedExpressions.put(expression.name, evaluatedValue);
	}

	private static String evaluateComplexExpression(String expression, Map<String, String> evaluatedExpressions, String suffix) {
		for (Map.Entry<String, String> entry : evaluatedExpressions.entrySet()) {
			expression = expression.replace("${" + entry.getKey() + "}", entry.getValue());
		}
		return "(" + expression + ") " + suffix;
	}

	private static String formatOutput(String groupName, Map<String, String> evaluatedExpressions) {
		StringBuilder sb = new StringBuilder(groupName + ":");
		boolean first = true;
		for (Map.Entry<String, String> entry : evaluatedExpressions.entrySet()) {
			if (!first) {
				sb.append(":");
			}
			sb.append(entry.getKey()).append(":").append(entry.getValue());
			first = false;
		}
		return sb.toString();
	}
}

public class JSONAPIExpressionEvaluator {
	public static void main(String[] args) throws IOException {

		String api = "https://raw.githubusercontent.com/arcjsonapi/expressionDataService/main/test1";

		List<String> result = Result.evaluate(api);
		for (String res : result) {
			System.out.println(res);
		}
	}
}
