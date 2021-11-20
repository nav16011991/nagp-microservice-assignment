package com.nagarro.nagp.accountservice.persistence.entity;

import com.nagarro.nagp.accountservice.model.common.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "USER_ACCOUNT")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID_USER_ACCOUNT")
    @SequenceGenerator(name = "SEQ_ID_USER_ACCOUNT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACCOUNT_ID")
    private String accountId;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
