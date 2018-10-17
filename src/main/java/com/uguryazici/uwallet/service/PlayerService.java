package com.uguryazici.uwallet.service;

import com.uguryazici.uwallet.entity.Player;

import java.util.Optional;

public interface PlayerService {
    Player save(Player player);

    Iterable<Player> findAll();

    void deleteByPlayerId(Long playerId);

    Optional<Player> findById(Long playerId);
}
