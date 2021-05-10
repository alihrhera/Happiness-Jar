package era.apps.happinessjar.ui.view.masseges

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import era.apps.happinessjar.R


class MessageWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            context.getSharedPreferences("msg", 0).edit()
                    .putInt("widgId", appWidgetId).apply()
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created


    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {
        @JvmStatic
        fun sUpdateAppWidget(context: Context, appWidgetManager: AppWidgetManager, mAppWidgetId: Int) {
            updateAppWidget(context, appWidgetManager, mAppWidgetId)
        }
    }
}

internal fun updateAppWidget(context: Context,
                             appWidgetManager: AppWidgetManager, appWidgetId: Int) {
    val widgetText = context.getSharedPreferences("message", 0)
            .getString("WidgetMessage", "")

    if (!widgetText.equals("null")||widgetText?.length!!>7) {
        val views = RemoteViews(context.packageName, R.layout.message_widget)
        views.setTextViewText(R.id.appwidget_text, widgetText)
        appWidgetManager.updateAppWidget(appWidgetId, views)
        showShareApp(context, widgetText!!)
    }
    }


    fun showShareApp(context: Context, message: String) {

    }
