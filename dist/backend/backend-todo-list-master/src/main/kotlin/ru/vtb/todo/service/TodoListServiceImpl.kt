package ru.vtb.todo.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.vtb.todo.domain.Item
import ru.vtb.todo.exceptions.ItemAlreadyExistsException
import ru.vtb.todo.model.CreateItemRequest
import ru.vtb.todo.model.ListItem
import ru.vtb.todo.repository.ItemRepository
import java.util.*

@Service
class TodoListServiceImpl(
    private val itemRepository: ItemRepository
) : TodoListService {

    @Transactional(readOnly = true)
    override fun findAll() = itemRepository
        .findAll()
        .map { ListItem(uid = it.id!!, text = it.text!!) }

    @Transactional
    override fun create(request: CreateItemRequest) {
        val id = request.uid
        if (itemRepository.findById(id!!).isPresent) {
            throw ItemAlreadyExistsException("Item $id already exists")
        }
        val item = Item(id = id, text = request.text)
        itemRepository.save(item)
    }

    @Transactional
    override fun delete(uid: UUID) {
        itemRepository.deleteById(uid)
    }
}