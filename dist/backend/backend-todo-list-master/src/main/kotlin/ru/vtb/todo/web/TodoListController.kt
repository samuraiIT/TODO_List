package ru.vtb.todo.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.headers.Header
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.created
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest
import ru.vtb.todo.model.CreateItemRequest
import ru.vtb.todo.model.ErrorResponse
import ru.vtb.todo.model.ListItem
import ru.vtb.todo.model.ValidationErrorResponse
import ru.vtb.todo.service.TodoListService
import java.util.*
import javax.validation.Valid

@Tag(
    name = "TODO list Controller",
    description = "Операции создания, получения списка и удаления над записями TODO-листа"
)
@RestController
@RequestMapping("/api/v1/public/items")
class TodoListController(
    private val todoListService: TodoListService
) {
    @Operation(
        summary = "Получение списка записей",
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Список всех записей TODO-листа",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = ArraySchema(schema = Schema(implementation = ListItem::class))
                )]
            )
        ]
    )
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun items(): List<ListItem> = todoListService.findAll()

    @Operation(
        summary = "Создание новой запсии",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "Новая запись успешно добавлена",
                headers = [Header(name = "Location", description = "Ссылка на список всех записей")]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Ошибка валидации",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ValidationErrorResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "409",
                description = "Запись с таким uid уже существует",
                content = [Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = Schema(implementation = ErrorResponse::class)
                )]
            )
        ]
    )
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@Valid @RequestBody request: CreateItemRequest): ResponseEntity<Void> {
        todoListService.create(request)
        return created(fromCurrentRequest().build().toUri()).build()
    }

    @Operation(
        summary = "Удаление записи",
        responses = [ApiResponse(responseCode = "204", description = "Запись успешно удалена")]
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uid}")
    fun delete(@PathVariable uid: UUID) {
        todoListService.delete(uid)
    }
}