package com.g4.tp.model.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(
        name = "user_practiced_sports",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "sport_id")
    )
    private List<Sport> practicedSports;

    @ManyToOne
    @JoinColumn(name = "favorite_sport_id")
    private Sport favoriteSport;

    @Enumerated(EnumType.STRING)
    private SkillLevelEnum skillLevel;

    public User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Sport> getPracticedSports() {
        return practicedSports;
    }

    public void setPracticedSports(List<Sport> practicedSports) {
        this.practicedSports = practicedSports;
    }

    public Sport getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport(Sport favoriteSport) {
        this.favoriteSport = favoriteSport;
    }

    public SkillLevelEnum getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(SkillLevelEnum skillLevel) {
        this.skillLevel = skillLevel;
    }
}
