package ru.vtb.todo.service

import ru.vtb.todo.model.CreateItemRequest
import ru.vtb.todo.model.ListItem
import java.util.*

interface TodoListService {
    fun findAll(): List<ListItem>
    fun create(request: CreateItemRequest)
    fun delete(uid: UUID)
}
