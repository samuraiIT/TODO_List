package ru.vtb.todo.web

import io.swagger.v3.oas.annotations.Hidden
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import ru.vtb.todo.exceptions.ItemAlreadyExistsException
import ru.vtb.todo.model.ErrorDescription
import ru.vtb.todo.model.ErrorResponse
import ru.vtb.todo.model.ValidationErrorResponse
import java.lang.RuntimeException
import java.util.stream.Collectors

@Hidden
@RestControllerAdvice
class ExceptionController {
    private val logger = LoggerFactory.getLogger(ExceptionController::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun badRequest(exception: MethodArgumentNotValidException): ValidationErrorResponse {
        val bindingResult = exception.bindingResult
        return ValidationErrorResponse(buildMessage(bindingResult), buildErrors(bindingResult))
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ItemAlreadyExistsException::class)
    fun handleException(exception: ItemAlreadyExistsException): ErrorResponse {
        return ErrorResponse(exception.message!!)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException::class)
    fun handleException(exception: RuntimeException): ErrorResponse {
        logger.error("", exception)
        return ErrorResponse(exception.message!!)
    }

    private fun buildMessage(bindingResult: BindingResult): String {
        return String.format("Error on %s, rejected errors [%s]",
            bindingResult.target,
            bindingResult.allErrors
                .stream()
                .map { it.defaultMessage }
                .collect(Collectors.joining(",")))
    }

    private fun buildErrors(bindingResult: BindingResult): List<ErrorDescription> {
        return bindingResult.fieldErrors
            .stream()
            .map { ErrorDescription(it.field, it.defaultMessage!!) }
            .collect(Collectors.toList())
    }
}