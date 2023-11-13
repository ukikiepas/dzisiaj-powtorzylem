package ukikiepas.dzisiajpowtorzylem.irregular;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerbDto;
import ukikiepas.dzisiajpowtorzylem.irregular.models.IrregularVerbResponseDto;
import ukikiepas.dzisiajpowtorzylem.user.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/irregular")
public class IrregularController {

    private final IrregularService irregularService;
    private final UserService userService;

    @GetMapping("/getRandomSet")
    public List<IrregularVerbDto> getRandomVerbs(@RequestParam("count") int count, @RequestParam("level") String level){
        return irregularService.getRandomVerbs(count, level);
    }

    @PostMapping("/sendResponse")
    public void getUserResponse(@RequestBody List<IrregularVerbResponseDto> verbResponse, HttpServletRequest request){


    }

}
