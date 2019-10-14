//package br.com.api.handler;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Locale;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.validation.FieldError;
//import org.springframework.validation.ObjectError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import br.com.api.exception.ApiException;
//import br.com.api.rest.response.ErrorResponse;
//
//@RestControllerAdvice
//public class RestExceptionHandler extends ResponseEntityExceptionHandler implements AccessDeniedHandler {
//
//	@Autowired
//	private MessageSource message;
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	private List<String> errors;
//
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		logError(ex);
//
//		errors = new ArrayList<>();
//		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
//			errors.add(error.getDefaultMessage());
//		}
//		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
//			errors.add(error.getDefaultMessage());
//		}
//		ErrorResponse<Object> response = new ErrorResponse<Object>(String.valueOf(HttpStatus.BAD_REQUEST.value()),
//				HttpStatus.BAD_REQUEST.value(), errors);
//		errors = null;
//		return handleExceptionInternal(ex, response, headers, HttpStatus.BAD_REQUEST, request);
//	}
//
//	@ExceptionHandler({ ApiException.class })
//	public ResponseEntity<Object> handleApiException(ApiException ex) {
//		logError(ex);
//
//		ErrorResponse<Object> response = new ErrorResponse<Object>(String.valueOf(HttpStatus.BAD_REQUEST.value()),
//				HttpStatus.BAD_REQUEST.value(), Arrays.asList(ex.getMessage()));
//		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler({ Exception.class })
//	public ResponseEntity<Object> handleGenericException(Exception ex) {
//		logError(ex);
//
//		ErrorResponse<Object> response = new ErrorResponse<Object>(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
//				HttpStatus.INTERNAL_SERVER_ERROR.value(),
//				Arrays.asList(message.getMessage("exception.general", null, Locale.getDefault())));
//		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	private void logError(Exception ex) {
//		logger.error(ex.getClass().getName(), ex);
//	}
//
//	@Override
//	public void handle(HttpServletRequest request, HttpServletResponse response,
//			AccessDeniedException ex) throws IOException, ServletException {
//		logError(ex);
//
//		response.setStatus(HttpStatus.FORBIDDEN.value());
//		response.setContentType("application/json;charset=UTF-8");
//
//		ErrorResponse<Object> customResponse = new ErrorResponse<Object>(String.valueOf(HttpStatus.FORBIDDEN.getReasonPhrase()),
//				HttpStatus.FORBIDDEN.value(),
//				Arrays.asList("Acesso negado. Você não possui autorização para acessar esse recurso."));
//
//		OutputStream out = response.getOutputStream();
//		ObjectMapper mapper = new ObjectMapper();
//		mapper.writeValue(out, customResponse);
//		out.flush();
//
//	}
//
//}
