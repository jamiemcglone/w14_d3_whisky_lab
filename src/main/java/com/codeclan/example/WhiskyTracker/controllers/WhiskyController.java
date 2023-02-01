package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskys")
    public ResponseEntity<List<Whisky>> findWhiskysFilteredByYear(
            @RequestParam(name = "year", required = false) String year, @RequestParam(name = "distillery", required = false) String distillery, @RequestParam(name = "age", required = false) String age){
                if (year != null){
                    return new ResponseEntity<>(whiskyRepository.findWhiskysByYear(parseInt(year)), HttpStatus.OK);
                }
                else if (age != null & distillery != null){
                    return new ResponseEntity<>(whiskyRepository.findWhiskyByDistilleryNameAndYear(distillery, parseInt(age)), HttpStatus.OK);
                }
                    return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }
}
