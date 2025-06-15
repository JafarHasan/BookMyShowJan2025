package com.BookMyShowJan2025.BookMyShow.Controllers;

import com.BookMyShowJan2025.BookMyShow.DTOs.ShowDto;
import com.BookMyShowJan2025.BookMyShow.Models.Show;
import com.BookMyShowJan2025.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/show")
public class ShowController {

    private final ShowService showService;
    @Autowired
    public ShowController(ShowService showService){
        this.showService=showService;
    }

    //POST http://localhost:8080/show/add +showDto in requestBody
    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto){
        String message=showService.addShow(showDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //http://localhost:8080/show/delete-by-id?showId=1
    @DeleteMapping("/delete-by-id")
    public ResponseEntity<String> deleteShowById(@RequestParam Integer showId){
        String response=showService.deleteShow(showId);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
