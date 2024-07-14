package github.com.miralhas.ecommerce_uol.api.exception_handler;

import github.com.miralhas.ecommerce_uol.domain.exception.BusinessException;
import github.com.miralhas.ecommerce_uol.domain.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.HashMap;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    // Padrão de Resposta RFC 9457
    // https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-ann-rest-exceptions.html
    // https://datatracker.ietf.org/doc/html/rfc9457
    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleBusinessException(BusinessException ex, WebRequest webRequest) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Requisição Inválida");
        problemDetail.setType(URI.create("http://localhost:8080/error/requisicao-invalida"));
        return problemDetail;
    }


    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex, WebRequest webRequest) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("Autenticação Inválida");
        problemDetail.setType(URI.create("http://localhost:8080/error/authentication"));
        return problemDetail;
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Recurso não encontrado");
        problemDetail.setType(URI.create("http://localhost:8080/error/recurso-nao-encontrado"));
        return problemDetail;
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleUncaughtException(Exception ex, WebRequest webRequest) {
        if (ex instanceof AccessDeniedException authError) throw authError;
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var detail = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
                + "o problema persistir, entre em contato com o administrador do sistema.";
        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle("Erro de Sistema");
        problemDetail.setType(URI.create("https://localhost:8080/errors/erro-de-sistema"));
        return problemDetail;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        var errorsMap = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            errorsMap.put(error.getField(), message);
        });

        var detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
        problemDetail.setTitle("Campos Inválidos");
        problemDetail.setType(URI.create("http://localhost:8080/error/campos-invalidos"));
        problemDetail.setProperty("errors", errorsMap);

        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var detail = "O corpo da requisição está inválido. Verifique erros de sintaxe";
        var problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
        problemDetail.setTitle("Mensagem incompreensivel");
        problemDetail.setType(URI.create("https://localhost:8080/errors/mensagem-incompreensivel"));
        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }
}
