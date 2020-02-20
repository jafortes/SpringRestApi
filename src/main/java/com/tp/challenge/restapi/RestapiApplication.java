package com.tp.challenge.restapi;

import java.io.File;
import java.io.IOException;
import com.tp.challenge.restapi.family.CountryCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.tp.challenge.restapi.family.Family;
import com.tp.challenge.restapi.family.FamilyRepository;
import com.tp.challenge.restapi.familym.FamilyM;
import com.tp.challenge.restapi.familym.FamilyMRepository;

import org.springframework.boot.CommandLineRunner;



@SpringBootApplication
public class RestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
			
	}
	
	/*@Bean
    public CommandLineRunner ValuesDemo(FamilyRepository fRepository,
    		FamilyMRepository fmRepository) {
		
		 //create ObjectMapper instance
        //ObjectMapper objectMapper = new ObjectMapper();

        //read json file and convert to customer object
        //CountryCode cccode = objectMapper.readValue(new File("/json/ISO3166_1.json"), CountryCode.class);

        //print customer details
        //System.out.println(cccode);
		
        return args -> { 
            Family f = new Family(1,"Silva", "pt");
            Family f2 = new Family(2,"Moreira", "pt");
            fRepository.save(f);
            fRepository.save(f2);            
            fmRepository.save(new FamilyM("Rui", "Silva","1","2", LocalDate.of(1980, 11, 11),f));
            fmRepository.save(new FamilyM("Maria", "Silva","3","4", LocalDate.of(1990, 11, 11),f));
            fmRepository.save(new FamilyM("Pedro", "Moreira","5","6", LocalDate.of(1990, 11, 11),f2));
            fmRepository.save(new FamilyM("Ana", "Moreira","7","8", LocalDate.of(2000, 11, 11),f2));
        };
    }*/
	

	
}

