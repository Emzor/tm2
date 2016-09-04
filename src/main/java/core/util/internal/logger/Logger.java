package core.util.internal.logger;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Logger {
	private long startTime;
	private String testName;
	StringBuilder sb;
	private static Logger instance = null;
	private StringJoiner logHeader = new StringJoiner("");
	private ArrayList<LoggerLineObject> logRow = new ArrayList<LoggerLineObject>();

	private Logger() {
	}

	public static Logger getInstance() {
		if (instance == null) {
			instance = new Logger();
			return instance;
		} else
			return instance;
	}

	public void newLog(String name) {
		this.testName = name;
		int nameSize = name.length();
		logHeader.add("----------------------------------------- ").add(name).add(" ");
		while (nameSize < 75) {
			logHeader.add("-");
			nameSize++;
		}
		logHeader.add("\n");
		startTime = System.currentTimeMillis();
	}

	public void log(String stringLog, boolean status) {
		long lastLogOccurence;
		try {
			LoggerLineObject lastLine = logRow.get(logRow.size() - 1);
			lastLogOccurence = lastLine.timeStamp;
		} catch (ArrayIndexOutOfBoundsException e) {
			lastLogOccurence = startTime;
		}

		long t2 = System.currentTimeMillis();
		float duration = (float) (t2 - lastLogOccurence) / 1000;

		logRow.add(new LoggerLineObject(stringLog, status, System.currentTimeMillis(),
				String.format("%.1f", duration) + " s"));
	}

	private void prepareLog() {
		sb = new StringBuilder();
		sb.append(logHeader);

		for (LoggerLineObject loggerLine : logRow) {
			int lineSize = loggerLine.logText.length();
			sb.append(loggerLine.logText);

			while (lineSize < 100) {
				sb.append(" ");
				lineSize++;
			}

			sb.append("| ").append(loggerLine.status).append(" |").append(" ");
			
			if (loggerLine.duration.length() == 5) {
				sb.append("| ").append(loggerLine.duration).append("  |").append("\n");
			} else {
				sb.append("| ").append(loggerLine.duration).append(" |").append("\n");
			}
		}
		
		logFooter();
	}

	public void logFooter() {
		sb.append("\n");
		boolean status = true;

		for (LoggerLineObject loggerLine : logRow) {
			String stat = loggerLine.status;
			if (stat.equals("FAIL")) {
				status = false;
			}
		}

		String testStatus;
		if (status) {
			testStatus = "Passed";
		} else {
			testStatus = "Failed";
		}

		sb.append("Test ").append(testName).append(" ").append(testStatus).append(" ").append(" with duration ")
				.append(getTestDuration());
	}

	public void printLog() {
		prepareLog();
		System.out.println(sb);
	}

	public String getTestDuration() {
		long t2 = logRow.get(logRow.size() - 1).timeStamp;
		float duration = (float) (t2 - startTime) / 1000;

		return String.format("%.1f", duration) + " s"+"\n\n\n";
	}
}
