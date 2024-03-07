package com.example.validatesonginformation.model;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song implements Validator {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String singer;
    private String category;

    public Song() {
    }

    public Song(Long id, String name, String singer, String category) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Song song = (Song) target;
        String name = song.getName();
        String singer = song.getSinger();
        String category = song.getCategory();
        ValidationUtils.rejectIfEmpty(errors,"name","name.empty");
        ValidationUtils.rejectIfEmpty(errors,"singer","singer.empty");
        ValidationUtils.rejectIfEmpty(errors,"category","category.empty");
        if (name.length()>800) {
            errors.rejectValue("name","name.length");
        }
        if (!name.matches("^[a-zA-Z0-9]+$")){
            errors.rejectValue("name","name.matches");
        }
        if (singer.length()>300) {
            errors.rejectValue("singer","singer.length");
        }
        if (!singer.matches("^[a-zA-Z0-9]+$")){
            errors.rejectValue("singer","singer.matches");
        }
        if (category.length()>800) {
            errors.rejectValue("category","category.length");
        }
        if (!category.matches("^[a-zA-Z0-9_]+$")){
            errors.rejectValue("category","category.matches");
        }
    }
}
