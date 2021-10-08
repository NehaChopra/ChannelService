package com.homestars.ChannelService.exception;

import com.homestars.ChannelService.util.ErrorType;
import org.springframework.http.HttpStatus;
/**
 *
 * @author nchopra
 *
 */
public class BadRequestError extends APIException {

	public BadRequestError(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	public ErrorType getErrorType() {
		return ErrorType.VALIDATION_ERROR;
	}

}
