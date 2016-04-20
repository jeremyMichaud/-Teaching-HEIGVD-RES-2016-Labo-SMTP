package ch.heigvd.res.mailrobot.smtp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import ch.heigvd.res.mailrobot.mail.Message;

public class SmtpClient implements ISmtpClient {

	private String host;
	private int port;
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	
	public SmtpClient(String host, int port) {
		this.host = host;
		this.port = port;
	}
    
	@Override
	public void sendMessage(Message message) throws IOException {
		socket = new Socket(host, port);
		writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		System.out.println(reader.readLine());
		writer.printf("EHLO localhost\r\n");
		String line = reader.readLine();
		if(!line.startsWith("250"))
			throw new IOException("SMTP Error!");
		
		while(line.startsWith("250-")){
			line = reader.readLine();
			System.out.println(line);
		}
		
		writer.write("MAIL FROM: ");
		writer.write(message.getFrom());
		writer.write("\r\n");
		writer.flush();
		System.out.println(reader.readLine());
		
		for(String to : message.getTo()){
			writer.write("RCPT TO: ");
			writer.write(to);
			writer.write("\r\n");
			writer.flush();
			line = reader.readLine();
			System.out.println(line);
		}
		
		for(String cc : message.getCc()){
			writer.write("RCPT TO: ");
			writer.write(cc);
			writer.write("\r\n");
			writer.flush();
			line = reader.readLine();
			System.out.println(line);
		}
		
		for(String bcc : message.getBcc()){
			writer.write("RCPT TO: ");
			writer.write(bcc);
			writer.write("\r\n");
			writer.flush();
			line = reader.readLine();
			System.out.println(line);
		}

		writer.write("DATA");
		writer.write("\r\n");
		writer.flush();
		line = reader.readLine();
		System.out.println(line);
		writer.write("Content-Type: text/plain; charset=\"utf-8\"\r\n");
		writer.write("From: " + message.getFrom() + "\r\n");
		
		writer.write("To: " + message.getTo()[0]);
		for(int i = 1; i < message.getTo().length; i++)
			writer.write(", " + message.getTo()[i]);
		
		writer.write("\r\n");
		
		writer.write("Cc: " + message.getCc()[0]);
		for(int i = 1; i < message.getCc().length; i++)
			writer.write(", " + message.getCc()[i]);
		
		writer.write("\r\n");
		writer.flush();
		
		writer.write("Subject: " + message.getSubject() + "\r\n\r\n");
		writer.flush();
		
		writer.write(message.getBody());
		writer.write("\r\n");
		writer.write(".");
		writer.write("\r\n");
		writer.flush();
		
		System.out.println(reader.readLine());
		
		writer.write("QUIT\r\n");
		writer.flush();
		writer.close();
		reader.close();
		socket.close();    
	}

}
