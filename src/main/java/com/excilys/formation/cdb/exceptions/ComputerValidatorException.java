package com.excilys.formation.cdb.exceptions;

public class ComputerValidatorException extends RuntimeException {

	public ComputerValidatorException() {
		super();
	}

	public ComputerValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComputerValidatorException(String message) {
		super(message);
	}

	public ComputerValidatorException(Throwable cause) {
		super(cause);
	}

	protected ComputerValidatorException(String message, Throwable cause,
			boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
	public static class NameValidator extends ComputerValidatorException{

		public NameValidator() {
			super();
		}

		public NameValidator(String message, Throwable cause) {
			super(message, cause);
		}

		public NameValidator(String message) {
			super(message);
		}

		public NameValidator(Throwable cause) {
			super(cause);
		}

		protected NameValidator(String message, Throwable cause,
				boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}
	}

	public static class DateValidator extends ComputerValidatorException{

		public DateValidator() {
			super();
		}

		public DateValidator(String message, Throwable cause) {
			super(message, cause);
		}

		public DateValidator(String message) {
			super(message);
		}

		public DateValidator(Throwable cause) {
			super(cause);
		}
		
		protected DateValidator(String message, Throwable cause,
				boolean enableSuppression,
				boolean writableStackTrace) {
			super(message, cause, enableSuppression, writableStackTrace);
		}

	}
}