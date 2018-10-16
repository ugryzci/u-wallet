package com.uguryazici.uwallet.service.impl;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.repository.PlayerRepository;
import com.uguryazici.uwallet.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public void deleteByPlayerId(Long playerId) {
        playerRepository.deleteById(playerId);
    }

    @Override
    public Optional<Player> findById(Long playerId) {
        return playerRepository.findById(playerId);
    }
}
