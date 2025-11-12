package com.example.uberauth.Dtos;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    private String email;
    private String password;

}


/**
 *
 * difference between putting value in header and cookie or httpOnly cookie
 * if we put token in header it will causes security violations
 * but before that our server are sending jwt token in httpOnly cookie therefore it is nto accesible at client side
 * using Javascript therefore if it is not accesible then our client will fetched it and stored inside header
 *
 * therefore we directly send and fecthed from cookie only not required for client intervention for token setting up
 * and token fetching>>>
 * beacuse httpOnly cookie is accessible from server side>>
 *
 * we can set cookie and fetched cookie using HttpServletRequest and HTTpServlet response object because they have
 *  access to the entire request ody and response body>> just like express controller  have requet and response access>>
 *
 *
 * */