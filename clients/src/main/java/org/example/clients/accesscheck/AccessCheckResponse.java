package org.example.clients.accesscheck;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessCheckResponse {
    private Boolean isAccessCheck;
}
