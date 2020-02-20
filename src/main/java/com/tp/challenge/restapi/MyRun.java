package com.tp.challenge.restapi;

import com.tp.challenge.restapi.family.Family;
import com.tp.challenge.restapi.family.FamilyRepository;
import com.tp.challenge.restapi.familym.FamilyM;
import com.tp.challenge.restapi.familym.FamilyMRepository;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRun implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRun.class);
    
    
    @Autowired
    private FamilyRepository fRepository;    
    @Autowired
	private FamilyMRepository fmRepository;

    @Override
    public void run(String... args) throws Exception {        
    	fRepository.deleteAll();
    	fmRepository.deleteAll();

        Family f1 = new Family(1,"Silva", "pt");
        Family f2 = new Family(2,"Moreira", "br");
        Family f3 = new Family(3,"ElChapo", "es");
        Family f4 = new Family(4,"Brexit", "uk");
        
        fRepository.save(f1);
        fRepository.save(f2);
        fRepository.save(f3);
        fRepository.save(f4);
        fmRepository.save(new FamilyM("Rui", "Silva","1","2", LocalDate.of(1980, 11, 11),f1));
        fmRepository.save(new FamilyM("Maria", "Silva","3","4", LocalDate.of(1990, 11, 11),f1));
        
        fmRepository.save(new FamilyM("Pedro", "Moreira","5","6", LocalDate.of(1990, 11, 11),f2));
        fmRepository.save(new FamilyM("Ana", "Moreira","7","8", LocalDate.of(2000, 11, 11),f2));
    	    	
        fmRepository.save(new FamilyM("Hernandez", "ElChapo","1","2", LocalDate.of(1980, 11, 11),f3));
        fmRepository.save(new FamilyM("Mariana", "ElChapo","3","4", LocalDate.of(1990, 11, 11),f3));
    	
        fRepository.findAll().forEach((Family) -> {
            logger.info("{}", Family);
        });
    }
}