package ru.vtb.todo.model

data class ValidationErrorResponse(
    val message: String,
    val errors: List<ErrorDescription>
)