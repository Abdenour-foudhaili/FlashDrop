package produitservice.produitservice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidationErrors(MethodArgumentNotValidException exception) {
		Map<String, String> fieldErrors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(Collectors.toMap(
						error -> error.getField(),
						error -> error.getDefaultMessage() == null ? "Invalid value" : error.getDefaultMessage(),
						(first, second) -> first,
						LinkedHashMap::new));

		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Validation failed");
		body.put("fieldErrors", fieldErrors);
		return ResponseEntity.badRequest().body(body);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException exception) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", HttpStatus.BAD_REQUEST.value());
		body.put("error", "Validation failed");
		body.put("message", exception.getMessage());
		return ResponseEntity.badRequest().body(body);
	}
}
