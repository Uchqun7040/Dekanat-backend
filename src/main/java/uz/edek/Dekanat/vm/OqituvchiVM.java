package uz.edek.Dekanat.vm;

import lombok.Data;

@Data
public class OqituvchiVM {
    private String username;
    private String oldPassword;
    private String newPassword;
    private  String confirm;
}
