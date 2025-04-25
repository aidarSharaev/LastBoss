package com.horizonguard.projectflow.ui.app_comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowOverflow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
internal fun AppUi(
    component: AppComponent,
    modifier: Modifier = Modifier,
) {

}

enum class ProjectStatus {
    ACTIVE,
    CLOSED,
    ON_PAUSE,
}

enum class Role {
    ADMIN,
    WRITER,
    READER,
}

data class Project(
    val client: String,
    val name: String,
    val description: String,
    val status: ProjectStatus = ProjectStatus.ACTIVE,
)

data class Employer(
    val address: String,
    val role: Role = Role.READER,
)

/*@Composable
fun AppUiContent(projects: List<Project>) {
    val activeProjects = remember(projects) {
        projects.filter { it.status != ProjectStatus.CLOSED }
    }
    val closedProjects = remember(projects) {
        projects.filter { it.status == ProjectStatus.CLOSED }
    }

    Column(
        modifier = Modifier.fillMaxSize().scrollable(
            rememberScrollState(), Orientation.Vertical
        ).background(MaterialTheme.colorScheme.background).padding(12.dp)
    ) {
        Text(
            modifier = Modifier.padding(start = 24.dp).padding(vertical = 8.dp),
            text = "Открытые проекты",
            style = projectFlowTypography.titleLarge
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 316.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(activeProjects) { project ->
                Card(
                    colors = CardDefaults.cardColors().copy(containerColor = Color.White),
                ) {
                    Column(modifier = Modifier.height(168.dp).padding(16.dp)) {
                        Text(
                            project.name,
                            style = projectFlowTypography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = project.description,
                            style = projectFlowTypography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Text(
                                "Статус: ${project.status.name}",
                                style = projectFlowTypography.bodySmall
                            )
                            Icon(
                                imageVector = Icons.Default.Star,
                                tint = Color.Yellow,
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
        }

        Text(
            modifier = Modifier.padding(start = 24.dp).padding(vertical = 8.dp),
            text = "Закрытые проекты",
            style = projectFlowTypography.titleLarge
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 316.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(closedProjects) { project ->
                Card(
                    colors = CardDefaults.cardColors().copy(containerColor = Color.LightGray),
                ) {
                    Column(
                        modifier = Modifier.height(168.dp).padding(16.dp),
                    ) {
                        Text(
                            project.name,
                            style = projectFlowTypography.titleMedium,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 2,
                            color = Color.DarkGray,
                        )
                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = project.description,
                            style = projectFlowTypography.bodySmall,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.DarkGray,
                        )

                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().weight(1f),
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            Text(
                                "Статус: ${project.status.name}",
                                style = projectFlowTypography.bodySmall
                            )
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    tint = Color.Gray,
                                    contentDescription = "",
                                )
                                Icon(
                                    modifier = Modifier.padding(start = 8.dp),
                                    imageVector = Icons.Default.Settings,
                                    tint = Color.Gray,
                                    contentDescription = "",
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}*/

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CreateProjectScreen(showDialog: Boolean) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ProjectFlow",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = topAppBarColors().copy(containerColor = Color(0xfff5f6f7))
            )
        },
    ) {

        var projectName by remember { mutableStateOf("Интеграция 1C на прозводство") }
        var projectDescription by remember { mutableStateOf("Необходимо внедрить на завод \"Камаал\" 1С, для отслеживания внутренних перемещений") }
        var boardName by remember { mutableStateOf("Команда ИИ") }
        var companyName by remember { mutableStateOf("РосАлмазСтрой") }
        var selectedEmployees by remember { mutableStateOf(setOf<String>()) }

        val employees = listOf(
            Employer("а.бабков", Role.WRITER),
            Employer("р.романов", Role.ADMIN),
            Employer("г.евсеев", Role.WRITER),
            Employer("е.селехов", Role.ADMIN),
            Employer("c.бобров", Role.WRITER),
            Employer("а.александров", Role.WRITER),
        )

        val boards = listOf(
            "Команда DevOps",
            "Команда Аналитика",
            "Команда Front",
            "Команда BackEnd",
        )

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Создание проекта",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 6.dp)
            )

            OutlinedTextField(
                value = projectName,
                onValueChange = { projectName = it },
                label = { Text("Название проекта") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = projectDescription,
                onValueChange = { projectDescription = it },
                label = { Text("Описание") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                boards.forEach {
                    AssistChip(
                        onClick = { },
                        label = { Text(it) },
                        leadingIcon = {
                            Icon(
                                tint = Color(0xff8b8f94),
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(AssistChipDefaults.IconSize),
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors()
                            .copy(containerColor = Color(0xfff4f6f8))
                    )
                }
            }

            OutlinedTextField(
                value = boardName,
                onValueChange = { boardName = it },
                label = { Text("Доски") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 5,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = Color.LightGray,
                        contentDescription = "",
                    )
                }
            )

            OutlinedTextField(
                value = companyName,
                onValueChange = { companyName = it },
                label = { Text("Компания") },
                modifier = Modifier.fillMaxWidth()
            )

            HorizontalDivider(modifier = Modifier.padding(top = 5.dp))

            Text(text = "Выбор сотрудников", style = MaterialTheme.typography.titleLarge)

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                employees.forEach {
                    AssistChip(
                        onClick = { },
                        label = {
                            Text(
                                "${it.address} (${it.role.name.first()})",
                            )
                        },
                        leadingIcon = {
                            Icon(
                                tint = Color(0xff8b8f94),
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Localized description",
                                modifier = Modifier.size(AssistChipDefaults.IconSize),
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors()
                            .copy(containerColor = Color(0xfff4f6f8))
                    )
                }
            }

            ExposedDropdownMenuBox(
                expanded = true,
                onExpandedChange = { },
            ) {
                OutlinedTextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    label = { Text("Сотрудник") },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )
                ExposedDropdownMenu(
                    true,
                    onDismissRequest = {},
                ) {
                    DropdownMenuItem(
                        text = { Text("Option 1") },
                        onClick = { /* Do something... */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Option 2") },
                        onClick = { /* Do something... */ }
                    )
                }
            }

            Button(
                onClick = { /* Handle project creation logic */ },
                modifier = Modifier.align(Alignment.End),
                colors = buttonColors().copy(containerColor = Color(0xFF027ef4))
            ) {
                Text("Создать проект")
            }
        }

        if (showDialog) {
            CheckboxDialog(employees.first(), true, {}, {})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxDialog(
    employer: Employer,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (List<String>) -> Unit
) {
    var option1 by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Роль: ${employer.address}") },
            text = {
                Column {
                    Role.entries.forEachIndexed { index, it ->
                        CheckboxWithLabel(
                            checked = index == 1,
                            onCheckedChange = { option1 = it },
                            label = it.name
                        )
                    }
                }
            },
            confirmButton = {
                Button(onClick = {}) {
                    Text("Назначить")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss) {
                    Text("Отмена")
                }
            }
        )
    }
}

@Composable
fun CheckboxWithLabel(checked: Boolean, onCheckedChange: (Boolean) -> Unit, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        Spacer(modifier = Modifier.width(8.dp))
        Text(label)
    }
}


data class Card(
    val title: String,
    val label: String,
    val current: Float = 0.1F,
    val estimation: Int = 1,
    val name: String,
)

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun KanbanBoard() {
    val list = listOf(
        "Разработка",
        "Дизайн",
        "Завершены",
        "Текущий спринт",
        "Разработка",
        "В работе"
    )
    val names = remember {
        listOf("В запасе", "В работе", "Проверка", "Закрыто")
    }
    val columns = remember {
        listOf(
            mutableStateListOf(
                Card("Баг с темной темой", "UI", 0.4F, 1, "р.романов"),
                Card("Интеграция backend и frontend", "Совместимость", 12F, 80,"р.романов"),
            ),
            mutableStateListOf(
                Card("Разработка API", "backend", 12f, 15, "а.бабков"),
                Card("Разработка frontend", "frontend", 16f, 16, "р.романов"),
                Card("Разворачивание серверов", "backend", name = "а.бабков"),
            ),
            mutableStateListOf(
                Card("Настройка серверов", "backend", 0.4F, 1, "а.бабков"),
                Card("Закупка оборудования", "Склад", name = "ф.макаров"),
                Card("Анализ данных", "ML", 0.4F, 1,"а.александров"),
            ),
            mutableStateListOf(
                Card("Разработка дизайна интерфейса (UI/UX)", "Дизайн", 12f, 15, "м.киселева"),
                Card("Определение требований к проекту", "Анализ", name = "г.евсеев"),
                Card("Выбор технологий и инструментов", "Анализ", name = "г.евсеев"),
                Card("Анализ рынка и конкурентов", "Анализ", 12f, 15, name = "г.евсеев"),
                Card("Первый commit", "frontend", name = "р.романов"),
            ),
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "EduTech",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = topAppBarColors().copy(containerColor = Color(0xfff5f6f7))
            )
        },
    ) {
        Column(modifier = Modifier.padding(it).fillMaxSize().background(Color.White).padding(16.dp)) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxLines = 1,
                overflow = FlowRowOverflow.Clip,
            ) {
                list.forEachIndexed { index, it ->
                    FilterChip(selected = index == 0, label = { Text(it) }, onClick = {})
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(columns.size) {
                    Column(modifier = Modifier.weight(1f).clip(RoundedCornerShape(12.dp)).background(Color(0xfff9fbfc)).padding(8.dp)) {
                        Row {
                            Text("${names[it]} ", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text(
                                text = columns[it].size.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.drawBehind {
                                    drawCircle(Color(0xFFBBAAEE))
                                }.padding(4.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        TaskColumn(cards = columns[it])
                    }
                }
            }
        }
    }
}

@Composable
fun TaskColumn(cards: List<Card>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        items(cards) { card ->
            Card(
                modifier = Modifier
                    .shadow(4.dp, CardDefaults.shape)
                    .fillMaxWidth()
                    .height(128.dp)
                    .clickable { },
                border = BorderStroke(1.dp, Color.Gray),
                colors = cardColors().copy(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                ) {
                    Text(
                        card.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(card.label, modifier = Modifier
                        .padding(top = 12.dp, start = 8.dp)
                        .drawBehind {
                            drawRoundRect(
                                Color(0xFFBBAAEE),
                                cornerRadius = CornerRadius(10.dp.toPx())
                            )
                        }
                        .padding(3.dp))

                    LinearProgressIndicator(
                        progress = { card.current/card.estimation },
                        modifier = Modifier.padding(top = 14.dp).fillMaxWidth(),
                    )

                    Text(
                        text = card.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}