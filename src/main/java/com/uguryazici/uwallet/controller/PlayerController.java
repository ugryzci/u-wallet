package com.uguryazici.uwallet.controller;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.entity.Transaction;
import com.uguryazici.uwallet.exception.PlayerNotFoundException;
import com.uguryazici.uwallet.model.PlayerDTO;
import com.uguryazici.uwallet.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@ControllerAdvice
@RestController
@RequestMapping(value = "/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping(path = "/add")
    public ResponseEntity<Player> addPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        player = playerService.save(player);

        return new ResponseEntity<>(player, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{playerId}")
    public ResponseEntity deleteByPlayerId(@PathVariable Long playerId) {
        playerService.deleteByPlayerId(playerId);

        return new ResponseEntity<>("Player Deleted.",HttpStatus.OK);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @GetMapping(path = "/list")
    public ResponseEntity<Iterable<Player>> listPlayer() {
        Iterable<Player> all = playerService.findAll();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping(path = "{playerId}")
    public ResponseEntity<Player> getPlayertById(@PathVariable long playerId) {
        Optional<Player> player = playerService.findById(playerId);

        if (!player.isPresent()) {
            throw new PlayerNotFoundException();
        }

        return new ResponseEntity<Player>(player.get(), HttpStatus.OK);
    }

}
