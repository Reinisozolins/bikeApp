package lv.ozo.bikeApp.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private  long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name="name")
    private String name;

    @NotBlank(message = "Item id is mandatory")
    @Column(name = "item_id")
    private String item_id;

    public Bike(){}

    public Bike(String name, String item_id){
        this.name = name;
        this.item_id = item_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
}
