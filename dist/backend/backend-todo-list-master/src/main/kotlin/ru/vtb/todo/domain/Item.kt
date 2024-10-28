package ru.vtb.todo.domain

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "items")
data class Item(

    @Id
    @Column(name = "id", nullable = false, unique = true)
    val id: UUID? = null,

    @Column(name = "text", length = 255)
    val text: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Item

        if (id != other.id) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Item(id=$id, data=$text)"
    }
}