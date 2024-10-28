package ru.vtb.todo.repository

import org.springframework.data.jpa.repository.JpaRepository
import ru.vtb.todo.domain.Item
import java.util.*

interface ItemRepository : JpaRepository<Item, UUID>
