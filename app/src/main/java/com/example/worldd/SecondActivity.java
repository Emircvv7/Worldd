package com.example.worldd;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String email = getIntent().getStringExtra("email");
        String subject = getIntent().getStringExtra("subject");
        String message = getIntent().getStringExtra("message");


        new SendEmailTask().execute(email, subject, message);
    }

    private class SendEmailTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String toEmail = params[0];
            String subject = params[1];
            String messageText = params[2];

            try {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "587");

                Session session = Session.getDefaultInstance(props, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("umarloh690@gmail.com", "gvrr yzeb rnaa ldr");
                    }
                });

                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress("umarloh690@gmail.com"));
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(messageText);

                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmail});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, messageText);
                startActivity(Intent.createChooser(emailIntent, "Выберите приложение для отправки"));

                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                Log.e("EmailError", "Error: " + e.getMessage(), e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(SecondActivity.this, "Письмо успешно отправлено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SecondActivity.this, "Ошибка отправки письма", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }
}
