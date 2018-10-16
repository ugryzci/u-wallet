package com.uguryazici.uwallet.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;
}
