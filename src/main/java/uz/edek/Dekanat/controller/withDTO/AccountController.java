package uz.edek.Dekanat.controller.withDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.edek.Dekanat.dto.OqituvchiDTO;
import uz.edek.Dekanat.entity.Oqituvchi;
import uz.edek.Dekanat.security.JwtTokenUtil;
import uz.edek.Dekanat.security.Token;
import uz.edek.Dekanat.security.UserProvider;
import uz.edek.Dekanat.security.UserSpecial;
import uz.edek.Dekanat.service.withDto.OqituvchiService;
import uz.edek.Dekanat.vm.OqituvchiVM;

@RestController
@RequestMapping("api/account")
public class AccountController {
    @Autowired
    UserProvider userProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OqituvchiService oqituvchiService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/authenticate")
    public ResponseEntity<Token> login(@RequestBody UserSpecial userSpecial) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userSpecial.getUsername(), userSpecial.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        UserDetails userDetails = userProvider.loadUserByUsername(userSpecial.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails, true);
        return ResponseEntity.ok(new Token(token));

    }
    @PostMapping("/register")
    public ResponseEntity<OqituvchiDTO> register(@RequestBody Oqituvchi oqituvchi) throws Exception {
        if (oqituvchi.getId() != null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(oqituvchiService.create(oqituvchi));
    }

    @GetMapping()
    public ResponseEntity<OqituvchiDTO> getCurrentUser(){
        return ResponseEntity.ok(oqituvchiService.getCurrentUser());
    }


    @PutMapping("/password")
    public ResponseEntity<?> updatePassword(@RequestBody OqituvchiVM vm) {
        if(oqituvchiService.changePassword(vm)){
            return ResponseEntity.noContent().build();
        }
        return  ResponseEntity.badRequest().build();
    }
}
