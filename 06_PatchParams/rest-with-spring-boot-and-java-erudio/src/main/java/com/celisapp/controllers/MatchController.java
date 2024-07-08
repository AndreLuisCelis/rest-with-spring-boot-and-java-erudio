package com.celisapp.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.celisapp.exceptions.ResourceNotfoundException;
import com.celisapp.service.MatchService;

import jakarta.websocket.server.PathParam;

@RestController
public class MatchController {
	
	@Autowired
	MatchService matchService;
	
	
	
	@GetMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(
			@PathVariable(value= "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo ) throws Exception{
		return matchService.sum(numberOne, numberTwo);
	}
	
	@GetMapping("/sub/{numberOne}/{numberTwo}")
	public Double sub(
			@PathVariable(value= "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo ) throws Exception{
		return matchService.sub(numberOne, numberTwo);
	}
	
	@GetMapping("/mult/{numberOne}/{numberTwo}")
	public Double mult(
			@PathVariable(value= "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo ) throws Exception{
		return matchService.mult(numberOne, numberTwo);
	}
	
	@GetMapping("/divider/{numberOne}/{numberTwo}")
	public Double divider(
			@PathVariable(value= "numberOne") String numberOne, 
			@PathVariable(value = "numberTwo") String numberTwo ) throws Exception{
		return matchService.divider(numberOne, numberTwo);
	}

}
