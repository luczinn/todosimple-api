package com.luczinn.todosimple.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity // tratando como uma tabela
@Table(name = User.TABLE_NAME) // definindo informaçoes da tabela
public class User {

    public interface createUser {
    }

    public interface updateUser {
    }

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define o ID de acordo com a ordem(auto-increment)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 100)
    @NotBlank(groups = createUser.class) // faz o @NotNull e NotEmpty juntos
    @Size(groups = createUser.class, min = 3, max = 100)
    private String username;

    @JsonProperty (access = Access.WRITE_ONLY) //Só permite escrita, ou seja nao retorna a senha digitada para o front-end
    @Column(name = "password", nullable = false, length = 30)
    @NotBlank(groups = { createUser.class, updateUser.class })
    @Size(groups = { createUser.class, updateUser.class }, min = 6, max = 30)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();

    public User() {
    }

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public List<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

}
