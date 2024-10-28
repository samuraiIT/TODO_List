package ru.vtb.todo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import ru.vtb.todo.config.DatabaseTestConfiguration

@SpringBootTest
@Import(DatabaseTestConfiguration::class)
internal class TodoListApplicationTest {

    @Test
    fun testApp() {
    }
}