package com.uguryazici.uwallet.service;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.model.PlayerDTO;

import java.util.Optional;

public interface PlayerService {
    Player save(Player player);

    Iterable<Player> findAll();

    void deleteByPlayerId(Long playerId);

    Optional<Player> findById(Long playerId);
}
