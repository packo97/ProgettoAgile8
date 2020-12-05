package it.unical.demacs.inf.asd.ProgettoAgile8;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {

  @Bean
  public ModelMapper getModelMapper() {return new ModelMapper();}

}