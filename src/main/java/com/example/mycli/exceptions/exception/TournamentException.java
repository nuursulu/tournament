package com.example.mycli.exceptions.exception;

public class TournamentException extends RuntimeException {
	public TournamentException() {
		super();
	}

	public TournamentException(String message) {
		super(message);
	}

	public TournamentException(String message, Throwable cause) {
		super(message, cause);
	}
}
