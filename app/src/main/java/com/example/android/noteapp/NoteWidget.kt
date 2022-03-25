package com.example.android.noteapp

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews

/**
 * Implementation of App Widget functionality.
 */
class NoteWidget : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int
) {
    val intentToOpenNoteApp = Intent(context,MainActivity::class.java)
    val pendingIntentToOpenNoteApp = PendingIntent.getActivity(context,0,intentToOpenNoteApp,0)
    val views = RemoteViews(context.packageName, R.layout.note_widget)
    views.setOnClickPendingIntent(R.id.tvNoteAppTextToOpenMainApp,pendingIntentToOpenNoteApp)

    //    views.setTextViewText(R.id.noteAppTextToOpenMainApp, widgetText)
    appWidgetManager.updateAppWidget(appWidgetId, views)
}