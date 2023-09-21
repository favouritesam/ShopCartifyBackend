package cartify.shop.shopcartify.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")

public class ShopCartifyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;
    private String lastName;

    @NaturalId(mutable = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private List<UserRole> role;

    private boolean isEnabled = false;

    public ShopCartifyUser() {
        this.role = new ArrayList<>();
    }

}