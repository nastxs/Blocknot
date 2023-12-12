Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        NotificationChannel channel = new NotificationChannel(
                "TEST_CHANNEL",
                "TEST_DESCRYPTION",
                NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, "TEST_CHANNEL")
                    .setContentTitle("Приветсвую тебя!")
                    .setContentText("Какие планы на сегодня?")
                    .setSmallIcon(R.mipmap.ic_launcher)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setContentIntent(contentIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.tack_ico)) // большая картинка
                .setTicker("Последнее китайское предупреждение!") // до Lollipop
                .setAutoCancel(true) // автоматически закрыть уведомление после нажатия

                    .build();
        // Удаляем конкретное уведомление
        notificationManager.cancel(42);

// Удаляем все свои уведомления
        notificationManager.cancelAll();

            notificationManager.notify(42, notification);
