package core.util.internal.logger;

class LoggerLineObject {

	String logText;
	String status;
	long timeStamp;
	String duration;

	public LoggerLineObject(String logText, boolean status, long timeStamp, String duration) {
		this.logText = logText;

		if (status) {
			this.status = "PASS";
		} else {
			this.status = "FAIL";
		}
		this.timeStamp = timeStamp;
		this.duration = duration;
	}

	public String getLogText() {
		return logText;
	}

	public String getStatus() {
		return status;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public String getDuration() {
		return duration;
	}
}
