package com.example.tezspringbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TezSpringBeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TezSpringBeApplication.class, args);
	}

	/*@Bean
	CommandLineRunner runner(NoticeRepo repo) {
		return args -> {
			Notice noticeFirst = new Notice(
					LocalDate.now(),
					"Sitemiz Faaliyete Geçti",
					"Sitemiz 08/04/2022 tarihinden itibaren demo versiyonu olarak kullanımına sunulmuştur."

			);
			Notice noticeSecond = new Notice(
					LocalDate.now(),
					"Bakım duyurusu",
					"Sitemiz 08.04.2022-14.04.2022 tarihleri arasında yeni geliştirmeler için bakıma alınacaktır."
			);
			repo.insert(noticeFirst);
			repo.insert(noticeSecond);
		};
	}*/

}
