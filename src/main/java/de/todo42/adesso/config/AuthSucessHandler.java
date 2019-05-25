package de.todo42.adesso.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import de.todo42.adesso.user.User;
import de.todo42.adesso.user.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @NonNull
    private UserRepository userRepository;
    
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = userRepository.findByUsername(authentication.getName());
        User user = (User) userDetails;
        user.setLastLogin(new Date());
        userRepository.save(user);
        
        setDefaultTargetUrl("http://localhost:8088/");
        super.onAuthenticationSuccess(request, response, authentication);
        
    }

}
