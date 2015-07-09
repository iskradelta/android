/**
 *   ownCloud Android client application
 *
 *   @author masensio
 *   Copyright (C) 2015 ownCloud Inc.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License version 2,
 *   as published by the Free Software Foundation.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package com.owncloud.android.widgets;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.owncloud.android.MainApp;
import com.owncloud.android.ui.activity.FileActivity;
import com.owncloud.android.ui.activity.FileDisplayActivity;

public class ShortcutsWidgetBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
        // get the account
        Account account = null;
        String accountName = intent.getStringExtra(ShortcutsWidget.EXTRA_ACCOUNT_NAME);

        AccountManager accountManager = AccountManager.get(context);
        Account[] accounts = accountManager.getAccountsByType(MainApp.getAccountType());
        for(Account a: accounts){
            if (a.name.equals(accountName)){
                account = a;
            }
        }

		if(intent.getAction().equals(ShortcutsWidget.ACTION_APPICON_CLICK)){
            // launch the app
            Intent appIntent = new Intent(context, FileDisplayActivity.class);
            appIntent.putExtra(FileActivity.EXTRA_ACCOUNT, account);
            appIntent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(appIntent);

		}else if (intent.getAction().equals(ShortcutsWidget.ACTION_UPLOAD_CLICK)) {

            Toast.makeText(context, ShortcutsWidget.ACTION_UPLOAD_CLICK, Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(ShortcutsWidget.ACTION_NEW_CLICK)) {
            Toast.makeText(context, ShortcutsWidget.ACTION_NEW_CLICK, Toast.LENGTH_SHORT).show();
        } else if (intent.getAction().equals(ShortcutsWidget.ACTION_REFRESH_CLICK)) {
            Toast.makeText(context, ShortcutsWidget.ACTION_REFRESH_CLICK, Toast.LENGTH_SHORT).show();
        }
	}

//	private void updateWidgetPictureAndButtonListener(Context context) {
//		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_demo);
//		remoteViews.setImageViewResource(R.id.widget_image, getImageToSet());
//
//		//REMEMBER TO ALWAYS REFRESH YOUR BUTTON CLICK LISTENERS!!!
//		remoteViews.setOnClickPendingIntent(R.id.widget_button, MyWidgetProvider.buildButtonPendingIntent(context));
//
//		MyWidgetProvider.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
//	}
//
//	private int getImageToSet() {
//		clickCount++;
//		return clickCount % 2 == 0 ? R.drawable.me : R.drawable.wordpress_icon;
//	}
}