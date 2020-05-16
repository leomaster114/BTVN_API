package com.hoanv.SpringBootApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
	/**
	 * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessages handleAllException(Exception ex, WebRequest request) {
		// quá trình kiểm soat lỗi diễn ra ở đây
		return new ErrorMessages(10000, ex.getLocalizedMessage());
	}

	/**
	 * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
	 */
	@ExceptionHandler(IndexOutOfBoundsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessages TodoException(Exception ex, WebRequest request) {
		return new ErrorMessages(10100, "Đối tượng không tồn tại");
	}
//	public String TodoException(Exception ex, WebRequest request) {
////		return new ErrorMessages(10100, "Đối tượng không tồn tại");
//		return "<!DOCTYPE html>\r\n" +
//		"<html>\r\n" + "<head>\r\n" +
//		"<meta charset=\"ISO-8859-1\">\r\n"+
//		"<title>Hello world</title>\r\n" +
//		" </head>\r\n" +
//		"<body>" +
//		"<h1>Có lỗi rồi</h1>\r\n" +
//		"</body>\r\n" +
//		"</html>\r\n";
//	}
}
