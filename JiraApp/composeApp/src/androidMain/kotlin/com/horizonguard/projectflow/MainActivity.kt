package com.horizonguard.projectflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.defaultComponentContext
import com.horizonguard.projectflow.ui.app_comp.CreateProjectScreen
import com.horizonguard.projectflow.ui.app_comp.KanbanBoard
import com.horizonguard.projectflow.ui.root.RootComponent
import com.horizonguard.projectflow.ui.root.RootUi
import com.horizonguard.projectflow.ui.root.localWindowSize
import com.horizonguard.projectflow.utils.ProjectFlowTheme
import org.koin.android.ext.android.inject

internal class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootComponentFactory: RootComponent.KoinFactory by inject()
        val rootComponent = rootComponentFactory(defaultComponentContext())

        setContent {
            ProjectFlowTheme {
                CompositionLocalProvider(localWindowSize provides calculateWindowSizeClass(this)) {
                    RootUi(component = rootComponent)
                }
            }
        }
    }
}

/*
@Preview
@Composable
private fun PreviewNotEmpty() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignInUiContent(
                "aidaar",
                {}, {}, {}
            )
        }
    }
}

@Preview
@Composable
private fun PreviewEmpty() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            SignInUiContent(
                "",
                {}, {}, {}
            )
        }
    }
}
*/

/*@Preview
@Composable
private fun PreviewErrorScreen() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            ErrorScreen({})
        }
    }

}*/

/*@Preview
@Composable
private fun PreviewOtp() {
    ProjectFlowTheme {
        CompositionLocalProvider(localWindowSize provides currentWindowAdaptiveInfo()) {
            OtpUiContent(
                "aidaar",
                true,
                {},
                {},
            )
        }
    }
}*/

/*@Preview
@Composable
private fun PrviewOtpe() {
    ProjectFlowTheme {
        *//*val projects = listOf(
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
        AppUiContent(projects)*//*
        CreateProjectScreen(true)
    }
}*/

@Preview
@Composable
private fun PrviewOtpe() {
    ProjectFlowTheme {
        KanbanBoard()
    }
}


@Composable
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
fun currentWindowAdaptiveInfo(): WindowSizeClass {
    val configuration = LocalConfiguration.current
    val size = DpSize(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
    return WindowSizeClass.calculateFromSize(size)
}
