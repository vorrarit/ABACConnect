package com.zicure.abacconnect.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;


import com.zicure.abacconnect.R;
import com.zicure.abacconnect.main.MainActivity;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.chat.ChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

/**
 * Created by mid on 11/3/2015.
 */
public class PushNotificationService extends Service implements ChatMessageListener {

	private static final String TAG = PushNotificationService.class.getSimpleName();
	private AbstractXMPPConnection connection = null;
	private ChatManager chatManager = null;
	private Chat chat = null;

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "PushNotificationService started");

		if (connection == null || !connection.isConnected()) {
			Log.d(TAG, "PushNotificationService trying to connect XMPP");
			XMPPTask task = new XMPPTask();
			task.execute("");
		}

		return Service.START_STICKY;
	}

	@Override
	public void processMessage(Chat chat, Message message) {
		if (message.getType() == Message.Type.chat || message.getType() == Message.Type.normal) {
			if (message.getBody() != null) {
				String messageBody = message.getBody();

				Log.d(TAG, messageBody);

				NotificationCompat.Builder notiBuilder = new NotificationCompat.Builder(this)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle("ABAC Connect")
						.setContentText(messageBody)
						.setAutoCancel(true);

				Intent resultIntent = new Intent(this, MainActivity.class);

				TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
				stackBuilder.addParentStack(MainActivity.class);
				stackBuilder.addNextIntent(resultIntent);

				PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
				);

				notiBuilder.setContentIntent(resultPendingIntent);

				NotificationManager notiManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				notiManager.notify(1, notiBuilder.build());
//				try {
//					chat.sendMessage(messageBody.toUpperCase());
//				} catch (SmackException.NotConnectedException e) {
//					Log.d(TAG, e.getMessage());
//					e.printStackTrace();
//				}


			}
		}
	}

	public class XMPPTask extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... strings) {
			SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(PushNotificationService.this);

			connection = new XMPPTCPConnection(XMPPTCPConnectionConfiguration.builder().setHost("180.180.243.84")
					.setPort(5222).setUsernameAndPassword("abac-user", "password"
					).setServiceName("vlc-pakgon").setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
					.setDebuggerEnabled(true).build());

			try {
				connection.connect().login();
				Presence presence = new Presence(Presence.Type.available);
				presence.setStatus("ready");
				connection.sendStanza(presence);

				chatManager = ChatManager.getInstanceFor(connection);
				chatManager.addChatListener(new ChatManagerListener() {
					@Override
					public void chatCreated(Chat chat, boolean createdLocally) {
						chat.addMessageListener(PushNotificationService.this);
					}
				});
				chat = chatManager.createChat("abac-connect@vlc-pakgon");
				//chat.sendMessage("Hello");

				Log.d(TAG, "PushNotificationService connected to XMPP");

				Thread.sleep(10000);
			} catch (XMPPException e) {
				e.printStackTrace();
			} catch (SmackException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			return "";
		}
	}
}
