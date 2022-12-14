package com.praxis.praxis_users.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.praxis.praxis_users.domain.enums.Role;
import lombok.*;
import org.hibernate.Hibernate;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(length = 15, unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(length = 60, nullable = false)
    private String password;

    @NotBlank
    @Column(length = 100, nullable = false)
    private String email;

    @Column
    @Builder.Default
    private boolean active = false;

    @Column
    @Builder.Default
    private boolean mobile = false;

    @Column(name = "registration_date")
    private LocalDate registrationDate;

    @Size(max = 15)
    @Column(name = "number_mobile")
    private String numberMobile;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User users = (User) o;
        return id != null && Objects.equals(id, users.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
