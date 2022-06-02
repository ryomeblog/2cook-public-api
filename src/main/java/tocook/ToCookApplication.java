/*
 *  ■■■                       ■   
 * ■   ■                      ■   
 *     ■   ■■■   ■■■    ■■■   ■  ■
 *    ■   ■  ■  ■  ■■  ■  ■■  ■ ■ 
 *   ■    ■     ■   ■  ■   ■  ■■  
 *  ■     ■     ■   ■  ■   ■  ■ ■ 
 * ■      ■  ■  ■  ■■  ■  ■■  ■ ■■
 * ■■■■■   ■■■   ■■■    ■■■   ■  ■
 *
 * Copyright 2022 ryome. All Rights Reserved.
 *
 */

package tocook;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("tocook.dao")
public class ToCookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToCookApplication.class, args);
	}

}
