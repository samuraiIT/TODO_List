package ru.vtb.todo.model

import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateItemRequest(
    @field:NotNull(message = "{field.is.null}")
    val uid: UUID?,
    @field:NotBlank(message = "{field.is.empty}")
    @field:Size(max = 255, message = "{field.too.large}")
    val text: String?
)
