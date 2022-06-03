package photo.model.exception;

public class PhotoException extends RuntimeException {

	public PhotoException() {
		super();
	}

	public PhotoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhotoException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhotoException(String message) {
		super(message);
	}

	public PhotoException(Throwable cause) {
		super(cause);
	}
}