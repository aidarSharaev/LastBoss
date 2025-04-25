package com.horizonguard.projectflow

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.horizonguard.projectflow.di.koin
import com.horizonguard.projectflow.ui.app_comp.CreateProjectScreen
import com.horizonguard.projectflow.ui.app_comp.KanbanBoard
import com.horizonguard.projectflow.ui.app_comp.Project
import com.horizonguard.projectflow.ui.app_comp.ProjectStatus
import com.horizonguard.projectflow.ui.commom.ErrorScreen
import com.horizonguard.projectflow.ui.login_comp.signin.SignInUiContent
import com.horizonguard.projectflow.ui.login_comp.signup.SignUpUiContent
import com.horizonguard.projectflow.ui.root.RootComponent
import com.horizonguard.projectflow.ui.root.RootUi
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.ProjectFlowTheme
import com.horizonguard.projectflow.utils.runOnUiThread
import org.jetbrains.compose.resources.stringResource
import projectflow.composeapp.generated.resources.Res
import projectflow.composeapp.generated.resources.app_name

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
private fun main() {

    val lifecycle = LifecycleRegistry()

    val rootComponentFactory: RootComponent.KoinFactory by koin.koin.inject()

    val rootComponent = runOnUiThread {
        rootComponentFactory(
            componentContext = DefaultComponentContext(lifecycle),
        )
    }

    application {
        val windowState = rememberWindowState()

        ProjectFlowTheme {

            Window(
                onCloseRequest = ::exitApplication,
                title = stringResource(Res.string.app_name),
            ) {
                LifecycleController(
                    lifecycleRegistry = lifecycle,
                    windowState = windowState,
                    windowInfo = LocalWindowInfo.current,
                )

                CompositionLocalProvider(localWindowSize provides calculateWindowSizeClass()) {
                    RootUi(component = rootComponent)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSignInNotEmpty() {
    currentWindowAdaptiveInfo {
        SignInUiContent(
            "aidaar", true,
            {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewSignInEmpty() {
    currentWindowAdaptiveInfo {
        SignInUiContent(
            "", true,
            {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewSignUpNotEmpty() {
    currentWindowAdaptiveInfo {
        SignUpUiContent(
            "aidaar",
            "aidara",
            true,
            {}, {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewSignUpEmpty() {
    currentWindowAdaptiveInfo {
        SignUpUiContent(
            "", "", true, {}, {}, {}, {}
        )
    }
}

@Preview
@Composable
private fun PreviewErrorScreen() {
    currentWindowAdaptiveInfo {
        ErrorScreen { {} }
    }
}

@Composable
@Preview
private fun AppUiPreview() {

    /*val projects = listOf(
        Project(
            "Sweet Treats Bakery",
            "Веб-сайт электронной коммерции",
            "Разработали интернет-магазин с каталогом продукции, онлайн-заказами и системой доставки, увеличив онлайн-продажи на 30%."
        ),
        Project(
            "FitLife Gym",
            "Мобильное приложение для фитнеса",
            "Создали приложение с функциями записи на тренировки, отслеживания прогресса и общения с тренерами, повысив вовлеченность клиентов на 25%.",
            status = ProjectStatus.CLOSED,
        ),
        Project(
            "Legal Eagles",
            "CRM-система",
            "Внедрили CRM-систему, автоматизировав управление делами, клиентами и документами, что повысило эффективность работы на 15%."
        ),
        Project(
            "City Tours",
            "Приложение для бронирования туров",
            "Разработали мобильное приложение, позволяющее бронировать экскурсии, просматривать отзывы и получать информацию о достопримечательностях, увеличив количество бронирований на 20%."
        ),
        Project(
            "Eco Solutions",
            "Система управления отходами",
            "Создали систему для отслеживания и управления сбором и переработкой отходов, оптимизировав логистику и снизив затраты на 10%."
        ),
        Project(
            "Bookworm Library",
            "Система библиотечного учета",
            "Разработали систему для учета книг, управления читательскими билетами и поиска литературы, упростив работу библиотекарей и улучшив обслуживание читателей."
        ),
        Project(
            "EduTech",
            "Платформа онлайн-обучения",
            "Создали платформу онлайн-обучения с интерактивными курсами, тестами и системой оценки знаний, расширив доступ к образовательным ресурсам.",
            status = ProjectStatus.CLOSED,
        ),
        Project(
            "Secure Solutions",
            "Система видеонаблюдения",
            "Внедрили систему видеонаблюдения, обеспечив безопасность объекта и контроль доступа."
        ),
        Project(
            "Music Stream",
            "Платформа для стриминга музыки",
            "Разработали платформу для стриминга музыки с персонализированными рекомендациями и доступом к обширной музыкальной библиотеке."
        ),
        Project(
            "GameDev Inc.",
            "Разработка мобильной игры",
            "Создали увлекательную мобильную игру с качественной графикой и захватывающим геймплеем, которая быстро набрала популярность.",
            status = ProjectStatus.CLOSED,
        ),
        Project(
            "Medical Center",
            "Система электронной медицинской карты",
            "Внедрили систему электронной медицинской карты, обеспечив быстрый и безопасный доступ к медицинской информации пациентов."
        ),
        Project(
            "Data Analytics Pro",
            "Инструмент для анализа данных",
            "Разработали инструмент для анализа данных, позволяющий визуализировать данные и получать ценные аналитические отчеты."
        ),
        Project(
            "Virtual Assistant Solutions",
            "Разработка чат-бота",
            "Создали чат-бота, автоматизировавшего общение с клиентами и обработку запросов.",
            status = ProjectStatus.CLOSED,
        ),
        Project(
            "AutoTech",
            "Система диагностики автомобилей",
            "Разработали систему диагностики автомобилей, позволяющую быстро выявлять неисправности и оптимизировать процесс ремонта."
        ),
        Project(
            "Language Learning Hub",
            "Приложение для изучения языков",
            "Создали мобильное приложение для изучения языков с интерактивными уроками и упражнениями, сделав процесс обучения более эффективным и увлекательным."
        )
    )
    currentWindowAdaptiveInfo {
        CreateProjectScreen(true)
    }*/

    KanbanBoard()
}

@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun currentWindowAdaptiveInfo(
    content: @Composable () -> Unit,
) {
    val size = DpSize(1000.dp, 1000.dp)
    val adaptive = WindowSizeClass.calculateFromSize(size)
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides adaptive) {
            content()
        }
    }
}