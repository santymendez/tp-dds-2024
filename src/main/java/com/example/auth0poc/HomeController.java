package com.example.auth0poc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the home page.
 */

@Controller
public class HomeController {

  /**
   * Renders the home page.
   *
   * @param model the model
   * @param principal the OIDC user principal
   * @return the view name
   */

  @GetMapping("/")
  public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
    if (principal != null) {
      model.addAttribute("profile", principal.getClaims());
    }
    return "index";
  }
}