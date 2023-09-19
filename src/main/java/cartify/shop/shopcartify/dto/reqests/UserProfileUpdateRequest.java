package cartify.shop.shopcartify.dto.reqests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserProfileUpdateRequest {

    private Long userId;

    private String firstName;

    private String lastName;
}
