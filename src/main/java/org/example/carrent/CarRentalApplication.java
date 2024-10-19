package org.example.carrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@SpringBootApplication
public class CarRentalApplication {

	public static void main(String[] args) {
		disableSslVerification();
		SpringApplication.run(CarRentalApplication.class, args);
	}
	public static void disableSslVerification() {
		try {
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
				@Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) { }
				@Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) { }
			} };

			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new SecureRandom());

			SSLContext.setDefault(sc);

			HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
